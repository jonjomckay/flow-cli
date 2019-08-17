package com.boomi.flow.cli.configuration;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import javax.inject.Provider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigurationProvider implements Provider<Configuration> {
    @Override
    public Configuration get() {
        String value = System.getenv("XDG_CONFIG_HOME");

        if (value == null || value.trim().length() == 0) {
            value = System.getenv("HOME") + File.separator + ".config";
        }

        File file = new File(value + "/boomi/flow-cli.yml");

        YamlReader reader;
        try {
            reader = new YamlReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not find a configuration file at $XDG_CONFIG_HOME/boomi/flow-cli.yml");
        }

        try {
            return reader.read(Configuration.class);
        } catch (YamlException e) {
            throw new RuntimeException(e);
        }
    }
}
