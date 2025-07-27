package libraryManagment;

import java.time.LocalDate;

class Person {

    String name;
    Address address;
    String email;
    String phone;

    public Person(String name, Address address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}

public abstract class Account {
    String id;
    String password;
    AccountStatus status;
    Person person;

    public boolean resetPassword() {
        if (status == AccountStatus.BLACKLISTED) {
            System.out.println("Sorry, You cannot reset your account. It is Closed.");
            return false;
        }
        System.out.println("Resetting Password : " + person.name);
        return true;
    }
}

class Member extends Account {
    LocalDate dateOfMembership;
    int totalBooks;

    public boolean checkOutBook(BookItem bookItem, int days) {
        if (status != AccountStatus.ACTIVE) {
            System.out.println("Sorry, You cannot take the book. Your account is not active.");
            return false;
        }
        if (totalBooks >= Constants.MAX_BOOKS_ISSUED_TO_A_USER) {
            System.out.println("Sorry, You cannot take the book, you have took maximum allowed books.");
            return false;
        }
        return BookLending.lendBook(bookItem.barcode, id, days);
    }

    public boolean placeReservation(BookItem bookItem) {
        if (status != AccountStatus.ACTIVE) {
            System.out.println("Sorry, You cannot reserve this book. YOur account is not active.");
            return false;
        }
        BookReservation reservation = new BookReservation(bookItem.barcode, id);
//        return BookService.reserve(reservation);
        return true;
    }
}

class Librarian extends Account {

    LocalDate dateOfJoining;

    public boolean addBookItem(BookItem bookItem) {
        bookItem.placedAt = BookService.getAvailableRack();
        bookItem.status = BookStatus.AVAILABLE;
        System.out.println("Placing book Item : " + bookItem.barcode + " at Rack : " + bookItem.placedAt);
        return BookService.addBookItem(bookItem);
    }

    public boolean blockMember(String memberId) {
        Member member = AccountManager.getMember(memberId);
        if (member == null) {
            System.out.println("Invalid, member Id : " + memberId);
            return false;
        } else if (member.status != AccountStatus.ACTIVE) {
            System.out.println("Invalid, action, Member is not active." + memberId);
            return false;
        }
        member.status = AccountStatus.BLACKLISTED;
        return true;
    }

    public boolean unBlockMember(String memberId) {
        Member member = AccountManager.getMember(memberId);
        if (member == null) {
            System.out.println("Invalid, member Id : " + memberId);
            return false;
        } else if (member.status == AccountStatus.ACTIVE) {
            System.out.println("Invalid, action, Member is active." + memberId);
            return false;
        }
        member.status = AccountStatus.BLACKLISTED;
        return member.resetPassword();
    }
}