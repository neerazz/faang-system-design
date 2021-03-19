package parkinglot;

import java.util.*;
import java.io.*;

/**
 * Created on:  Mar 18, 2021
 */

public class ParkingSystemRunner {

    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance("ABC Parking", "Florida");
        Admin admin = new Admin(new Person("ABC", ""), parkingLot);
        ParkingAttendant attendant1 = new ParkingAttendant(new Person("CDE", ""), parkingLot);

        CustomerInfoPanel customerInfoPanel = admin.addCustomerInfoPanel();

        EntryPanel entry1 = new EntryPanel(parkingLot);
        EntryPanel entry2 = new EntryPanel(parkingLot);
        admin.addEntrancePanel(entry1);
        admin.addEntrancePanel(entry2);

        ExitPanel exit = new ExitPanel(parkingLot);
        admin.addExitPanel(exit);

        ParkingFloor f1 = new ParkingFloor("1");
        admin.addParkingFloor(f1);

        ParkingSpot s1 = new ParkingSpot("s1", ParkingSpotType.CAR);
        ParkingSpot s2 = new ParkingSpot("s2", ParkingSpotType.VAN);
        ParkingSpot s3 = new ParkingSpot("s3", ParkingSpotType.VAN);
        ParkingSpot s4 = new ParkingSpot("s4", ParkingSpotType.CAR);
        f1.addParkingSlot(s1);
        f1.addParkingSlot(s2);
        f1.addParkingSlot(s3);
        f1.addParkingSlot(s4);

        ParkingFloor f2 = new ParkingFloor("2");
        admin.addParkingFloor(f2);

        ParkingSpot s5 = new ParkingSpot("s5", ParkingSpotType.CAR);
        ParkingSpot s6 = new ParkingSpot("s6", ParkingSpotType.VAN);
        ParkingSpot s7 = new ParkingSpot("s7", ParkingSpotType.VAN);
        ParkingSpot s8 = new ParkingSpot("s8", ParkingSpotType.CAR);
        f2.addParkingSlot(s5);
        f2.addParkingSlot(s6);
        f2.addParkingSlot(s7);
        f2.addParkingSlot(s8);

        Car car1 = new Car("car1");
        ParkingTicket ticket1 = attendant1.newTicket(car1, "1");

        parkingLot.availableSlots();
        f1.availableSlots();
        f2.availableSlots();

        Car car2 = new Car("car2");
        ParkingTicket ticket2 = entry1.newTicket(car2);

        parkingLot.availableSlots();
        f1.availableSlots();
        f2.availableSlots();

        Van van1 = new Van("van1");
        ParkingTicket ticket3 = entry2.newTicket(van1);

        parkingLot.availableSlots();
        f1.availableSlots();
        f2.availableSlots();

        Van van2 = new Van("van2");
        ParkingTicket ticket4 = customerInfoPanel.newTicket(van2);

        parkingLot.availableSlots();
        f1.availableSlots();
        f2.availableSlots();

        exit.processTicket(ticket1.ticketNumber);
        customerInfoPanel.processTicket(ticket2.ticketNumber);
        attendant1.processTicket(ticket3.ticketNumber);

        parkingLot.availableSlots();
        f1.availableSlots();
        f2.availableSlots();
    }
}
