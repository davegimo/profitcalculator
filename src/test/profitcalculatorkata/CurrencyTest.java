package profitcalculatorkata;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyTest {
    @Test
    @Disabled
    void equality() {
        assertThat(new Currency("USD")).isEqualTo(new Currency("USD"));
    }

    @Test
    void asString() {
        Currency currency = new Currency("USD");

        assertThat(currency.toString()).contains("USD");
    }
}
