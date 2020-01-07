package com.google.zlotnikova.task2.service;

public interface DatabaseService {

    void initializeDatabase(String query);

    void createTable(String query);

}