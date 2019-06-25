package com.boomi.flow.cli;

import com.google.inject.Guice;
import com.google.inject.Injector;
import picocli.CommandLine;


public class Application {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new CliModule());

        CommandLine.run(Cli.class, injector.getInstance(CliFactory.class), System.err, args);
    }
}
