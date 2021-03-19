package parkinglot;

import java.util.*;

public class ParkingLot {
    String name, address;
    AvailableSlot availableSlot;
    Double earning = 0.0;
    ParkingDisplayBoard parkingDisplayBoard;
    CustomerInfoPanel customerInfoPanel;
    List<EntryPanel> entries = new ArrayList<>();
    List<ExitPanel> exits = new ArrayList<>();
    Map<String, ParkingFloor> parkingFloors = new HashMap<>();
    Map<String, ParkingTicket> tickets = new HashMap<>();

    private static ParkingLot parkingLot = null;

    public static ParkingLot getInstance(String name, String address) {
        if (parkingLot == null) {
            synchronized (ParkingLot.class) {
                if (parkingLot == null) {
                    parkingLot = new ParkingLot(name, address);
                }
            }
        }
        return parkingLot;
    }

    private ParkingLot(String name, String address) {
        this.name = name;
        this.address = address;
        availableSlot = new AvailableSlot();
        parkingDisplayBoard = new ParkingDisplayBoard(availableSlot);
        this.customerInfoPanel = null;
    }

    boolean addParkingFloor(ParkingFloor parkingFloor) {
        parkingFloors.put(parkingFloor.floor, parkingFloor);
        return true;
    }

    public boolean addParkingSpot(String floorName, ParkingSpot spot) {
        if (parkingFloors.containsKey(floorName)) {
            availableSlot.newSlot(spot.parkingSpotType);
            return parkingFloors.get(floorName).addParkingSlot(spot);
        }
        System.out.println("This parking floor doesn't exits, Kindly add it first");
        return false;
    }

    public boolean addEntrancePanel(EntryPanel entrancePanel) {
        return entries.add(entrancePanel);
    }

    public boolean addExitPanel(ExitPanel exitPanel) {
        return exits.add(exitPanel);
    }

    public ParkingTicket newTicket(Vehicle vehicle) {
        if (availableSlot.hasSlot(vehicle.parkingType())) {
            for (String floor : parkingFloors.keySet()) {
                ParkingTicket parkingTicket = newTicket(vehicle, floor);
                if (parkingTicket != null) {
                    return parkingTicket;
                }
            }
        }
        System.out.println("All floors are occupied");
        return null;
    }

    public ParkingTicket newTicket(Vehicle vehicle, String floor) {
        if (parkingFloors.containsKey(floor)) {
            ParkingTicket parkingTicket = parkingFloors.get(floor).newTicket(vehicle);
            if (parkingTicket != null) {
                tickets.put(parkingTicket.ticketNumber, parkingTicket);
                System.out.println("Parking Slot Booked");
                accumulateAllFloorSlots();
                return parkingTicket;
            }
            System.out.println("Unable to Book a parking spot");
            return null;
        }
        System.out.println("This parking floor doesn't exits. Enter a valid Floor !!!");
        return null;
    }

    public ParkingTicket getTicket(String ticketNumber) {
        return tickets.get(ticketNumber);
    }

    public boolean processTicket(ParkingTicket ticket) {
        if (parkingFloors.get(ticket.floor).processTicket(ticket)) {
            collectFee(ticket);
            availableSlot.freeSlot(ticket.parkingType);
            accumulateAllFloorSlots();
        }
        return false;
    }

    public boolean collectFee(ParkingTicket ticket) {
        Double ticketFee = ticket.getFee();
        earning += ticketFee;
        System.out.println("Collecting Fee: " + ticketFee);
        return true;
    }

    public void availableSlots() {
        System.out.println("AvailableSlot in Parking Lot : " + name);
        System.out.println(parkingDisplayBoard.displayAvailableSlots());
    }

    private void accumulateAllFloorSlots() {
        availableSlot.clear();
        for (ParkingFloor floor : parkingFloors.values()) {
            Map<ParkingSpotType, Integer> floorAvailability = floor.availableSlot.availability;
            for (Map.Entry<ParkingSpotType, Integer> entry : floorAvailability.entrySet()) {
                availableSlot.addSlot(entry.getKey(), entry.getValue());
            }
        }
    }

    public void setCustomerInfoPanel(CustomerInfoPanel customerInfoPanel) {
        this.customerInfoPanel = customerInfoPanel;
    }
}