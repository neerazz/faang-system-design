package movieTicketSystem;

/**
 * Created on:  Aug 05, 2021
 * Ref : https://sketchboard.me/fCPjpCZldYw#/
 */
public class Main {
    public static void main(String[] args) {
        PassSystem passSystem = new PassSystem();
        PaymentSystem paymentSystem = new PaymentSystem();
        BookingSystem bookingSystem = new BookingSystem();
        TheaterManager theaterManager = new TheaterManager();
        MovieSystem movieSystem = new MovieSystem(bookingSystem, passSystem, theaterManager, paymentSystem);
    }
}
