package org.jenkinsci.plugins.nomad;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import static org.junit.Assert.assertTrue;

/**
 * @author Yegor Andreenko
 */
public class NomadApiTest {

    NomadApi nomadApi = new NomadApi("http://localhost");
    NomadSlaveTemplate slaveTemplate = new NomadSlaveTemplate(
            "300", "256", "100",
            null, "remoteFs", "3",
            "ams", "0", "image"
    );

    private NomadCloud nomadCloud = new NomadCloud(
            "nomad",
            "nomadUrl",
            "jenkinsUrl",
            "slaveUrl",
            Collections.singletonList(slaveTemplate));

    @Before
    public void setup() {
        slaveTemplate.setCloud(nomadCloud);
    }

    @Test
    public void testStartSlave() {
        String job = nomadApi.buildSlaveJob("slave-1", slaveTemplate);
        
        assertTrue(job.contains("\"Region\":\"ams\""));
        assertTrue(job.contains("\"CPU\":300"));
        assertTrue(job.contains("\"MemoryMB\":256"));
        assertTrue(job.contains("\"DiskMB\":100"));
        assertTrue(job.contains("\"GetterSource\":\"slaveUrl\""));
    }

}
