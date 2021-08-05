package parkinglot;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingDisplayBoard {
    AvailableSlot availableSlot;

    public ParkingDisplayBoard(AvailableSlot availableSlot) {
        this.availableSlot = availableSlot;
    }

    String displayAvailableSlots() {
        return availableSlot.toString();
    }
}

class AvailableSlot {
    Map<ParkingSpotType, Integer> availability = new HashMap<>();

    boolean hasSlot(ParkingSpotType vehicleType) {
        return availability.containsKey(vehicleType) && availability.get(vehicleType) > 0;
    }

    void freeSlot(ParkingSpotType vehicleType) {
        availability.put(vehicleType, availability.getOrDefault(vehicleType, 0) + 1);
    }

    void occupySlot(ParkingSpotType vehicleType) {
        availability.put(vehicleType, availability.getOrDefault(vehicleType, 0) - 1);
    }

    @Override
    public String toString() {
        return availability.entrySet().stream().map(entry -> "\t" + entry.getKey() + " --> " + entry.getValue()).collect(Collectors.joining("\t\t"));
    }

    public void newSlot(ParkingSpotType parkingSpotType) {
        freeSlot(parkingSpotType);
    }

    public void addSlot(ParkingSpotType key, Integer value) {
        availability.put(key, availability.getOrDefault(key, 0) + value);
    }

    public void clear() {
        availability.clear();
    }
}

class CustomerInfoPanel implements TicketProcessor {
    ParkingLot parkingLot;

    public CustomerInfoPanel(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        parkingLot.setCustomerInfoPanel(this);
    }

    public ParkingTicket newTicket(Vehicle vehicle) {
        return newTicket(parkingLot, vehicle);
    }

    public ParkingTicket newTicket(Vehicle vehicle, String floor) {
        return newTicket(parkingLot, vehicle, floor);
    }

    public boolean processTicket(String ticketNumber) {
        return processTicket(parkingLot, ticketNumber);
    }
}