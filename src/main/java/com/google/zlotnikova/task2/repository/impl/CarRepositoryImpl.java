package com.google.zlotnikova.task2.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.zlotnikova.task2.repository.model.Car;
import com.google.zlotnikova.task2.repository.CarRepository;

public class CarRepositoryImpl implements CarRepository {

    @Override
    public void add(Connection connection, Car car) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO car(name, model, engine_capacity) VALUES (?,?,?)"
                )
        ) {
            statement.setString(1, car.getName());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getEngineCapacity());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding car failed, no rows affected.");
            }
        }
    }

    @Override
    public List<Car> selectByCapacity(Connection connection, int capacity) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM car WHERE engine_capacity = ?")
        ) {
            List<Car> cars = new ArrayList<>();
            statement.setInt(1, capacity);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Car car = getCar(rs);
                    cars.add(car);
                }
                return cars;
            }
        }
    }

    @Override
    public int findMinCapacity(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement()
        ) {
            int minCapacity = -1;
            try (
                    ResultSet rs = statement.executeQuery("SELECT MIN(engine_capacity) FROM car")
            ) {
                if (rs.next()) {
                    minCapacity = rs.getInt("MIN(engine_capacity)");
                }
            }
            return minCapacity;
        }
    }

    @Override
    public int deleteByCapacity(Connection connection, int capacity) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement("DELETE FROM car WHERE engine_capacity = ?")
        ) {
            statement.setInt(1, capacity);
            return statement.executeUpdate();
        }
    }

    @Override
    public int countByCapacity(Connection connection, int capacity) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM car WHERE engine_capacity = ?")
        ) {
            statement.setInt(1, capacity);
            int countRows = -1;
            try (
                    ResultSet rs = statement.executeQuery()
            ) {
                if (rs.next()) {
                    countRows = rs.getInt("COUNT(*)");
                }
                return countRows;
            }
        }
    }

    @Override
    public int renameByCapacity(Connection connection, int capacity, String newName) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement("UPDATE car SET name = ? WHERE engine_capacity = ?")
        ) {
            statement.setString(1, newName);
            statement.setInt(2, capacity);
            return statement.executeUpdate();
        }
    }

    private Car getCar(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String model = rs.getString("model");
        int engineCapacity = rs.getInt("engine_capacity");
        return new Car(name, model, engineCapacity);
    }

}