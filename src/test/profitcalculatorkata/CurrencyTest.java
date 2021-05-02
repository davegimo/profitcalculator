package profitcalculatorkata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyTest {
    @Test
    void equality() {
        assertThat(new Currency("USD")).isEqualTo(new Currency("USD"));
        assertThat(new Currency("USD")).isNotEqualTo(new Currency("EUR"));
    }

    @Test
    void asString() {
        Currency currency = new Currency("USD");

        assertThat(currency.toString()).contains("USD");
    }
}
