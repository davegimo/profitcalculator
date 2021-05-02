package profitcalculatorkata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static profitcalculatorkata.Currency.currency;

public final class ProfitCalculatorTest {
    private final ProfitCalculator gbpCalculator = new ProfitCalculator(currency("GBP"));
    private final ProfitCalculator eurCalculator = new ProfitCalculator(currency("EUR"));

    @Test
    public void
    calculates_the_tax_at_20_percent() {
        gbpCalculator.add(500, currency("GBP"), true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(400);
        assertThat(tax).isEqualTo(100);
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        gbpCalculator.add(120, currency("GBP"), true);
        gbpCalculator.add(200, currency("GBP"), true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(256);
        assertThat(tax).isEqualTo(64);
    }

    @Test public void
    different_currencies_are_not_taxed() {
        gbpCalculator.add(120, currency("GBP"), true);
        gbpCalculator.add(200, currency("USD"), true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(221);
        assertThat(tax).isEqualTo(24);
    }

    @Test public void
    handle_outgoings() {
        gbpCalculator.add(500, currency("GBP"), true);
        gbpCalculator.add(80, currency("USD"), true);
        gbpCalculator.add(360, currency("EUR"), false);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(150);
        assertThat(tax).isEqualTo(100);
    }

    @Test public void
    a_negative_balance_results_in_no_tax() {
        gbpCalculator.add(500, currency("GBP"), true);
        gbpCalculator.add(200, currency("GBP"), false);
        gbpCalculator.add(400, currency("GBP"), false);
        gbpCalculator.add(20, currency("GBP"), false);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(-120);
        assertThat(tax).isEqualTo(0);
    }

    @Test public void
    everything_is_reported_in_the_local_currency() {
        eurCalculator.add(400, currency("GBP"), true);
        eurCalculator.add(200, currency("USD"), false);
        eurCalculator.add(200, currency("EUR"), true);

        int profit = eurCalculator.calculateProfit();
        int tax = eurCalculator.calculateTax();

        assertThat(profit).isEqualTo(491);
        assertThat(tax).isEqualTo(40);
    }
}
