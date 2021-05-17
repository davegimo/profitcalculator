package profitcalculatorkata;

import java.util.Objects;

public class Money {
    public int value;
    public Currency currency;

    public Money(int value, Currency currency) {
        this.currency = currency;
        this.value = value;
    }

    public Money(int value) {
        this.value = value;
    }

    public boolean isNegative() {
        return this.value < 0;
    }

    public Money sum(Money money) {
        return new Money(this.value + money.value,this.currency);
    }

    public Money subtract(Money money) {
        return new Money(this.value - money.value,this.currency);
    }

    public Money getOpposite() {
        return new Money(-this.value,this.currency);
    }

    public Money divideBy(Double divisor) {
        return new Money((int) (this.value/divisor), this.currency);
    }

    public Money multiply(Double value) {
        return new Money((int) (this.value*value), this.currency);
    }

    public boolean isSameCurrency(Money money) {
        return this.currency.equals(money.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
