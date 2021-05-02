package profitcalculatorkata;

import java.text.MessageFormat;
import java.util.Objects;

public class Currency {
    public final String value;

    public Currency(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[{0}]", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return value.equals(currency.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
