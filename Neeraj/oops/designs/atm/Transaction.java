package atm;

import java.time.LocalDateTime;

public class Transaction {
    String accountNumber;
    LocalDateTime createdTime;
    Double amount;
    String bankId;

    public Transaction(String accountNumber, Double amount, String bankId) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.bankId = bankId;
        createdTime = LocalDateTime.now();
    }
}
