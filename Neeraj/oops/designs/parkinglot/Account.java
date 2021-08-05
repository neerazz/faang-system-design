package parkinglot;

import java.util.*;
import java.io.*;

public abstract class Account {
    private String userName;
    private String password;
    private AccountStatus status;
    private Person person;
    ParkingLot parkingLot;

    public Account(Person person, ParkingLot parkingLot) {
        this.person = person;
        this.parkingLot = parkingLot;
    }

    public boolean resetPassword() {
        System.out.println("Resetting Password");
        return true;
    }
}

class Admin extends Account {

    public Admin(Person person, ParkingLot parkingLot) {
        super(person, parkingLot);
    }

    public boolean addParkingFloor(ParkingFloor floor) {
        return parkingLot.addParkingFloor(floor);
    }

    public boolean addParkingSpot(String floorName, ParkingSpot spot) {
        return parkingLot.addParkingSpot(floorName, spot);
    }

    public boolean addEntrancePanel(EntryPanel entrancePanel) {
        return parkingLot.addEntrancePanel(entrancePanel);
    }

    public boolean addExitPanel(ExitPanel exitPanel) {
        return parkingLot.addExitPanel(exitPanel);
    }

    public CustomerInfoPanel addCustomerInfoPanel() {
        return new CustomerInfoPanel(parkingLot);
    }
}

class ParkingAttendant extends Account implements TicketProcessor {
    public ParkingAttendant(Person person, ParkingLot parkingLot) {
        super(person, parkingLot);
    }

    public ParkingTicket newTicket(Vehicle vehicle, String floor) {
        return newTicket(parkingLot, vehicle, floor);
    }

    public boolean processTicket(String ticketNumber) {
        return processTicket(parkingLot, ticketNumber);
    }
}

interface TicketProcessor {

    default ParkingTicket newTicket(ParkingLot parkingLot, Vehicle vehicle, String floor) {
        return parkingLot.newTicket(vehicle, floor);
    }

    default ParkingTicket newTicket(ParkingLot parkingLot, Vehicle vehicle) {
        return parkingLot.newTicket(vehicle);
    }

    default boolean processTicket(ParkingLot parkingLot, String ticketNumber) {
        ParkingTicket parkingTicket = getTicket(parkingLot, ticketNumber);
        Double fee = parkingTicket.getFee();
        System.out.println("Kindly pay a Fee of : " + fee);
        if (parkingLot.collectFee(parkingTicket)) {
            return parkingLot.processTicket(parkingTicket);
        }
        return false;
    }

    default ParkingTicket getTicket(ParkingLot parkingLot, String ticketNumber) {
        return parkingLot.getTicket(ticketNumber);
    }
}

class Person {
    String firstName;
    String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

enum AccountStatus {
    ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
}