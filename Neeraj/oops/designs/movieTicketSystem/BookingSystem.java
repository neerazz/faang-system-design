package movieTicketSystem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BookingSystem {

    PaymentSystem paymentSystem;
    Map<String, Ticket> tickets = new HashMap<>();

    public void setPaymentSystem(PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public Ticket bookTicket(String movieName, SeatType seatType, Theater theater, LocalDate date, int showNumber, Transaction transaction) {
        if (theater.hasTickets(movieName, seatType, date, showNumber)) {
            if (paymentSystem.processTransaction(transaction)) {
                Seat seat = theater.reserveSeat(movieName, seatType, date, showNumber);
                if (seat == null) {
                    System.out.println("There was an error Booking Seat. Amount will be returned in 3 days.");
                    paymentSystem.revertTransaction(transaction);
                } else {
                    return createTicket(seat, transaction, theater, date, showNumber);
                }
            }
        }
        System.out.println("No tickets available in Theater : " + theater.theaterId);
        return null;
    }

    private Ticket createTicket(Seat seat, Transaction transaction, Theater theater, LocalDate date, int showNumber) {
        Ticket ticket = new Ticket(seat.seatNumber, transaction.tranId, theater.theaterId, date, showNumber);
        tickets.put(ticket.ticketNumber, ticket);
        return ticket;
    }
}

class Ticket {
    String ticketNumber;
    int seat;
    String transactionId;
    String theaterId;
    LocalDate bookingDate;
    LocalDate showDate;
    int showNumber;

    public Ticket(int seatNumber, String transactionId, String theaterId, LocalDate showDate, int showNumber) {
        this.ticketNumber = UUID.randomUUID().toString();
        this.seat = seatNumber;
        this.transactionId = transactionId;
        this.theaterId = theaterId;
        this.bookingDate = LocalDate.now();
        this.showDate = showDate;
        this.showNumber = showNumber;
    }
}