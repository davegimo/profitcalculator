package profitcalculatorkata;

public abstract class Item {

    private Money money;

    public Item(Money m) {
        this.money = m;
    }

    public Money amount() {
        return this.money;
    }
}
