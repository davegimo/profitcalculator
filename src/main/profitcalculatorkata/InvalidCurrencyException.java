package profitcalculatorkata;

public class InvalidCurrencyException extends Exception {
    private static final String DEFAULT_MESSAGE = "INVALID CURRENCY";

    public InvalidCurrencyException() {
        super(DEFAULT_MESSAGE);
    }
    public InvalidCurrencyException(String msg) {
        super(msg);
    }
}
