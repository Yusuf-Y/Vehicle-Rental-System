package com.mycompany.proje3;

import java.time.LocalDate;

public class Truck extends Vehicle {
    protected long loadingCapacity;

    public Truck(String plateNumber, int numOfTires, int dailyFee, long loadingCapacity) {
        super(plateNumber, numOfTires, dailyFee);
        this.loadingCapacity = loadingCapacity;
        canCarryLoad = true;
    }

    public long getLoadingCapacity() {
        return loadingCapacity;
    }

    @Override
    public void rentMe(LocalDate startDate, LocalDate endDate) throws SorryWeDontHaveThatOneException {
        if (!isBooked) {
            System.out.println("You must book the truck before renting.");
            return;
        }

        LocalDate today = LocalDate.now();
        if (bookedStartDate == null || bookedStartDate.isAfter(today.minusDays(7))) {
            System.out.println("Trucks must be booked at least 1 week before renting.");
            return;
        }

        if (isRented && Vehicle.datesOverlap(startDate, endDate, rentedStartDate, rentedEndDate)) {
            throw new SorryWeDontHaveThatOneException("Truck already rented in that period.");
        }

        rentedStartDate = startDate;
        rentedEndDate = endDate;
        isRented = true;

        System.out.println("Truck rented successfully from " + startDate + " to " + endDate);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "plate number=" + getPlateNumber() +
                ", tires=" + getNumOfTires() +
                ", daily fee=" + getDailyFee() +
                ", loading capacity=" + loadingCapacity +
                '}';
    }
}
