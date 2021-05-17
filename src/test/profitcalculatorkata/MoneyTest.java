package profitcalculatorkata;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @Test
    void addValue() {

        Money m1 = new Money(2);
        Money m2 = new Money(2);

        m1 = m1.addValue(m2);

        assertThat(m1.value).isEqualTo(4);
    }
}
