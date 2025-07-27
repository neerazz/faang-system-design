package atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
    String accountNumber;
    Double balance;
    String bankCode;
    List<Transaction> transactions;
    Map<String, Customer> customers;

    public Account(String accountNumber, String bankCode, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankCode = bankCode;
        transactions = new ArrayList<>();
        customers = new HashMap<>();
    }

    void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        balance += transaction.amount;
    }

    Customer createCustomer(String firstName, String lastName) {
        String custId = UserIdFactory.getInstance(bankCode).getUserId(firstName);
        Customer customer = new Customer(accountNumber, custId, firstName, lastName);
        customers.put(custId, customer);
        return customer;
    }

    boolean createCard(String custId, String pin) {
        Customer customer = getCustomer(custId);
        if (customer != null) {
            return customer.assignCard(pin);
        }
        System.out.println("Customer With Cust Id : " + custId + " not found in Account : " + accountNumber);
        return false;
    }

    public Customer getCustomer(String custId) {
        return customers.get(custId);
    }
}
