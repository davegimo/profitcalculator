package profitcalculatorkata;


import org.junit.jupiter.api.Test;
import static profitcalculatorkata.Currency.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @Test
    void addValue() {

        Money m1 = new Money(2, GBP);
        Money m2 = new Money(2, GBP);

        m1 = m1.sum(m2);

        assertThat(m1).isEqualTo(new Money(4, GBP));
    }
}
