package com.boomi.flow.cli.commands;

import com.boomi.flow.cli.options.CommonOptions;
import picocli.CommandLine;

public abstract class AbstractCommand {
    @CommandLine.Mixin
    protected CommonOptions commonOptions;

    public void setCommonOptions(CommonOptions commonOptions) {
        this.commonOptions = commonOptions;
    }
}
