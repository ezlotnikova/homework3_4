package com.google.zlotnikova.task2.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.zlotnikova.task2.repository.model.Car;

public interface CarRepository {

    void add(Connection connection, Car car) throws SQLException;

    List<Car> selectByCapacity(Connection connection, int capacity) throws SQLException;

    int findMinCapacity(Connection connection) throws SQLException;

    int deleteByCapacity(Connection connection, int capacity) throws SQLException;

    int countByCapacity(Connection connection, int capacity) throws SQLException;

    int renameByCapacity(Connection connection, int capacity, String newName) throws SQLException;

}