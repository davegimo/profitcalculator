package profitcalculatorkata;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static profitcalculatorkata.Currency.GBP;

public class ExchangeRateTest {
    @Test
    void ratio() {
        Currency gbp = new Currency("GBP");
        Currency usd = new Currency("USD");

        ExchangeRates EXCHANGE_RATES = new ExchangeRates(ImmutableMap.<Currency, Double>builder()
                .put(gbp, 1.0)
                .put(usd,2.0)
                .build());

        assertThat(EXCHANGE_RATES.ratio(usd,gbp)).isEqualTo(2.0);
    }

    @Test
    public void invalid_currency() {
        Currency gbp = new Currency("GBP");
        Currency invalidCurrency = new Currency("INVALIDCURRENCY");
        ExchangeRates EXCHANGE_RATES = new ExchangeRates(ImmutableMap.<Currency, Double>builder()
                .put(gbp, 1.0)
                .build());

        assertThrows(Exception.class,
                ()->{ EXCHANGE_RATES.ratio(gbp,invalidCurrency);}
        );


    }
}
