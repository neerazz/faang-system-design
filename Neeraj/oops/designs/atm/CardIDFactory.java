package atm;

public class CardIDFactory {

    static int counter = 0;
    private static CardIDFactory instance = null;

    private CardIDFactory() {
    }

    public static CardIDFactory getInstance() {
        if (instance == null) instance = new CardIDFactory();
        return instance;
    }

    public long getCardId(String accountNumber, String firstName) {
        return accountNumber.hashCode() + firstName.hashCode() + ++counter;
    }
}
