package parkinglot;

import java.util.*;
import java.io.*;

public class ParkingSpot {
    String number;
    String licenseNumber;
    boolean free;
    ParkingSpotType parkingSpotType;

    public ParkingSpot(String number, ParkingSpotType parkingSpotType) {
        this.number = number;
        this.parkingSpotType = parkingSpotType;
        free = true;
    }

    void addVehicle(Vehicle vehicle) {
        free = false;
        licenseNumber = vehicle.licenseNumber;
    }

    public void freeSpot() {
        free = true;
        licenseNumber = null;
    }
}

enum ParkingSpotType {
    CAR(2, 1.7, 1.5),
    TRUCK(3, 2.5, 2),
    VAN(2.5, 2, 1.75),
    MOTORCYCLE(1.75, 1.5, 1.25);
    double per1Hour, per2Hour, per3Hour;

    ParkingSpotType(double per1Hour, double per2Hour, double per3Hour) {
        this.per1Hour = per1Hour;
        this.per2Hour = per2Hour;
        this.per3Hour = per3Hour;
    }
}