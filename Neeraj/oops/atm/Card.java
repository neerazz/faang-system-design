package atm;

import java.time.LocalDate;

class Card {
    Long cardNumber;
    String customerName;
    String pin;
    LocalDate expiry;

    public Card(Long cardNumber, String customerName, String pin, LocalDate expiry) {
        this.cardNumber = cardNumber;
        this.customerName = customerName;
        this.pin = pin;
        this.expiry = expiry;
    }
}
