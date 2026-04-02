/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proje3;



/**
 *
 * @author HP
 */
public class SUV extends Car{
    
    private String wheelDrive;
    
    public SUV(String plateNumber, int numOfTires, int dailyfee, String color, int seatingCapacity, int numOfDoors, String wheelDrive) {
    super(plateNumber, numOfTires, dailyfee, color, seatingCapacity, numOfDoors);
    
    if (isValidWheelDrive(wheelDrive)) {
        this.wheelDrive = wheelDrive.toUpperCase(); 
    } else {
        throw new IllegalArgumentException("Invalid wheel drive type: " + wheelDrive);
    }
}

    private boolean isValidWheelDrive(String wd) {
        return wd.equalsIgnoreCase("FWD") || wd.equalsIgnoreCase("RWD") || wd.equalsIgnoreCase("4WD") || wd.equalsIgnoreCase("AWD");
    }

    public String getWheelDrive() {
        return wheelDrive;
    }

    public void setWheelDrive(String wheelDrive) {
        if (isValidWheelDrive(wheelDrive)) {
            this.wheelDrive = wheelDrive.toUpperCase();
        } else {
            throw new IllegalArgumentException("Invalid wheel drive type: " + wheelDrive);
        }
    }
    
    @Override
    public String toString() {
        return "SUV{" +
                "plate number=" + getPlateNumber() +
                ", tires=" + getNumOfTires() +
                ", daily fee=" + getDailyFee() +
                ", color='" + color + '\'' +
                ", seating capacity=" + seatingCapacity +
                ", number of doors=" + numOfDoors +
                ", wheel drive is=" + wheelDrive +
                '}';
    }
}