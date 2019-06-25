package com.boomi.flow.cli.options;

import picocli.CommandLine;

import java.util.UUID;

public class CommonOptions {
    @CommandLine.Option(names = {"--runtime"}, description = "The name of the runtime use")
    private String runtime;

    @CommandLine.Option(names = {"--tenant"}, description = "The ID of the tenant to use")
    private UUID tenant;

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public UUID getTenant() {
        return tenant;
    }

    public void setTenant(UUID tenant) {
        this.tenant = tenant;
    }
}
