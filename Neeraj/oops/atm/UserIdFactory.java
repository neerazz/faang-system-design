package atm;

public class UserIdFactory {

    String bankCode;
    int counter = 0;
    private static UserIdFactory instance = null;

    private UserIdFactory(String bankCode) {
        this.bankCode = bankCode;
    }

    public static UserIdFactory getInstance(String bankCode) {
        if (instance == null) instance = new UserIdFactory(bankCode);
        return instance;
    }

    public String getUserId(String firstName) {
        return bankCode + firstName + ++counter;
    }
}
