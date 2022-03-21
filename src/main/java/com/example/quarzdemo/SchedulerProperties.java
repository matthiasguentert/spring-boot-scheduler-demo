package com.example.quarzdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "scheduler")
public class SchedulerProperties {
    private String cron;
    private int fixedRate;
    private String[] someArray;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public int getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(int fixedRate) {
        this.fixedRate = fixedRate;
    }

    public String[] getSomeArray() {
        return someArray;
    }

    public void setSomeArray(String[] someArray) {
        this.someArray = someArray;
    }
}
