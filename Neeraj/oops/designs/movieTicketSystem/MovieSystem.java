package movieTicketSystem;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieSystem {

    Map<String, Theater> theaters;
    Map<String, Movie> movies;
    Map<String, List<Theater>> movieTheaters;
    BookingSystem bookingSystem;
    PassSystem passSystem;
    TheaterManager theaterManager;
    PaymentSystem paymentSystem;

    public MovieSystem(BookingSystem bookingSystem, PassSystem passSystem, TheaterManager theaterManager, PaymentSystem paymentSystem) {
        this.bookingSystem = bookingSystem;
        this.passSystem = passSystem;
        this.theaterManager = theaterManager;
        this.paymentSystem = paymentSystem;
        theaterManager.setMovieSystem(this);
        paymentSystem.setPassSystem(passSystem);
        bookingSystem.setPaymentSystem(paymentSystem);
    }

    List<Movie> searchMovie(String movieName) {
        return movies.entrySet().stream().filter(entry -> entry.getKey().contains(movieName)).map(entry -> entry.getValue()).collect(Collectors.toList());
    }

    List<Theater> getTheaters(Movie movie) {
        return movieTheaters.get(movie.name);
    }

    Ticket bookTicket(String movieName, String theaterId, SeatType seatType, LocalDate date, int showNumber) {
        if (validMovieAndTheater(movieName, theaterId)) {
            Transaction transaction = new Transaction(seatType.price);
            return bookingSystem.bookTicket(movieName, seatType, theaters.get(theaterId), date, showNumber, transaction);
        }
        System.out.println("Invalid Input");
        return null;
    }

    private boolean validMovieAndTheater(String movieName, String theaterId) {
        if (!theaters.containsKey(theaterId)) {
            System.out.println("Invalid Theater Name : " + theaterId);
            return false;
        }
        if (!movies.containsKey(movieName)) {
            System.out.println("Invalid Movie Name : " + movieName);
            return false;
        }
        return true;
    }

}
