package com.mycompany.proje3;

import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehiclePark {
    
    ArrayList<Vehicle> vehicleList = new ArrayList<>();
    
    public void displayVehicles() {
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }
        for (Vehicle v : vehicleList) {
            System.out.println(v.toString());
            System.out.println("-------------------------");
        }
    }

    public void displayAvailableVehicles(LocalDate startDate, LocalDate endDate) {
        boolean found = false;
        for (Vehicle v : vehicleList) {
            if ((v.isRented() && Vehicle.datesOverlap(startDate, endDate, v.getRentedStartDate(), v.getRentedEndDate())) ||
                (v.isBooked() && Vehicle.datesOverlap(startDate, endDate, v.getBookedStartDate(), v.getBookedEndDate()))) {
                continue;
            }
            System.out.println(v.toString());
            System.out.println("-------------------------");
            found = true;
        }
        if (!found) {
            System.out.println("No available vehicles for the given dates.");
        }
    }
   
    public void dropVehicle(String plateNumber) {
        Vehicle vehicle = findVehicleByPlate(plateNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        vehicle.dropMe(LocalDate.now());
        System.out.println("Vehicle dropped off.");
    }

    // <=== FIXED addVehicle method ===>
    public void addVehicle() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plate number: ");
        String plateNumber = sc.nextLine();

        System.out.print("Enter number of tires: ");
        int numOfTires = Integer.parseInt(sc.nextLine());

        System.out.print("Enter daily fee: ");
        int dailyFee = Integer.parseInt(sc.nextLine());

        System.out.print("Enter vehicle type (SUV, SmallTruck, Sport, StationWagon, TransportTruck): ");
        String type = sc.nextLine();

        Vehicle newVehicle = null;

        switch (type.toLowerCase()) {
            case "suv":
                System.out.print("Enter color: ");
                String color = sc.nextLine();

                System.out.print("Enter seating capacity: ");
                int seatingCapacity = Integer.parseInt(sc.nextLine());

                System.out.print("Enter number of doors: ");
                int numOfDoors = Integer.parseInt(sc.nextLine());

                System.out.print("Enter wheel drive (FWD, RWD, 4WD, AWD): ");
                String wheelDrive = sc.nextLine();

                newVehicle = new SUV(plateNumber, numOfTires, dailyFee, color, seatingCapacity, numOfDoors, wheelDrive);
                break;

            case "smalltruck":
                System.out.print("Enter loading capacity: ");
                long loadingCapacitySmallTruck = Long.parseLong(sc.nextLine()); // long here

                newVehicle = new SmallTrucks(plateNumber, numOfTires, dailyFee, loadingCapacitySmallTruck);
                break;

            case "sport":
                System.out.print("Enter color: ");
                String sportColor = sc.nextLine();

                System.out.print("Enter seating capacity: ");
                int sportSeatingCapacity = Integer.parseInt(sc.nextLine());

                System.out.print("Enter number of doors: ");
                int sportNumOfDoors = Integer.parseInt(sc.nextLine());

                System.out.print("Enter power: ");
                int power = Integer.parseInt(sc.nextLine());

                newVehicle = new Sports(plateNumber, numOfTires, dailyFee, sportColor, sportSeatingCapacity, sportNumOfDoors, power);
                break;

            case "stationwagon":
                System.out.print("Enter color: ");
                String swColor = sc.nextLine();

                System.out.print("Enter seating capacity: ");
                int swSeatingCapacity = Integer.parseInt(sc.nextLine());

                System.out.print("Enter number of doors: ");
                int swNumOfDoors = Integer.parseInt(sc.nextLine());

                System.out.print("Enter loading capacity: ");
                long swLoadingCapacity = Long.parseLong(sc.nextLine());

                newVehicle = new StationWagon(plateNumber, numOfTires, dailyFee, swColor, swSeatingCapacity, swNumOfDoors, swLoadingCapacity);
                break;

            case "transporttruck":
                System.out.print("Enter loading capacity: ");
                long loadingCapacity = Long.parseLong(sc.nextLine());

                System.out.print("Is the truck abroad? (true/false): ");
                boolean isItAbroad = Boolean.parseBoolean(sc.nextLine());

                newVehicle = new TransportTrucks(plateNumber, numOfTires, dailyFee, loadingCapacity, isItAbroad);
                break;

            default:
                System.out.println("Invalid vehicle type.");
                return;
        }

        if (newVehicle != null) {
            vehicleList.add(newVehicle);
            System.out.println("Vehicle added successfully.");
        }
    }

    protected void removeVehicle(Vehicle newVehicle) throws TheVehicleDoesNotExistException {
        if(vehicleList.contains(newVehicle)) {
            vehicleList.remove(newVehicle);
        } else {
            throw new TheVehicleDoesNotExistException("The vehicle is not on the list.");
        }
    }
    
    public void rentVehicle(String plateNumber, LocalDate startDate, LocalDate endDate) {
        Vehicle vehicle = findVehicleByPlate(plateNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        try {
            vehicle.rentMe(startDate, endDate);
        } catch (SorryWeDontHaveThatOneException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Vehicle findVehicleByPlate(String plateNumber) {
        for (Vehicle v : vehicleList) {
            if (v.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                return v;
            }
        }
        return null;
    }
    
    public void displayAvailableVehiclesByType(LocalDate startDate, LocalDate endDate, String type) {
        boolean found = false;

        for (Vehicle vehicle : vehicleList) {
            if (!vehicle.getClass().getSimpleName().equalsIgnoreCase(type)) {
                continue; 
            }

            if ((vehicle.isRented() && Vehicle.datesOverlap(startDate, endDate, vehicle.getRentedStartDate(), vehicle.getRentedEndDate())) ||
                (vehicle.isBooked() && Vehicle.datesOverlap(startDate, endDate, vehicle.getBookedStartDate(), vehicle.getBookedEndDate()))) {
                continue;
            }

            System.out.println(vehicle.toString());
            System.out.println("------------------------------------");
            found = true;
        }

        if (!found) {
            System.out.println("No available vehicles of type " + type + " for the given dates.");
        }
    }
    
    public void cancelBooking(String plateNumber) {
        Vehicle vehicle = findVehicleByPlate(plateNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        try {
            vehicle.cancelMe(LocalDate.now());
        } catch (NoCancellationYouMustPayException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void bookVehicle(String plateNumber, LocalDate startDate, LocalDate endDate) {
        Vehicle vehicle = findVehicleByPlate(plateNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        try {
            vehicle.bookMe(startDate, endDate);
            System.out.println("Vehicle booked successfully from " + startDate + " to " + endDate);
        } catch (SorryWeDontHaveThatOneException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
    
    public void dropVehicle(String plateNumber, LocalDate dropDate) {
        Vehicle vehicle = findVehicleByPlate(plateNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        vehicle.dropMe(dropDate);
        System.out.println("Vehicle dropped off on " + dropDate);
    }

    public void loadVehicle(String plateNumber, long loadAmount) {
        Vehicle vehicle = findVehicleByPlate(plateNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!vehicle.canCarryLoad) {
            System.out.println("This vehicle cannot carry load.");
            return;
        }

        try {
            vehicle.loadMe(loadAmount);
            System.out.println("Loaded " + loadAmount + " units onto vehicle " + plateNumber);
        } catch (OverWeightException e) {
            System.out.println("Error loading vehicle: " + e.getMessage());
        }
    }
    
    public void dailyReport(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("=== Daily Vehicle Report ===\n\n");

            if (vehicleList.isEmpty()) {
                writer.write("No vehicles in the system.\n");
            } else {
                for (Vehicle v : vehicleList) {
                    writer.write(v.toString() + "\n");

                    if (v.isBooked()) {
                        writer.write("  Booked from " + v.getBookedStartDate() + " to " + v.getBookedEndDate() + "\n");
                    }
                    if (v.isRented()) {
                        writer.write("  Rented from " + v.getRentedStartDate() + " to " + v.getRentedEndDate() + "\n");
                    }
                }
            }

            writer.flush();
            System.out.println("Daily report written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing daily report: " + e.getMessage());
        }
    }
}
