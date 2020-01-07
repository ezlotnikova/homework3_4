package com.google.zlotnikova.task2.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.zlotnikova.task2.repository.DatabaseRepository;

public class DatabaseRepositoryImpl implements DatabaseRepository {

    @Override
    public void createObject(Connection connection, String query) throws SQLException {
        try (
                Statement statement = connection.createStatement()
        ) {
            statement.execute(query);
        }
    }

}