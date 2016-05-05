package org.jenkinsci.plugins.nomad;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.jenkinsci.plugins.nomad.Api.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NomadApi {

    private static final Logger LOGGER = Logger.getLogger(NomadApi.class.getName());

    private static final OkHttpClient client = new OkHttpClient();

    private final String nomadApi;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public NomadApi(String nomadApi) {
        this.nomadApi = nomadApi;
    }

    public void startSlave(String slaveName, NomadSlaveTemplate template) {

        String slaveJob = buildSlaveJob(
            slaveName,
            template
        );

        try {
            RequestBody body = RequestBody.create(JSON, slaveJob);
            Request request = new Request.Builder()
                    .url(this.nomadApi + "/v1/job/" + slaveName)
                    .put(body)
                    .build();

            client.newCall(request).execute().body().close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }


    public void stopSlave(String slaveName) {

        Request request = new Request.Builder()
                .url(this.nomadApi + "/v1/job/" + slaveName)
                .delete()
                .build();

        try {
            client.newCall(request).execute().body().close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    private Map<String,Object> buildDriverConfig(String name, NomadSlaveTemplate template) {
        Map<String,Object> driverConfig = new HashMap<>();

        if (template.getDriver().equals("java")) {
            driverConfig.put("jar_path", "slave.jar");
            driverConfig.put("args", new String[]{"-jnlpUrl", template.getCloud().getJenkinsUrl() + "computer/" + name + "/slave-agent.jnlp"});
        } else if (template.getDriver().equals("docker")) {
            driverConfig.put("image", template.getImage());
            driverConfig.put("command", "java");
            driverConfig.put("args", new String[]{"-jar", "/local/local/slave.jar", "-jnlpUrl", template.getCloud().getJenkinsUrl() + "computer/" + name + "/slave-agent.jnlp"});
        }

        return driverConfig;
    }

    String buildSlaveJob(
            String name,
            NomadSlaveTemplate template
    ) {

        Task task = new Task(
                "jenkins-slave",
                template.getDriver(),
                buildDriverConfig(name, template),
                new Resource(
                    template.getCpu(),
                    template.getMemory(),
                    template.getDisk()
                ),
                new LogConfig(1, 10),
                new Artifact[]{
                    new Artifact(template.getCloud().getSlaveUrl(), null, "local/")
                }
        );

        TaskGroup taskGroup = new TaskGroup(
                "jenkins-slave-taskgroup",
                1,
                new Task[]{task},
                new RestartPolicy(0, 10000000000L, 1000000000L, "fail")
        );

        Job job = new Job(
                name,
                name,
                template.getRegion(),
                "batch",
                template.getPriority(),
                template.getDatacenters(),
                new TaskGroup[]{taskGroup}
        );

        Gson gson = new Gson();
        JsonObject jobJson = new JsonObject();

        jobJson.add("Job", gson.toJsonTree(job));

        return gson.toJson(jobJson);
    }
}
