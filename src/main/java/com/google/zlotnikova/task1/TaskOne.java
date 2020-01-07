package com.google.zlotnikova.task1;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaskOne {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void runTaskOne() {

        String fileName = "SQLQueries.txt";
        try (InputStream inputStream = TaskOne.class.getClassLoader().getResourceAsStream(fileName)) {
            try {
                String text;
                Scanner scanner;
                if (inputStream != null) {
                    scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
                    while (scanner.hasNext()) {
                        text = scanner.nextLine();
                        logger.info(text);
                    }
                } else {
                    throw new IllegalArgumentException("Resource file is not found");
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

}