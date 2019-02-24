package com.reminderx.accountservice.helper;

import com.google.common.base.Strings;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class ConfigurationHelper {
    public static <T> T getConfiguration(String configurationPath, Class<T> clazz)
            throws IllegalAccessException, InstantiationException {
        if (Strings.isNullOrEmpty(configurationPath)) {
            return clazz.newInstance();
        }

        Yaml yaml = new Yaml();
        InputStream inputStream = ConfigurationHelper.class
                .getClassLoader()
                .getResourceAsStream(configurationPath);
        return yaml.load(inputStream);
    }
}
