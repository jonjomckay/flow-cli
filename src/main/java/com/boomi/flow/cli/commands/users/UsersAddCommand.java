package com.boomi.flow.cli.commands.users;

import com.boomi.flow.cli.client.Callback;
import com.boomi.flow.cli.client.FlowClient;
import com.boomi.flow.cli.commands.AbstractCommand;
import com.boomi.flow.cli.configuration.Configuration;
import com.boomi.flow.cli.configuration.Runtime;
import picocli.CommandLine;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static org.fusesource.jansi.Ansi.ansi;

@CommandLine.Command(name = "add")
public class UsersAddCommand extends AbstractCommand implements Runnable {
    @CommandLine.Parameters(index = "0")
    private String email;

    public UsersAddCommand(FlowClient flowClient, Configuration configuration) {
        super(flowClient, configuration);
    }

    @Override
    public void run() {
        Runtime runtime = configuration.getRuntimes()
                .stream()
                .filter(item -> item.getName().equals(commonOptions.getRuntime()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No runtime could be found with that name"));

        System.out.print(ansi().render("⏳\tAdding user with the email @|blue %s|@ to the tenant @|blue %s|@ in the @|blue %s|@ runtime", email, commonOptions.getTenant(), commonOptions.getRuntime()).newline());

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.print(ansi().a(".").cursorToColumn(0));
            }
        }, 0, 500);

        flowClient.addUser(runtime.getUser().getToken(), email, new Callback<Void>() {
            @Override
            public void onFailure(IOException exception) {
                System.err.println("❌\tSomething went wrong adding the user: " + exception.getMessage());
            }

            @Override
            public void onResponse(Void item) throws IOException {
                timer.cancel();

                System.out.println(ansi().eraseLine().render("✅\tAdded the user @|blue %s|@", email));
                System.exit(0);
            }
        });
    }
}
