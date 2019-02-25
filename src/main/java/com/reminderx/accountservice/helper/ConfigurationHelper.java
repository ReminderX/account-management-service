package com.reminderx.accountservice.helper;

import com.google.common.base.Strings;
import com.reminderx.accountservice.properties.ServerProperties;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ConfigurationHelper {
    public static <T> T getConfiguration(String configurationPath, Class<T> clazz)
            throws IllegalAccessException, InstantiationException, FileNotFoundException {
        if (Strings.isNullOrEmpty(configurationPath)) {
            return clazz.newInstance();
        }

        File initialFile = new File(configurationPath);
        InputStream targetStream = new FileInputStream(initialFile);
        Yaml yaml = new Yaml(new Constructor(ServerProperties.class));
        return yaml.load(targetStream);
    }
}
