package libraryManagment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.*;

public class BookReservation {
    LocalDate creationDate;
    ReservationStatus status;
    String bookItemBarcode;
    String memberId;

    static Map<String, BookReservation> reservations = new HashMap<>();

    public BookReservation(String barcode, String memberId) {
        creationDate = LocalDate.now();
        status = ReservationStatus.WAITING;
        bookItemBarcode = barcode;
        this.memberId = memberId;
    }

    public static boolean reserve(BookReservation reservation) {
        if (reservations.containsKey(reservation.bookItemBarcode)) {
            System.out.println("There is an another user waiting for this book.");
            return false;
        }
        reservations.put(reservation.bookItemBarcode, reservation);
        return true;
    }

    public static BookReservation fetchReservationDetails(String barcode) {
        return reservations.get(barcode);
    }
}

class BookLending {
    LocalDate creationDate;
    LocalDate dueDate = LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS);
    LocalDate returnDate;
    String bookItemBarcode;
    String memberId;

    public BookLending(String barcode, String memberId, int days) {
        creationDate = LocalDate.now();
        bookItemBarcode = barcode;
        this.memberId = memberId;
        returnDate = creationDate.plusDays(days);
    }

    public static boolean lendBook(String barcode, String memberId, int days) {
        BookReservation reservation = BookReservation.fetchReservationDetails(barcode);
        if (reservation != null && !reservation.memberId.equals(memberId)) {
            System.out.println("There is an another user waiting for this book.");
            return false;
        }
        BookItem bookItem = BookService.getBookItem(barcode);
        return bookItem.checkOut(memberId, days);
    }

    public static BookLending fetchLendingDetails(String barcode) {
        return null;
    }

    @Override
    public String toString() {
        return "BookLending{" +
                "dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", bookItemBarcode='" + bookItemBarcode + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}

class Fine {
    LocalDate creationDate;
    double price;
    String memberId;

    public static Fine getInstance(String memberId) {
        Fine newFine = new Fine();
        newFine.creationDate = LocalDate.now();
        newFine.memberId = memberId;
        newFine.price = 0;
        return newFine;
    }

    public static boolean collectFine(String memberId, long days) {
        if (days <= Constants.MAX_LENDING_DAYS) {
            return true;
        }
        Fine fine = BookService.getFine(memberId);
        if (fine == null) {
            fine = getInstance(memberId);
        }
        fine.price += days * Constants.PER_DAY_FINE;
        return BookService.addFine(memberId, fine);
    }

    @Override
    public String toString() {
        return "Fine{" +
                "creationDate=" + creationDate +
                ", price=" + price +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}