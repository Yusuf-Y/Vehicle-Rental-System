package com.mycompany.proje3;

public class StationWagon extends Car {
    private long loadingCapacity;
    private long currentLoad = 0;

    public StationWagon(String plateNumber, int numOfTires, int dailyFee, String color, int seatingCapacity, int numOfDoors, long loadingCapacity) {
        super(plateNumber, numOfTires, dailyFee, color, seatingCapacity, numOfDoors);
        this.loadingCapacity = loadingCapacity;
    }

    public long getLoadingCapacity() {
        return loadingCapacity;
    }

    @Override
    public void loadMe(long additionalLoad) throws OverWeightException {
        if (currentLoad + additionalLoad > loadingCapacity) {
            throw new OverWeightException("Loading capacity exceeded!");
        }
        currentLoad += additionalLoad;
        System.out.println("Loaded " + additionalLoad + " units. Current load: " + currentLoad);
    }

    @Override
    public String toString() {
        return "StationWagon{" +
                "plateNumber='" + getPlateNumber() + '\'' +
                ", tires=" + getNumOfTires() +
                ", dailyFee=" + getDailyFee() +
                ", color='" + color + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", numOfDoors=" + numOfDoors +
                ", loadingCapacity=" + loadingCapacity +
                '}';
    }
}
