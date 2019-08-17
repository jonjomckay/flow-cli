package com.boomi.flow.cli.commands;

import com.boomi.flow.cli.client.FlowClient;
import com.boomi.flow.cli.configuration.Configuration;
import com.boomi.flow.cli.options.CommonOptions;
import picocli.CommandLine;

public abstract class AbstractCommand {
    protected final FlowClient flowClient;
    protected final Configuration configuration;

    @CommandLine.Mixin
    protected CommonOptions commonOptions;

    protected AbstractCommand(FlowClient flowClient, Configuration configuration) {
        this.flowClient = flowClient;
        this.configuration = configuration;
    }

    public void setCommonOptions(CommonOptions commonOptions) {
        this.commonOptions = commonOptions;
    }
}
