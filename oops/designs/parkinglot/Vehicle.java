package parkinglot;

import java.util.*;
import java.io.*;

public abstract class Vehicle {
    String licenseNumber;
    final VehicleType type;

    public Vehicle(String licenseNumber, VehicleType type) {
        this.type = type;
        this.licenseNumber = licenseNumber;
    }

    abstract ParkingSpotType parkingType();

    @Override
    public String toString() {
        return "Vehicle{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", type=" + type +
                '}';
    }
}

class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }

    @Override
    ParkingSpotType parkingType() {
        return ParkingSpotType.CAR;
    }
}

class Van extends Vehicle {
    public Van(String licenseNumber) {
        super(licenseNumber, VehicleType.VAN);
    }

    @Override
    ParkingSpotType parkingType() {
        return ParkingSpotType.VAN;
    }
}

class Truck extends Vehicle {
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }

    @Override
    ParkingSpotType parkingType() {
        return ParkingSpotType.TRUCK;
    }
}

class Motorcycle extends Vehicle {

    public Motorcycle(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTORCYCLE);
    }

    @Override
    ParkingSpotType parkingType() {
        return ParkingSpotType.MOTORCYCLE;
    }
}

enum VehicleType {
    CAR,
    TRUCK,
    VAN,
    MOTORCYCLE;
}
