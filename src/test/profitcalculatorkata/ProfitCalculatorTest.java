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

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(400, GBP));
        assertThat(tax).isEqualTo(new Money(100, GBP));
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        gbpCalculator.add(new Money(120, GBP), true);
        gbpCalculator.add(new Money(200, GBP), true);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(256, GBP));
        assertThat(tax).isEqualTo(new Money(64, GBP));
    }

    @Test public void
    different_currencies_are_not_taxed() {
        gbpCalculator.add(new Money(120, GBP), true);
        gbpCalculator.add(new Money(200, USD), true);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(221, GBP));
        assertThat(tax).isEqualTo(new Money(24, GBP));
    }

    @Test public void
    handle_outgoings() {
        gbpCalculator.add(new Money(500, GBP), true);
        gbpCalculator.add(new Money(80, USD), true);
        gbpCalculator.add(new Money(360, EUR), false);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(150, GBP));
        assertThat(tax).isEqualTo(new Money(100, GBP));
    }

    @Test public void
    a_negative_balance_results_in_no_tax() {
        gbpCalculator.add(new Money(500, GBP), true);
        gbpCalculator.add(new Money(200, GBP), false);
        gbpCalculator.add(new Money(400, GBP), false);
        gbpCalculator.add(new Money(20, GBP), false);

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(-120, GBP));
        assertThat(tax).isEqualTo(new Money(0, GBP));
    }

    @Test public void
    everything_is_reported_in_the_local_currency() {
        eurCalculator.add(new Money(400, GBP), true);
        eurCalculator.add(new Money(200, USD), false);
        eurCalculator.add(new Money(200, EUR), true);

        Money profit = eurCalculator.calculateProfit();
        Money tax = eurCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(491, EUR));
        assertThat(tax).isEqualTo(new Money(40, EUR));
    }


}
