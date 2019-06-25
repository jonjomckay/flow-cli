package com.boomi.flow.cli;

import com.boomi.flow.cli.commands.ConfigureCommand;
import com.boomi.flow.cli.commands.FlowsCommand;
import com.boomi.flow.cli.commands.UsersCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "flow-cli", subcommands = {
        ConfigureCommand.class,
        FlowsCommand.class,
        UsersCommand.class
})
public class Cli implements Runnable {
    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true)
    boolean isHelp;

    @CommandLine.Option(names = {"-v", "--version"}, versionHelp = true)
    boolean isVersion;

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }
}
