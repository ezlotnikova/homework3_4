package com.google.zlotnikova.task2.repository.model;

public class Car {

    private final String name;
    private final String model;
    private final int engineCapacity;

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public Car(String name, String model, int engineCapacity) {
        this.name = name;
        this.model = model;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", model=" + model +
                ", engineCapacity=" + engineCapacity +
                '}';
    }

}