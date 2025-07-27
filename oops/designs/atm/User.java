package atm;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class User {
    String userId;
    String firstName, lastName;

    public User(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

class Customer extends User {

    Card card;
    String accountNumber;

    public Customer(String userId, String firstName, String lastName, String accountNumber) {
        super(userId, firstName, lastName);
        this.accountNumber = accountNumber;
    }

    boolean assignCard(String pin) {
        if (card != null) {
            System.out.println("Customer : " + userId + " Already has a card :" + card.cardNumber + " assigned to him.");
            return false;
        } else {
            long cardNumber = CardIDFactory.getInstance().getCardId(accountNumber, firstName);
            card = new Card(cardNumber, firstName, pin, LocalDate.now().plusYears(5));
            return true;
        }
    }
}

class ATMMaintainer extends User {
    LocalDateTime joiningDate;

    public ATMMaintainer(String userId, String firstName, String lastName) {
        super(userId, firstName, lastName);
        joiningDate = LocalDateTime.now();
    }
}