package com.boomi.flow.cli.commands.users;

import com.boomi.flow.cli.client.Callback;
import com.boomi.flow.cli.client.FlowClient;
import com.boomi.flow.cli.client.User;
import com.boomi.flow.cli.commands.AbstractCommand;
import com.boomi.flow.cli.configuration.Configuration;
import com.boomi.flow.cli.configuration.Runtime;
import com.jakewharton.fliptables.FlipTable;
import picocli.CommandLine;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CommandLine.Command(name = "list")
public class UsersListCommand extends AbstractCommand implements Runnable {
    private FlowClient flowClient;
    private Configuration configuration;

    @Inject
    public UsersListCommand(FlowClient flowClient, Configuration configuration) {
        this.flowClient = flowClient;
        this.configuration = configuration;
    }

    @Override
    public void run() {
        Runtime runtime = configuration.getRuntimes()
                .stream()
                .filter(item -> item.getName().equals(commonOptions.getRuntime()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No runtime could be found with that name"));

        System.out.println("Loading users...");
        System.out.println();

        flowClient.listUsers(runtime.getUser().getToken(), new Callback<List<User>>() {
            @Override
            public void onFailure(IOException exception) {
                System.err.println("Something went wrong listing users: " + exception.getMessage());
            }

            @Override
            public void onResponse(List<User> users) throws IOException {
                List<String[]> data = new ArrayList<>();

                for (User user : users) {
                    data.add(new String[] {
                            user.getId().toString(),
                            user.getFirstName() + " " + user.getLastName(),
                            user.getEmail()
                    });
                }

                String[] headers = new String[] {
                        "ID",
                        "Name",
                        "Email"
                };

                System.out.println(FlipTable.of(headers, data.toArray(new String[0][])));
                System.exit(0);
            }
        });
    }
}
