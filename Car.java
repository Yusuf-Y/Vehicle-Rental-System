/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proje3;


public class Car extends Vehicle {
    protected String color;
    protected int seatingCapacity;
    protected int numOfDoors;

    public Car(String plateNumber, int numOfTires, int dailyFee, String color, int seatingCapacity, int numOfDoors) {
        super(plateNumber, numOfTires, dailyFee);
        this.color = color;
        this.seatingCapacity = seatingCapacity;
        this.numOfDoors = numOfDoors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plate number=" + getPlateNumber() +
                ", tires=" + getNumOfTires() +
                ", daily fee=" + getDailyFee() +
                ", color='" + color + '\'' +
                ", seating capacity=" + seatingCapacity +
                ", number of doors=" + numOfDoors +
                '}';
    }
}