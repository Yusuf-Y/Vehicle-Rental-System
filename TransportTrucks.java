package com.mycompany.proje3;

public class TransportTrucks extends Truck {
    private boolean isItAbroad;

    public TransportTrucks(String plateNumber, int numOfTires, int dailyFee, long loadingCapacity, boolean isItAbroad) {
        super(plateNumber, numOfTires, dailyFee, loadingCapacity);
        this.isItAbroad = isItAbroad;
    }

    public boolean isItAbroad() {
        return isItAbroad;
    }

    @Override
    public String toString() {
        return "TransportTruck{" +
                "plateNumber='" + getPlateNumber() + '\'' +
                ", tires=" + getNumOfTires() +
                ", dailyFee=" + getDailyFee() +
                ", loadingCapacity=" + loadingCapacity +
                ", isItAbroad=" + isItAbroad +
                '}';
    }
}
