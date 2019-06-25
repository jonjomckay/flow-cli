package com.boomi.flow.cli.configuration;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private List<Runtime> runtimes = new ArrayList<>();

    public List<Runtime> getRuntimes() {
        return runtimes;
    }

    public void setRuntimes(List<Runtime> runtimes) {
        this.runtimes = runtimes;
    }
}
