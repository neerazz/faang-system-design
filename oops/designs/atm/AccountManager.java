package atm;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountManager {

    String bankCode;

    public AccountManager(String bankCode) {
        this.bankCode = bankCode;
    }

    Map<String, Account> accounts = new HashMap<>();
    int counter = 0;

    String createAccount(String firstName, String lastName) {
        String accountNumber = createAccountNumber();
        Account account = new Account(accountNumber, bankCode, 0.0);
        accounts.put(accountNumber, account);
        return accountNumber;
    }

    String createCustomer(String accountNumber, String firstName, String lastName) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            Customer customer = account.createCustomer(firstName, lastName);
            return customer.userId;
        }
        return null;
    }

    boolean createCard(String customerId, String pin) {
        Optional<Customer> customerOptional = accounts.values().stream().map(account -> account.getCustomer(customerId)).filter(customer -> customer != null).findFirst();
        if (customerOptional.isPresent()) {
            return createCard(customerOptional.get().accountNumber, customerId, pin);
        }
        System.out.println("Customer With Cust Id : " + customerId + " not found. Provide a valid customer.");
        return false;
    }

    boolean createCard(String accountNumber, String customerId, String pin) {
        Account account = getAccount(accountNumber);
        if (account == null) return false;
        Customer customer = account.getCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer With Cust Id : " + customerId + " not found in Account : " + accountNumber);
        }
        return customer.assignCard(pin);
    }

    private String createAccountNumber() {
        return "10000000" + counter++;
    }

    private Account getAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            return accounts.get(accountNumber);
        }
        System.out.println("There are no any accounts With this account Number.");
        return null;
    }
}
