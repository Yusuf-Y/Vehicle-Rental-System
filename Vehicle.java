
package com.mycompany.proje3;


import java.time.LocalDate;

public class Vehicle {
    protected String plateNumber;
    protected int numOfTires;
    protected int dailyFee;
    protected boolean canCarryLoad = false;

    protected boolean isBooked = false;
    protected boolean isRented = false;
    protected LocalDate bookedStartDate;
    protected LocalDate bookedEndDate;
    protected LocalDate rentedStartDate;
    protected LocalDate rentedEndDate;

    public Vehicle(String plateNumber, int numOfTires, int dailyFee) {
        this.plateNumber = plateNumber;
        this.numOfTires = numOfTires;
        this.dailyFee = dailyFee;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getNumOfTires() {
        return numOfTires;
    }

    public int getDailyFee() {
        return dailyFee;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public boolean isRented() {
        return isRented;
    }

    public LocalDate getBookedStartDate() {
        return bookedStartDate;
    }

    public LocalDate getBookedEndDate() {
        return bookedEndDate;
    }

    public LocalDate getRentedStartDate() {
        return rentedStartDate;
    }

    public LocalDate getRentedEndDate() {
        return rentedEndDate;
    }

    public void bookMe(LocalDate startDate, LocalDate endDate) throws SorryWeDontHaveThatOneException {
        if (isBooked && datesOverlap(startDate, endDate, bookedStartDate, bookedEndDate)) {
            throw new SorryWeDontHaveThatOneException("Vehicle is already booked during this period.");
        }

        bookedStartDate = startDate;
        bookedEndDate = endDate;
        isBooked = true;
        System.out.println("Vehicle booked successfully from " + startDate + " to " + endDate);
    }

    public void cancelMe(LocalDate cancellationDate) throws NoCancellationYouMustPayException {
        if (!isBooked) {
            System.out.println("No booking to cancel.");
            return;
        }

        if (cancellationDate.isAfter(bookedStartDate)) {
            throw new NoCancellationYouMustPayException("You must pay, cancellation is too late!");
        }

        isBooked = false;
        bookedStartDate = null;
        bookedEndDate = null;
        System.out.println("Booking cancelled.");
    }

    public void rentMe(LocalDate startDate, LocalDate endDate) throws SorryWeDontHaveThatOneException {
        if (isRented && datesOverlap(startDate, endDate, rentedStartDate, rentedEndDate)) {
            throw new SorryWeDontHaveThatOneException("Vehicle already rented in that period.");
        }

        rentedStartDate = startDate;
        rentedEndDate = endDate;
        isRented = true;
        System.out.println("Vehicle rented successfully from " + startDate + " to " + endDate);
    }

    public void dropMe(LocalDate dropDate) {
        if (!isRented) {
            System.out.println("Vehicle is not currently rented.");
            return;
        }

        isRented = false;
        rentedStartDate = null;
        rentedEndDate = null;
        System.out.println("Vehicle returned on " + dropDate);
    }

   public static boolean datesOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
    return !start1.isAfter(end2) && !end1.isBefore(start2);
}
   
    public void loadMe(long loadAmount) throws UnsupportedOperationException {
        if (!canCarryLoad) {
            throw new UnsupportedOperationException("This vehicle cannot carry load.");
        }
    }

    public boolean canCarryLoad() {
        return canCarryLoad;
    }

    @Override
    public String toString() {
        return "Vehicle {plateNumber='" + plateNumber + "', tires=" + numOfTires + ", dailyFee=" + dailyFee + "}";
    }
}