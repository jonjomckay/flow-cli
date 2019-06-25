package com.boomi.flow.cli.commands;

import com.boomi.flow.cli.commands.users.UsersListCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "users", subcommands = {
        UsersListCommand.class
})
public class UsersCommand {
}
