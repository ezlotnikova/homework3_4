package com.google.zlotnikova.task2.repository;

import java.sql.Connection;

public interface ConnectionRepository {

    Connection getInitialConnection();

    Connection getConnection();

}