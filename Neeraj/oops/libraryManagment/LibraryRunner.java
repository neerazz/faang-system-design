package libraryManagment;

/**
 * Created on:  Mar 14, 2021
 * Questions:
 */

public class LibraryRunner {

    public static void main(String[] args) {
        Account member1 = AccountManager.createAccount("Member", "Member 1", new Address(), "member1@email.com", "1234567890");
        Account member2 = AccountManager.createAccount("Member", "Member 2", new Address(), "member2@email.com", "67890");
        Account member3 = AccountManager.createAccount("Member", "Member 3", new Address(), "member3@email.com", "12345");
        Account librarian = AccountManager.createAccount("Liberian", "Librian 1", new Address(), "lib1@email.com", "67891334");

        BookItem book1 = new BookItem("1", true);
        BookItem book2 = new BookItem("2", false);
        BookItem book3 = new BookItem("3", false);

        ((Librarian) librarian).addBookItem(book1);
        ((Librarian) librarian).addBookItem(book2);
        ((Librarian) librarian).addBookItem(book3);

        if (((Member) member1).checkOutBook(book1, 12)) {
            ((Member) member1).placeReservation(book1);
        }
        ((Member) member2).checkOutBook(book2, 15);

        System.out.println("********************* Library Status ****************************");
        System.out.println("Total Users      : " + AccountManager.members.keySet());
        System.out.println("Total librarians : " + AccountManager.librarians.keySet());
        System.out.println("Total Racks      : " + BookService.racks);
        System.out.println("Total Books      : " + BookService.booksByBarCode.values());
        System.out.println("Total Lending    : " + BookService.lendings.values());
        System.out.println("Member Fines     : " + BookService.memberFines.values());
        System.out.println("*****************************************************************");
    }
}
