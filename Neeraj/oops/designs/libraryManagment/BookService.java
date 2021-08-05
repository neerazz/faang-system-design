package libraryManagment;

import java.time.temporal.ChronoUnit;
import java.util.*;

public class BookService {
    static List<Rack> racks = new ArrayList<>();
    static Map<String, BookLending> lendings = new HashMap<>();
    static Map<String, Fine> memberFines = new HashMap<>();
    static Map<String, BookItem> booksByBarCode = new HashMap<>();

    public static boolean addBookItem(BookItem bookItem) {
        booksByBarCode.put(bookItem.barcode, bookItem);
        System.out.println("Adding new Book : " + bookItem);
        return true;
    }

    public static BookItem getBookItem(String barCode) {
        return booksByBarCode.get(barCode);
    }

    public static Rack getAvailableRack() {
        return racks.stream().filter(rack -> rack.hasSpace).findFirst().orElse(createNewRack());
    }

    public static Rack createNewRack() {
        Rack newRack = new Rack();
        newRack.number = racks.size();
        newRack.hasSpace = true;
        newRack.locationIdentifier = "R" + racks.size();
        racks.add(newRack);
        return newRack;
    }

    public static boolean executeOrder(BookLending lending) {
        String barcode = lending.bookItemBarcode;
        lendings.put(barcode, lending);
        return true;
    }

    public static BookLending fetchLendingDetails(String barcode) {
        return lendings.get(barcode);
    }

    public static Fine getFine(String memberId) {
        return memberFines.get(memberId);
    }

    public static boolean addFine(String memberId, Fine fine) {
        memberFines.put(memberId, fine);
        return true;
    }
}
