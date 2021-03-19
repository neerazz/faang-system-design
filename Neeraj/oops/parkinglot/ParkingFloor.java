package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    String floor;
    AvailableSlot availableSlot;
    ParkingDisplayBoard parkingDisplayBoard;
    List<ParkingSpot> moterCycleSpots = new ArrayList<>();
    List<ParkingSpot> carSpots = new ArrayList<>();
    List<ParkingSpot> vanSpots = new ArrayList<>();
    List<ParkingSpot> truckSpots = new ArrayList<>();

    public ParkingFloor(String floor) {
        this.floor = floor;
        availableSlot = new AvailableSlot();
        parkingDisplayBoard = new ParkingDisplayBoard(availableSlot);
    }

    public boolean hasFreeSpot(ParkingSpotType parkingSpotType) {
        return availableSlot.hasSlot(parkingSpotType);
    }

    public boolean addParkingSlot(ParkingSpot spot) {
        switch (spot.parkingSpotType) {
            case CAR -> carSpots.add(spot);
            case TRUCK -> truckSpots.add(spot);
            case VAN -> vanSpots.add(spot);
            case MOTORCYCLE -> moterCycleSpots.add(spot);
            default -> {
                System.out.println("Invalid Parking Spot type");
                return false;
            }
        }
        availableSlot.newSlot(spot.parkingSpotType);
        return true;
    }

    public void freeSpot(ParkingSpot spot) {
        spot.freeSpot();
        availableSlot.freeSlot(spot.parkingSpotType);
    }

    public void occupySlot(ParkingSpot spot, Vehicle vehicle) {
        spot.addVehicle(vehicle);
        availableSlot.occupySlot(spot.parkingSpotType);
    }

    ParkingSpot getFreeSlot(ParkingSpotType parkingSpotType) {
        return switch (parkingSpotType) {
            case CAR -> getFreeCarSlot();
            case TRUCK -> getFreeTruckSlot();
            case VAN -> getFreeVanSlot();
            case MOTORCYCLE -> getFreeMotorCycleSlot();
        };
    }

    private ParkingSpot getFreeMotorCycleSlot() {
        return moterCycleSpots.stream().filter(spot -> spot.free).findFirst().orElse(null);
    }

    private ParkingSpot getFreeVanSlot() {
        return vanSpots.stream().filter(spot -> spot.free).findFirst().orElse(null);
    }

    private ParkingSpot getFreeTruckSlot() {
        return truckSpots.stream().filter(spot -> spot.free).findFirst().orElse(null);
    }

    private ParkingSpot getFreeCarSlot() {
        return carSpots.stream().filter(spot -> spot.free).findFirst().orElse(null);
    }

    public ParkingTicket newTicket(Vehicle vehicle) {
        if (availableSlot.hasSlot(vehicle.parkingType())) {
            ParkingTicket parkingTicket = new ParkingTicket(vehicle, floor);
            ParkingSpot parkingSpot = getFreeSlot(vehicle.parkingType());
            occupySlot(parkingSpot, vehicle);
            parkingTicket.printTicket();
            return parkingTicket;
        }
        System.out.println("Sorry, There are not available slots in this Floor.");
        return null;
    }

    public boolean processTicket(ParkingTicket ticket) {
        ParkingSpot parkingSpot = getSport(ticket.parkingType, ticket.licenseNumber);
        if (parkingSpot != null) {
            ticket.status = ParkingTicketStatus.PAID;
            parkingSpot.freeSpot();
            return true;
        }
        return false;
    }

    private ParkingSpot getSport(ParkingSpotType parkingType, String licenseNumber) {
        return switch (parkingType) {
            case CAR -> getCarSlot(licenseNumber);
            case TRUCK -> getTruckSlot(licenseNumber);
            case VAN -> getVanSlot(licenseNumber);
            case MOTORCYCLE -> getMotorCycleSlot(licenseNumber);
        };
    }

    private ParkingSpot getMotorCycleSlot(String licenseNumber) {
        return moterCycleSpots.stream().filter(spot -> licenseNumber.equals(spot.licenseNumber)).findFirst().orElse(null);
    }

    private ParkingSpot getVanSlot(String licenseNumber) {
        return vanSpots.stream().filter(spot -> licenseNumber.equals(spot.licenseNumber)).findFirst().orElse(null);
    }

    private ParkingSpot getTruckSlot(String licenseNumber) {
        return truckSpots.stream().filter(spot -> licenseNumber.equals(spot.licenseNumber)).findFirst().orElse(null);
    }

    private ParkingSpot getCarSlot(String licenseNumber) {
        return carSpots.stream().filter(spot -> licenseNumber.equals(spot.licenseNumber)).findFirst().orElse(null);
    }

    public void availableSlots() {
        System.out.println("AvailableSlot in Floor : " + floor);
        System.out.println(parkingDisplayBoard.displayAvailableSlots());
    }
}