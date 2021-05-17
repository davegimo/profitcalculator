package profitcalculatorkata;

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

    public boolean isSameCurrency(Money money) {
        return this.currency.equals(money.currency);
    }


}
