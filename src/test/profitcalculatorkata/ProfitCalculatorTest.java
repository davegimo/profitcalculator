package profitcalculatorkata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static profitcalculatorkata.Currency.*;

public final class ProfitCalculatorTest {
    private final ProfitCalculator gbpCalculator = new ProfitCalculator(GBP);
    private final ProfitCalculator eurCalculator = new ProfitCalculator(EUR);

    @Test
    public void
    calculates_the_tax_at_20_percent() {
        gbpCalculator.add(new Money(500, GBP), true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax().value;

        assertThat(profit).isEqualTo(400);
        assertThat(tax).isEqualTo(100);
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        gbpCalculator.add(new Money(120, GBP), true);
        gbpCalculator.add(new Money(200, GBP), true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax().value;

        assertThat(profit).isEqualTo(256);
        assertThat(tax).isEqualTo(64);
    }

    @Test public void
    different_currencies_are_not_taxed() {
        gbpCalculator.add(new Money(120, GBP), true);
        gbpCalculator.add(new Money(200, USD), true);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax().value;

        assertThat(profit).isEqualTo(221);
        assertThat(tax).isEqualTo(24);
    }

    @Test public void
    handle_outgoings() {
        gbpCalculator.add(new Money(500, GBP), true);
        gbpCalculator.add(new Money(80, USD), true);
        gbpCalculator.add(new Money(360, EUR), false);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax().value;

        assertThat(profit).isEqualTo(150);
        assertThat(tax).isEqualTo(100);
    }

    @Test public void
    a_negative_balance_results_in_no_tax() {
        gbpCalculator.add(new Money(500, GBP), true);
        gbpCalculator.add(new Money(200, GBP), false);
        gbpCalculator.add(new Money(400, GBP), false);
        gbpCalculator.add(new Money(20, GBP), false);

        int profit = gbpCalculator.calculateProfit();
        int tax = gbpCalculator.calculateTax().value;

        assertThat(profit).isEqualTo(-120);
        assertThat(tax).isEqualTo(0);
    }

    @Test public void
    everything_is_reported_in_the_local_currency() {
        eurCalculator.add(new Money(400, GBP), true);
        eurCalculator.add(new Money(200, USD), false);
        eurCalculator.add(new Money(200, EUR), true);

        int profit = eurCalculator.calculateProfit();
        int tax = eurCalculator.calculateTax().value;

        assertThat(profit).isEqualTo(491);
        assertThat(tax).isEqualTo(40);
    }


}
