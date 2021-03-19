package parkinglot;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.*;

public class ParkingTicket {
    String ticketNumber;
    String licenseNumber;
    ParkingSpotType parkingType;
    String floor;
    LocalDateTime startTime;
    Vehicle vehicle;
    ParkingTicketStatus status;

    public ParkingTicket(Vehicle vehicle, String floor) {
        ticketNumber = "F" + floor + "-" + Instant.now().toEpochMilli();
        this.licenseNumber = vehicle.licenseNumber;
        this.parkingType = vehicle.parkingType();
        this.floor = floor;
        startTime = LocalDateTime.now();
        this.vehicle = vehicle;
        status = ParkingTicketStatus.ACTIVE;
    }

    void printTicket() {
        System.out.println(this.toString());
    }

    Double getFee() {
        long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), startTime);
        return ParkingRate.getFare(parkingType, hours);
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "ticketNumber='" + ticketNumber + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", floor='" + floor + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}

enum ParkingTicketStatus {
    ACTIVE, PAID, LOST
}