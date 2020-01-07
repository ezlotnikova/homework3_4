package com.google.zlotnikova.task2.service;

import java.util.List;

import com.google.zlotnikova.task2.repository.model.Car;

public interface CarService {

    void addAll(List<Car> cars);

    void selectByCapacity(int capacity);

    int findMinCapacity();

    void deleteByCapacity(int capacity);

    void countByCapacity(int capacity);

    void renameByCapacity(int capacity, String newName);
}