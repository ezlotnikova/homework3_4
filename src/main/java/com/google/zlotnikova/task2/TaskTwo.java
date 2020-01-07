package com.google.zlotnikova.task2;

import java.util.List;
import java.util.Random;

import com.google.zlotnikova.task2.repository.model.Car;
import com.google.zlotnikova.task2.repository.model.CarFactory;
import com.google.zlotnikova.task2.service.CarService;
import com.google.zlotnikova.task2.service.DatabaseService;
import com.google.zlotnikova.task2.service.impl.CarServiceImpl;
import com.google.zlotnikova.task2.service.impl.DatabaseServiceImpl;
import com.google.zlotnikova.task2.util.DatabaseInitUtil;

public class TaskTwo {

    private static DatabaseService databaseService = new DatabaseServiceImpl();
    private static CarService carService = new CarServiceImpl();
    private static final Random random = new Random();

    public static void runTaskTwo() {

        String fileName = "init.sql";
        String queryDB = DatabaseInitUtil.getInitData(fileName);
        databaseService.initializeDatabase(queryDB);

        String query = "CREATE TABLE car (name VARCHAR (50), model VARCHAR (50), engine_capacity INT (10))";
        databaseService.createTable(query);

        int carAmount = 10;
        List<Car> cars = CarFactory.createRandomCars(carAmount);
        carService.addAll(cars);

        int capacity = random.nextInt();
        carService.selectByCapacity(capacity);

        int minCapacity = carService.findMinCapacity();
        carService.deleteByCapacity(minCapacity);

        capacity = random.nextInt();
        carService.countByCapacity(capacity);

        capacity = random.nextInt();
        String newName = "Good capacity";
        carService.renameByCapacity(capacity, newName);
    }

}