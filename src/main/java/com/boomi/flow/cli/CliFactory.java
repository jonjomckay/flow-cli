package com.boomi.flow.cli;

import com.google.inject.Injector;
import picocli.CommandLine;

import javax.inject.Inject;

public class CliFactory implements CommandLine.IFactory {
    private final Injector injector;

    @Inject
    public CliFactory(Injector injector) {
        this.injector = injector;
    }

    @Override
    public <K> K create(Class<K> cls) throws Exception {
        return injector.getInstance(cls);
    }
}
