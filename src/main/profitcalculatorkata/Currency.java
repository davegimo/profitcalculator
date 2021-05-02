package profitcalculatorkata;

import java.text.MessageFormat;

public class Currency {
    public final String value;

    public Currency(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[{0}]", value);
    }
}
