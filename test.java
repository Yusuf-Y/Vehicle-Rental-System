package com.mycompany.proje3;

import java.time.LocalDate;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VehiclePark park = new VehiclePark();

        while (true) {
            System.out.println("Welcome to the Vehicle Rental System");
            System.out.println("Are you an Admin (1) or Customer (2)? Press 0 to Exit.");
            int role = sc.nextInt();
            sc.nextLine(); 

            if (role == 1) {
                runAdminMenu(sc, park);
            } else if (role == 2) {
                runCustomerMenu(sc, park);
            } else if (role == 0) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }

    private static void runAdminMenu(Scanner sc, VehiclePark park) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Display all vehicles");
            System.out.println("2. Display available vehicles (by dates)");
            System.out.println("3. Add a new vehicle");
            System.out.println("4. Remove a vehicle");
            System.out.println("5. Daily report");
            System.out.println("6. Quit to main menu");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    park.displayVehicles();
                    break;
                case 2:
                    LocalDate[] range = askForDateRange(sc);
                    park.displayAvailableVehicles(range[0], range[1]);
                    break;
                case 3:
                    addVehicleMenu(sc, park);
                    break;
                case 4:
                    System.out.print("Enter plate number to remove: ");
                    String plate = sc.nextLine();
                    Vehicle toRemove = park.findVehicleByPlate(plate);
                    try {
                        park.removeVehicle(toRemove);
                        System.out.println("Vehicle removed.");
                    } catch (TheVehicleDoesNotExistException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Enter filename for report: ");
                    String file = sc.nextLine();
                    park.dailyReport(file);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static void runCustomerMenu(Scanner sc, VehiclePark park) {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Display all vehicles");
            System.out.println("2. Display available vehicles (by dates)");
            System.out.println("3. Display available vehicles by type");
            System.out.println("4. Book a vehicle");
            System.out.println("5. Cancel my booking");
            System.out.println("6. Rent a vehicle");
            System.out.println("7. Drop a vehicle");
            System.out.println("8. Load a vehicle");
            System.out.println("9. Quit to main menu");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    park.displayVehicles();
                    break;
                case 2:
                    LocalDate[] range = askForDateRange(sc);
                    park.displayAvailableVehicles(range[0], range[1]);
                    break;
                case 3:
                    LocalDate[] range2 = askForDateRange(sc);
                    System.out.print("Enter vehicle type: ");
                    String type = sc.nextLine();
                    park.displayAvailableVehiclesByType(range2[0], range2[1], type);
                    break;
                case 4:
                    System.out.print("Plate number: ");
                    String plate = sc.nextLine();
                    LocalDate[] bookRange = askForDateRange(sc);
                    park.bookVehicle(plate, bookRange[0], bookRange[1]);
                    break;
                case 5:
                    System.out.print("Plate number to cancel: ");
                    park.cancelBooking(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Plate number: ");
                    String rentPlate = sc.nextLine();
                    LocalDate[] rentRange = askForDateRange(sc);
                    park.rentVehicle(rentPlate, rentRange[0], rentRange[1]);
                    break;
                case 7:
                    System.out.print("Plate number: ");
                    String dropPlate = sc.nextLine();
                    park.dropVehicle(dropPlate);
                    break;
                case 8:
                    System.out.print("Plate number: ");
                    String loadPlate = sc.nextLine();
                    System.out.print("Load amount: ");
                    long loadAmount = Long.parseLong(sc.nextLine());
                    park.loadVehicle(loadPlate, loadAmount);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static LocalDate[] askForDateRange(Scanner sc) {
        System.out.print("Enter start date (yyyy-mm-dd): ");
        LocalDate start = LocalDate.parse(sc.nextLine());
        System.out.print("Enter end date (yyyy-mm-dd): ");
        LocalDate end = LocalDate.parse(sc.nextLine());
        return new LocalDate[]{start, end};
    }

    private static void addVehicleMenu(Scanner sc, VehiclePark park) {
        System.out.print("Enter vehicle type (SUV, SmallTruck, Sport, StationWagon, TransportTruck): ");
        String type = sc.nextLine();

        System.out.print("Plate number: ");
        String plateNumber = sc.nextLine();

        System.out.print("Number of tires: ");
        int numOfTires = Integer.parseInt(sc.nextLine());

        System.out.print("Daily fee: ");
        int dailyFee = Integer.parseInt(sc.nextLine());  // Changed to int for consistency

        Vehicle newVehicle = null;

        switch (type.toLowerCase()) {
            case "suv":
                System.out.print("Color: ");
                String color = sc.nextLine();
                System.out.print("Seating capacity: ");
                int seats = Integer.parseInt(sc.nextLine());
                System.out.print("Doors: ");
                int doors = Integer.parseInt(sc.nextLine());
                System.out.print("Wheel drive (FWD/RWD/4WD/AWD): ");
                String drive = sc.nextLine();
                newVehicle = new SUV(plateNumber, numOfTires, dailyFee, color, seats, doors, drive);
                break;
            case "smalltruck":
                System.out.print("Loading capacity: ");
                long load = Long.parseLong(sc.nextLine()); // Use long
                newVehicle = new SmallTrucks(plateNumber, numOfTires, dailyFee, load);
                break;
            case "sport":
                System.out.print("Color: ");
                String sportColor = sc.nextLine();
                System.out.print("Seating capacity: ");
                int sportSeats = Integer.parseInt(sc.nextLine());
                System.out.print("Doors: ");
                int sportDoors = Integer.parseInt(sc.nextLine());
                System.out.print("Power: ");
                int power = Integer.parseInt(sc.nextLine());
                newVehicle = new Sports(plateNumber, numOfTires, dailyFee, sportColor, sportSeats, sportDoors, power);
                break;
            case "stationwagon":
                System.out.print("Color: ");
                String swColor = sc.nextLine();
                System.out.print("Seating capacity: ");
                int swSeats = Integer.parseInt(sc.nextLine());
                System.out.print("Doors: ");
                int swDoors = Integer.parseInt(sc.nextLine());
                System.out.print("Loading capacity: ");
                long swLoad = Long.parseLong(sc.nextLine());
                newVehicle = new StationWagon(plateNumber, numOfTires, dailyFee, swColor, swSeats, swDoors, swLoad);
                break;
            case "transporttruck":
                System.out.print("Loading capacity: ");
                long tLoad = Long.parseLong(sc.nextLine());  // long here
                System.out.print("Is it abroad? (true/false): ");
                boolean abroad = Boolean.parseBoolean(sc.nextLine());
                newVehicle = new TransportTrucks(plateNumber, numOfTires, dailyFee, tLoad, abroad);
                break;
            default:
                System.out.println("Invalid type.");
                return;
        }

        if (newVehicle != null) {
            park.vehicleList.add(newVehicle);
            System.out.println("Vehicle added.");
        }
    }
}
