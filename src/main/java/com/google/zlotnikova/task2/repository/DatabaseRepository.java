package com.google.zlotnikova.task2.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseRepository {

    void createObject(Connection connection, String query) throws SQLException;

}