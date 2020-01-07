package com.google.zlotnikova.task2.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.zlotnikova.task2.repository.CarRepository;
import com.google.zlotnikova.task2.repository.ConnectionRepository;
import com.google.zlotnikova.task2.repository.impl.CarRepositoryImpl;
import com.google.zlotnikova.task2.repository.impl.ConnectionRepositoryImpl;
import com.google.zlotnikova.task2.repository.model.Car;
import com.google.zlotnikova.task2.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarServiceImpl implements CarService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = new ConnectionRepositoryImpl();
    private CarRepository carRepository = new CarRepositoryImpl();

    @Override
    public void addAll(List<Car> cars) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            int counter = 0;
            try {
                for (Car car : cars) {
                    carRepository.add(connection, car);
                    counter++;
                }
                connection.commit();
                logger.info(counter + " car(s) added");
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void selectByCapacity(int capacity) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Car> cars = carRepository.selectByCapacity(connection, capacity);
                logger.info(cars.size() + " car(s) selected with engine capacity " + capacity);
                cars.forEach(logger::info);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public int findMinCapacity() {
        int minCapacity = -1;
        try (Connection connection = connectionRepository.getConnection()) {
            try {
                minCapacity = carRepository.findMinCapacity(connection);
                logger.info("minimal engine capacity found is " + minCapacity);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return minCapacity;
    }

    @Override
    public void deleteByCapacity(int capacity) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int rowsDeleted = carRepository.deleteByCapacity(connection, capacity);
                connection.commit();
                logger.info(rowsDeleted + " car(s) deleted with engine capacity " + capacity);
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void countByCapacity(int capacity) {
        try (Connection connection = connectionRepository.getConnection()) {
            try {
                int countRows = carRepository.countByCapacity(connection, capacity);
                logger.info("count of cars with engine capacity " + capacity + " is " + countRows);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void renameByCapacity(int capacity, String newName) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int renamedRows = carRepository.renameByCapacity(connection, capacity, newName);
                logger.info(renamedRows + " car(s) with engine capacity " + capacity + " renamed to " + newName);
                List<Car> cars = carRepository.selectByCapacity(connection, capacity);
                cars.forEach(logger::info);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

}