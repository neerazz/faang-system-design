package libraryManagment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.*;

public class BookItem extends Book {

    String barcode;
    boolean isReferenceOnly;
    Date borrowed;
    Date dueDate;
    double price;
    BookFormat format;
    BookStatus status;
    Date dateOfPurchase;
    Date publicationDate;
    Rack placedAt;

    public BookItem(String barcode, boolean isReferenceOnly) {
        this.barcode = barcode;
        this.isReferenceOnly = isReferenceOnly;
    }

    public boolean checkOut(String memberId, int days) {
        if (isReferenceOnly) {
            System.out.println("Sorry, This Book is ReferenceOnly, cannot be loaned.");
            return false;
        } else if (status != BookStatus.AVAILABLE) {
            System.out.println("Sorry, This Book is not available.");
            return false;
        }
        if (Fine.collectFine(memberId, days) && BookService.executeOrder(new BookLending(barcode, memberId, days))) {
            status = BookStatus.LOANED;
            System.out.println("Loaned book to " + memberId);
            return true;
        }
        System.out.println("Sorry book cannot be loaned at this time.");
        return false;
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", author=" + author +
                '}';
    }
}

class Book {
    String title;
    String subject;
    String publisher;
    String language;
    int numberOfPages;
    Author author;
}

class Rack {
    int number;
    String locationIdentifier;
    boolean hasSpace;

    @Override
    public String toString() {
        return "Rack ='" + locationIdentifier + '\'' +
                ", hasSpace=" + hasSpace;
    }
}

class Author extends Person {

    public Author(String name, Address address, String email, String phone) {
        super(name, address, email, phone);
    }

}