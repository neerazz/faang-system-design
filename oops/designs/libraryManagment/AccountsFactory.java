package libraryManagment;

import java.time.LocalDate;

public class AccountsFactory {

    static int id = 1;

    public static Account createAccount(String accountType, String name, Address address, String email, String phone) {
        Person newPerson = new Person(name, address, email, phone);
        if (accountType.equals("Member")) {
            Member member = new Member();
            member.id = createId(accountType);
            member.person = newPerson;
            member.dateOfMembership = LocalDate.now();
            member.password = "abc";
            member.status = AccountStatus.ACTIVE;
            return member;
        } else {
            Librarian librarian = new Librarian();
            librarian.id = createId(accountType);
            librarian.person = newPerson;
            librarian.dateOfJoining = LocalDate.now();
            librarian.password = "abc";
            librarian.status = AccountStatus.ACTIVE;
            return librarian;
        }
    }

    private static String createId(String accountType) {
        return String.format("%s-5%d", accountType, id++);
    }

}
