package org.jenkinsci.plugins.nomad.Api;

public class Resource {
    private Integer CPU;
    private Integer MemoryMB;
    private Integer DiskMB;

    public Resource(Integer CPU, Integer memoryMB, Integer diskMB) {
        this.CPU = CPU;
        MemoryMB = memoryMB;
        DiskMB = diskMB;
    }

    public Integer getCPU() {
        return CPU;
    }

    public void setCPU(Integer CPU) {
        this.CPU = CPU;
    }

    public Integer getMemoryMB() {
        return MemoryMB;
    }

    public void setMemoryMB(Integer memoryMB) {
        MemoryMB = memoryMB;
    }

    public Integer getDiskMB() {
        return DiskMB;
    }

    public void setDiskMB(Integer diskMB) {
        DiskMB = diskMB;
    }
}
