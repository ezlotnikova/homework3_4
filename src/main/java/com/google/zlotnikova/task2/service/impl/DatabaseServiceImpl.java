package com.google.zlotnikova.task2.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

import com.google.zlotnikova.task2.repository.ConnectionRepository;
import com.google.zlotnikova.task2.repository.DatabaseRepository;
import com.google.zlotnikova.task2.repository.impl.ConnectionRepositoryImpl;
import com.google.zlotnikova.task2.repository.impl.DatabaseRepositoryImpl;
import com.google.zlotnikova.task2.service.DatabaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseServiceImpl implements DatabaseService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = new ConnectionRepositoryImpl();
    private DatabaseRepository databaseRepository = new DatabaseRepositoryImpl();

    @Override
    public void initializeDatabase(String query) {
        try (Connection connection = connectionRepository.getInitialConnection()) {
            try {
                databaseRepository.createObject(connection, query);
                logger.info("database created");
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void createTable(String query) {
        try (Connection connection = connectionRepository.getConnection()) {
            try {
                databaseRepository.createObject(connection, query);
                logger.info("table 'car' created");
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

}