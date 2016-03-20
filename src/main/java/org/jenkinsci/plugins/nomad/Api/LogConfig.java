package org.jenkinsci.plugins.nomad.Api;

public class LogConfig {
    private Integer MaxFiles;
    private Integer MaxFileSizeMB;

    public LogConfig(Integer maxFiles, Integer maxFileSizeMB) {
        MaxFiles = maxFiles;
        MaxFileSizeMB = maxFileSizeMB;
    }

    public Integer getMaxFiles() {
        return MaxFiles;
    }

    public void setMaxFiles(Integer maxFiles) {
        MaxFiles = maxFiles;
    }

    public Integer getMaxFileSizeMB() {
        return MaxFileSizeMB;
    }

    public void setMaxFileSizeMB(Integer maxFileSizeMB) {
        MaxFileSizeMB = maxFileSizeMB;
    }
}
