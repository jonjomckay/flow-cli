package com.boomi.flow.cli;

import com.boomi.flow.cli.client.FlowClient;
import com.boomi.flow.cli.client.HttpFlowClient;
import com.boomi.flow.cli.configuration.Configuration;
import com.boomi.flow.cli.configuration.ConfigurationProvider;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import picocli.CommandLine;


public class Application {
    public static void main(String[] args) {

        FlowClient flowClient = new HttpFlowClient(new Gson(), new OkHttpClient());
        Configuration configuration = ConfigurationProvider.create();

        CliFactory cliFactory = new CliFactory(flowClient, configuration);

        CommandLine.run(Cli.class, cliFactory, System.err, args);
    }
}
