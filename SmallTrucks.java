
package com.mycompany.proje3;

public class SmallTrucks extends Truck {

    public SmallTrucks(String plateNumber, int numOfTires, int dailyFee, long loadingCapacity) {
        super(plateNumber, numOfTires, dailyFee, loadingCapacity);
    }

    @Override
    public String toString() {
        return "Small Truck{" +
                "plate number=" + getPlateNumber() +
                ", tires=" + getNumOfTires() +
                ", daily fee=" + getDailyFee() +
                ", loading capacity=" + getLoadingCapacity() +
                '}';
    }
}
