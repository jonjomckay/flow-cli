package com.boomi.flow.cli.commands;

import picocli.CommandLine;

@CommandLine.Command(name = "configure")
public class ConfigureCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("❗ You don't seem to have a user token configured for the Flow Cli.");
        System.out.println("   Opening up a browser window for you to authorize access: https://flow.boomi.com/authorize?code=12345");

        System.out.println();

        System.out.println("\uD83D\uDCAC Once you've authorized, paste your token here:");
        System.out.println("   eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODIiwibmFtZSI6IkpvaG4gRG9l...");

        System.out.println();
        System.out.println("   ....");
        System.out.println();

        System.out.println("\uD83D\uDC4B Hello Jonjo!");
        System.out.println("   You've successfully authenticated with the hosted Boomi Flow platform.");
    }
}
