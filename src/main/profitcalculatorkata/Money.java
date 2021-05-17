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


    public Money addValue(Money money) {
        return new Money(this.value + money.value,this.currency);
    }

    public Money getOpposite() {
        return new Money(-this.value,this.currency);
    }

    public Money getRealMoney(Double exchangeRate) {
        return new Money((int) (this.value/exchangeRate), this.currency);
    }

    public boolean isSameCurrency(Money money) {
        return this.currency.equals(money.currency);
    }


}
