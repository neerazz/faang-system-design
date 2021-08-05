package movieTicketSystem;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentSystem {

    PassSystem passSystem;

    public void setPassSystem(PassSystem passSystem) {
        this.passSystem = passSystem;
    }

    public boolean processTransaction(Transaction transaction) {
        return false;
    }

    public void revertTransaction(Transaction transaction) {

    }
}

class Transaction {
    Double amount;
    LocalDateTime createTime;
    TransactionStatus status;
    String tranId;

    public Transaction(Double amount) {
        this.amount = amount;
        createTime = LocalDateTime.now();
        status = TransactionStatus.INITIATED;
        tranId = UUID.randomUUID().toString();
    }
}

enum TransactionStatus {
    INITIATED, COMPLETED, REJECTED, FAILED
}