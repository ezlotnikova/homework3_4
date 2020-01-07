package com.google.zlotnikova.task2.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.google.zlotnikova.task1.TaskOne;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseInitUtil {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    static public String getInitData (String fileName){
        String initData = null;
        try (InputStream inputStream = TaskOne.class.getClassLoader().getResourceAsStream(fileName)) {
            try {
                Scanner scanner;
                if (inputStream != null) {
                    scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
                    StringBuilder stringBuilder = new StringBuilder();
                    while (scanner.hasNext()) {
                        stringBuilder.append(scanner.nextLine());
                    }
                    initData = stringBuilder.toString();
                } else {
                    throw new IOException("Resource file not found");
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    return initData;
    }
}
