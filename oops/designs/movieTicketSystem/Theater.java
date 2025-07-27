package movieTicketSystem;

import java.time.LocalDate;
import java.util.*;

public class Theater {
    String theaterId;
    List<PlayingMovie> moviesSchedule;
    Map<SeatType, Integer> seatsCount;
    Set<Integer> shows;
    Map<LocalDate, Map<Integer, Map<SeatType, Integer>>> booked;

    public boolean hasTickets(String movieName, SeatType seatType, LocalDate date, int showNumber) {
        if (!shows.contains(showNumber)) {
            System.out.println("Invalid Show Number. Please enter a valid Show number from the List : " + shows);
            return false;
        }
        Optional<PlayingMovie> matching = moviesSchedule.stream().filter(mov -> mov.movie.name.equalsIgnoreCase(movieName)).filter(mov -> mov.inRange(date)).findFirst();
        if (matching.isEmpty()) {
            System.out.println("This theater don't have the move on teh given date.");
            return false;
        }
        int bookedSeats = bookedSeats(date, seatType, showNumber);
        int availableSeats = availableSeats(seatType);
        return availableSeats > bookedSeats;
    }

    private int availableSeats(SeatType seatType) {
        return seatsCount.getOrDefault(seatType, 0);
    }

    private int bookedSeats(LocalDate date, SeatType seatType, int showNumber) {
        if (booked.containsKey(date) && booked.get(date).containsKey(showNumber) && booked.get(date).get(showNumber).containsKey(seatType)) {
            return booked.get(date).get(showNumber).get(seatType);
        }
        return 0;
    }

    public Seat reserveSeat(String movieName, SeatType seatType, LocalDate date, int showNumber) {
        if (hasTickets(movieName, seatType, date, showNumber)) {
            int bookedSeatNumber = bookSeat(date, showNumber, seatType);
            int availableSeats = availableSeats(seatType);
            if (bookedSeatNumber > availableSeats) return null;
            return new Seat(theaterId, bookedSeatNumber);
        }
        return null;
    }

    private int bookSeat(LocalDate date, int showNumber, SeatType seatType) {
        Map<SeatType, Integer> seatTypeMap = booked.computeIfAbsent(date, val -> new HashMap<>()).computeIfAbsent(showNumber, val -> new HashMap<>());
        Integer booked = seatTypeMap.getOrDefault(seatType, 0);
        seatTypeMap.put(seatType, booked + 1);
        return booked + 1;
    }
}

class Seat {
    String theaterID;
    int seatNumber;

    public Seat(String theaterID, int seatNumber) {
        this.theaterID = theaterID;
        this.seatNumber = seatNumber;
    }
}

enum SeatType {
    SLEEPER(10.0), SEMI_SLEEPER(7.5), CHAIR(5.0);
    Double price;

    SeatType(Double price) {
        this.price = price;
    }
}