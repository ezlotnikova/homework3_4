package com.google.zlotnikova.task2.repository.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.zlotnikova.task2.repository.ConnectionRepository;
import com.google.zlotnikova.task2.util.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.google.zlotnikova.task2.repository.constant.ConnectionConstant.DATABASE_URL;
import static com.google.zlotnikova.task2.repository.constant.ConnectionConstant.MYSQL_SERVER_URL;
import static com.google.zlotnikova.task2.repository.constant.ConnectionConstant.PASSWORD;
import static com.google.zlotnikova.task2.repository.constant.ConnectionConstant.USERNAME;

public class ConnectionRepositoryImpl implements ConnectionRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    @Override
    public Connection getInitialConnection() {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            return DriverManager.getConnection(
                    PropertyUtil.getProperty(MYSQL_SERVER_URL),
                    PropertyUtil.getProperty(USERNAME),
                    PropertyUtil.getProperty(PASSWORD)
            );
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot get connection to database");
        }
        catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot find MySQL driver at classpath");
        }
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            return DriverManager.getConnection(
                    PropertyUtil.getProperty(DATABASE_URL),
                    PropertyUtil.getProperty(USERNAME),
                    PropertyUtil.getProperty(PASSWORD)
            );
        }
        catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot get connection to database");
        }
        catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot find MySQL driver at classpath");
        }
    }

}