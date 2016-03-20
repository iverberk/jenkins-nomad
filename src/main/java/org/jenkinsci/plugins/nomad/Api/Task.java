package org.jenkinsci.plugins.nomad.Api;

import java.util.Map;

public class Task {
    private String Name;
    private String Driver;
    private Map<String, Object> Config;
    private Resource Resources;
    private LogConfig LogConfig;
    private Artifact[] Artifacts;

    public Task(
            String name,
            String driver,
            Map<String, Object> config,
            Resource resources,
            LogConfig logConfig,
            Artifact[] artifacts
    ) {
        Name = name;
        Driver = driver;
        Config = config;
        Resources = resources;
        LogConfig = logConfig;
        Artifacts = artifacts;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String driver) {
        Driver = driver;
    }

    public Map<String, Object> getConfig() {
        return Config;
    }

    public void setConfig(Map<String, Object> config) {
        Config = config;
    }

    public Resource getResources() {
        return Resources;
    }

    public void setResources(Resource resources) {
        Resources = resources;
    }

    public LogConfig getLogConfig() {
        return LogConfig;
    }

    public void setLogConfig(LogConfig logConfig) {
        LogConfig = logConfig;
    }

    public Artifact[] getArtifacts() {
        return Artifacts;
    }

    public void setArtifacts(Artifact[] artifacts) {
        Artifacts = artifacts;
    }

}
