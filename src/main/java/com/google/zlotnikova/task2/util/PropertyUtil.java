package com.google.zlotnikova.task2.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyUtil {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String CONFIG_PROPERTY_FILE_NAME = "config.properties";

    private PropertyUtil() {
    }

    public static String getProperty(String name) {
        Properties properties = new Properties();
        try (
                InputStream propertiesStream = PropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTY_FILE_NAME)
        ) {
            if (propertiesStream != null) {
                properties.load(propertiesStream);
            } else {
                throw new IllegalArgumentException("Config property file is not found");
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalArgumentException("Config property file is not found");
        }
        return properties.getProperty(name);
    }

}
