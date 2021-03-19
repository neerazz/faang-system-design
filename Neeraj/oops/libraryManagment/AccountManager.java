package libraryManagment;

import java.util.*;
import java.io.*;

public class AccountManager {

    static Map<String, Member> members = new HashMap<>();
    static Map<String, Librarian> librarians = new HashMap<>();

    public static Account createAccount(String accountType, String name, Address address, String email, String phone) {
        Account account = AccountsFactory.createAccount(accountType, name, address, email, phone);
        if (accountType.equals("Member")) {
            members.put(account.id, (Member) account);
        } else {
            librarians.put(account.id, (Librarian) account);
        }
        return account;
    }


    public static Librarian getLibrarian(String id) {
        return librarians.get(id);
    }

    public static Librarian getAnyLibrarian() {
        return librarians.values().stream().findAny().orElse(null);
    }

    public static Member getMember(String id) {
        return members.get(id);
    }
}
