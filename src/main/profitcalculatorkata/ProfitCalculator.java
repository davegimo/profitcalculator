package profitcalculatorkata;

import com.google.common.collect.ImmutableMap;

public final class ProfitCalculator {

    private static final ExchangeRates EXCHANGE_RATES = new ExchangeRates(ImmutableMap.<Currency, Double>builder()
            .put(new Currency("GBP"), 1.0)
            .put(new Currency("USD"),1.6)
            .put(new Currency("EUR"), 1.2)
            .build());


    private Money amountInLocalCurrency;
    private Money amountInForeignCurrencies;

    public ProfitCalculator(Currency localCurrency) {
        EXCHANGE_RATES.isValidCurrency(localCurrency);
        this.amountInLocalCurrency = new Money(0,localCurrency);
        this.amountInForeignCurrencies = new Money(0,localCurrency);
    }

    public void add(Money money, boolean incoming) {
        Double exchangeRate = EXCHANGE_RATES.ratio(money.currency,amountInLocalCurrency.currency);
        Money realMoney = money.divideBy(exchangeRate);
        if (!incoming) {
            realMoney = realMoney.getOpposite();
        }
        if (amountInLocalCurrency.isSameCurrency(money)) {
            this.amountInLocalCurrency = this.amountInLocalCurrency.sum(realMoney);
        } else {
            this.amountInForeignCurrencies = this.amountInForeignCurrencies.sum(realMoney);
        }
    }

//    public int calculateProfit() {
//        return this.amountInLocalCurrency.value - calculateTax() + this.amountInForeignCurrencies.value;
//    }

    public int calculateProfit() {
        return this.amountInLocalCurrency.subtract(new Money(calculateTax(), this.amountInLocalCurrency.currency)).sum(new Money(this.amountInForeignCurrencies.value,this.amountInLocalCurrency.currency)).value;
    }

    public int calculateTax() {
        if (this.amountInLocalCurrency.value < 0) {
            return 0;
        }

        return (int) (this.amountInLocalCurrency.value * 0.2);
    }
}
