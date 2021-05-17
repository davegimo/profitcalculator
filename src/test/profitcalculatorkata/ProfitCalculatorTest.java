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
        Money money = new Money(500, GBP);
        gbpCalculator.add(new Incoming(money));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(400, GBP));
        assertThat(tax).isEqualTo(new Money(100, GBP));
    }

    @Test public void
    calculates_the_tax_of_multiple_amounts() {
        Money money1 = new Money(120, GBP);
        gbpCalculator.add(new Incoming(money1));
        Money money = new Money(200, GBP);
        gbpCalculator.add(new Incoming(money));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(256, GBP));
        assertThat(tax).isEqualTo(new Money(64, GBP));
    }

    @Test public void
    different_currencies_are_not_taxed() {
        Money money1 = new Money(120, GBP);
        gbpCalculator.add(new Incoming(money1));
        Money money = new Money(200, USD);
        gbpCalculator.add(new Incoming(money));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(221, GBP));
        assertThat(tax).isEqualTo(new Money(24, GBP));
    }

    @Test public void
    handle_outgoings() {
        Money money2 = new Money(500, GBP);
        gbpCalculator.add(new Incoming(money2));
        Money money1 = new Money(80, USD);
        gbpCalculator.add(new Incoming(money1));
        Money money = new Money(360, EUR);
        gbpCalculator.add(new Outgoing(money));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(150, GBP));
        assertThat(tax).isEqualTo(new Money(100, GBP));
    }

    @Test public void
    a_negative_balance_results_in_no_tax() {
        Money money3 = new Money(500, GBP);
        gbpCalculator.add(new Incoming(money3));
        Money money2 = new Money(200, GBP);
        gbpCalculator.add(new Outgoing(money2));
        Money money1 = new Money(400, GBP);
        gbpCalculator.add(new Outgoing(money1));
        Money money = new Money(20, GBP);
        gbpCalculator.add(new Outgoing(money));

        Money profit = gbpCalculator.calculateProfit();
        Money tax = gbpCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(-120, GBP));
        assertThat(tax).isEqualTo(new Money(0, GBP));
    }

    @Test public void
    everything_is_reported_in_the_local_currency() {
        Money money2 = new Money(400, GBP);
        eurCalculator.add(new Incoming(money2));
        Money money1 = new Money(200, USD);
        eurCalculator.add(new Outgoing(money1));
        Money money = new Money(200, EUR);
        eurCalculator.add(new Incoming(money));

        Money profit = eurCalculator.calculateProfit();
        Money tax = eurCalculator.calculateTax();

        assertThat(profit).isEqualTo(new Money(491, EUR));
        assertThat(tax).isEqualTo(new Money(40, EUR));
    }


}
