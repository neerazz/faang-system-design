package libraryManagment;

enum BookFormat {
    HARDCOVER,
    PAPERBACK,
    AUDIO_BOOK,
    EBOOK,
    NEWSPAPER,
    MAGAZINE,
    JOURNAL
}

enum BookStatus {
    AVAILABLE,
    RESERVED,
    LOANED,
    LOST
}

enum ReservationStatus {
    WAITING,
    PENDING,
    CANCELED,
    NONE
}

enum AccountStatus {
    ACTIVE,
    CLOSED,
    CANCELED,
    BLACKLISTED,
    NONE
}

public class Constants {
    public static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;
    public static final int MAX_LENDING_DAYS = 10;
    public static final double PER_DAY_FINE = 0.5;
}
