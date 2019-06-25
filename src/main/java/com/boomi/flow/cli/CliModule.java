package com.boomi.flow.cli;

import com.boomi.flow.cli.client.FlowClient;
import com.boomi.flow.cli.client.HttpFlowClient;
import com.boomi.flow.cli.configuration.Configuration;
import com.boomi.flow.cli.configuration.ConfigurationProvider;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;

import javax.inject.Singleton;

public class CliModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Configuration.class).toProvider(ConfigurationProvider.class);
        bind(FlowClient.class).to(HttpFlowClient.class);
        bind(Gson.class).toProvider(Gson::new).in(Singleton.class);
    }
}
