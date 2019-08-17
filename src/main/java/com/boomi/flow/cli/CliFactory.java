package com.boomi.flow.cli;

import com.boomi.flow.cli.client.FlowClient;
import com.boomi.flow.cli.commands.AbstractCommand;
import com.boomi.flow.cli.configuration.Configuration;
import picocli.CommandLine;

public class CliFactory implements CommandLine.IFactory {
    private final FlowClient flowClient;
    private final Configuration configuration;

    public CliFactory(FlowClient flowClient, Configuration configuration) {
        this.flowClient = flowClient;
        this.configuration = configuration;
    }

    @Override
    public <K> K create(Class<K> cls) throws Exception {
        if (AbstractCommand.class.isAssignableFrom(cls)) {
            return cls.getConstructor(FlowClient.class, Configuration.class)
                    .newInstance(flowClient, configuration);
        }

        return CommandLine.defaultFactory().create(cls);
    }
}
