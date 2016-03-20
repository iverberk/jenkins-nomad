package org.jenkinsci.plugins.nomad.Api;

import java.util.Map;

public class Artifact {

    private String GetterSource;
    private Map<String, String> GetterOptions;
    private String RelativeDest;

    public Artifact(String getterSource, Map<String, String> getterOptions, String relativeDest) {
        GetterSource = getterSource;
        GetterOptions = getterOptions;
        RelativeDest = relativeDest;
    }

    public String getGetterSource() {
        return GetterSource;
    }

    public void setGetterSource(String getterSource) {
        GetterSource = getterSource;
    }

    public Map<String, String> getGetterOptions() {
        return GetterOptions;
    }

    public void setGetterOptions(Map<String, String> getterOptions) {
        GetterOptions = getterOptions;
    }

    public String getRelativeDest() {
        return RelativeDest;
    }

    public void setRelativeDest(String relativeDest) {
        RelativeDest = relativeDest;
    }
}
