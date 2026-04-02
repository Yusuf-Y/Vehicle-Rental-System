package com.mycompany.proje3;

public class Sports extends Car {
    private int power;

    public Sports(String plateNumber, int numOfTires, int dailyFee, String color, int seatingCapacity, int numOfDoors, int power) {
        super(plateNumber, numOfTires, dailyFee, color, seatingCapacity, numOfDoors);
        this.power = power;
    }

    @Override
    public String toString() {
        return "Sports{" +
                "plateNumber='" + getPlateNumber() + '\'' +
                ", tires=" + getNumOfTires() +
                ", dailyFee=" + getDailyFee() +
                ", color='" + color + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", numOfDoors=" + numOfDoors +
                ", power=" + power +
                '}';
    }
}