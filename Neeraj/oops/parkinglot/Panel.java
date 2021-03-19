package parkinglot;

import java.util.*;
import java.io.*;

class EntryPanel implements TicketProcessor {

    ParkingLot parkingLot;

    public EntryPanel(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket newTicket(Vehicle vehicle) {
        return newTicket(parkingLot, vehicle);
    }
}

class ExitPanel implements TicketProcessor {
    ParkingLot parkingLot;

    public ExitPanel(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean processTicket(String ticketNumber) {
        return processTicket(parkingLot, ticketNumber);
    }
}