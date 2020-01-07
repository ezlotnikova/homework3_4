package com.google.zlotnikova.task2.repository.model;

import java.util.ArrayList;
import java.util.List;

import com.google.zlotnikova.task2.repository.constant.CarModelEnum;
import com.google.zlotnikova.task2.util.RandomUtil;

public class CarFactory {

    public static List<Car> createRandomCars(int amount) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cars.add(createRandomCar("Car_" + (i + 1)));
        }
        return cars;
    }

    public static Car createRandomCar(String name) {
        String model = String.valueOf(CarModelEnum.values()[RandomUtil.getElement(0, 2)]);
        int engineCapacity = RandomUtil.getElement(1, 10);
        return new Car(name, model, engineCapacity);
    }

}