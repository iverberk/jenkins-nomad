package org.jenkinsci.plugins.nomad;

import hudson.Extension;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Util;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.model.Label;
import hudson.model.labels.LabelAtom;
import jenkins.model.Jenkins;

import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Logger;

public class NomadSlaveTemplate implements Describable<NomadSlaveTemplate> {

    private static final String SLAVE_PREFIX = "jenkins-";
    private static final Logger LOGGER = Logger.getLogger(NomadSlaveTemplate.class.getName());

    private final int idleTerminationInMinutes;

    private final int cpu;
    private final int memory;
    private final int disk;
    private final int priority;
    private final String labels;
    private final String region;
    private final String remoteFs;
    private final String image;

    private NomadCloud cloud;
    private String driver;
    private final String[] datacenters = new String[]{"dc1"};
    private Set<LabelAtom> labelSet;

    @DataBoundConstructor
    public NomadSlaveTemplate(
            String cpu,
            String memory,
            String disk,
            String labels,
            String remoteFs,
            String idleTerminationInMinutes,
            String region,
            String priority,
            String image
            ) {
        this.cpu = Integer.parseInt(cpu);
        this.memory = Integer.parseInt(memory);
        this.disk = Integer.parseInt(disk);
        this.priority = Integer.parseInt(priority);
        this.idleTerminationInMinutes = Integer.parseInt(idleTerminationInMinutes);
        this.remoteFs = remoteFs;
        this.labels = Util.fixNull(labels);
        this.labelSet = Label.parse(labels);
        this.region = region;
        this.image = image;

        readResolve();
    }

    protected Object readResolve() {
        this.driver = !this.image.equals("") ? "docker" : "java";
        return this;
    }

    @Extension
    public static final class DescriptorImpl extends Descriptor<NomadSlaveTemplate> {

        public DescriptorImpl() {
            load();
        }

        @Override
        public String getDisplayName() {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Descriptor<NomadSlaveTemplate> getDescriptor() {
        return Jenkins.getInstance().getDescriptor(getClass());
    }

    public String createSlaveName() {
        return SLAVE_PREFIX + Long.toHexString(System.nanoTime());
    }

    public Set<LabelAtom> getLabelSet() {
        return labelSet;
    }

    public int getNumExecutors() {
        return 1;
    }

    public int getCpu() {
        return cpu;
    }

    public int getMemory() {
        return memory;
    }

    public String getLabels() {
        return labels;
    }

    public int getIdleTerminationInMinutes() {
        return idleTerminationInMinutes;
    }

    public String getRegion() {
        return region;
    }

    public String[] getDatacenters() {
        return datacenters;
    }

    public int getPriority() {
        return priority;
    }

    public int getDisk() {
        return disk;
    }

    public void setCloud(NomadCloud cloud) {
        this.cloud = cloud;
    }

    public NomadCloud getCloud() {
        return cloud;
    }

    public String getRemoteFs() {
        return remoteFs;
    }

    public String getImage() {
        return image;
    }

    public String getDriver() {
        return driver;
    }
}
