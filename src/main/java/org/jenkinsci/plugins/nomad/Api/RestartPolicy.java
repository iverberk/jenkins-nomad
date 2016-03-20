package org.jenkinsci.plugins.nomad.Api;

public class RestartPolicy {

    private Long Interval;
    private String Mode;
    private Long Delay;
    private Integer Attempts;

    public RestartPolicy(Integer attempts, Long interval, Long delay, String mode) {
        Interval = interval;
        Mode = mode;
        Delay = delay;
        Attempts = attempts;
    }

    public Long getInterval() {
        return Interval;
    }

    public void setInterval(Long interval) {
        Interval = interval;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public Long getDelay() {
        return Delay;
    }

    public void setDelay(Long delay) {
        Delay = delay;
    }

    public Integer getAttempts() {
        return Attempts;
    }

    public void setAttempts(Integer attempts) {
        Attempts = attempts;
    }
}
