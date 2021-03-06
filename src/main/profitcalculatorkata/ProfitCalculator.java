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


    public void add(Item item) {
        Double exchangeRate = EXCHANGE_RATES.ratio(item.amount().currency,amountInLocalCurrency.currency);
        Money realMoney = item.amount().divideBy(exchangeRate);
        if (amountInLocalCurrency.isSameCurrency(item.amount())) {
            this.amountInLocalCurrency = this.amountInLocalCurrency.sum(realMoney);
        } else {
            this.amountInForeignCurrencies = this.amountInForeignCurrencies.sum(realMoney);
        }
    }

    public Money calculateProfit() {
        return this.amountInLocalCurrency.subtract(calculateTax()).sum(amountInForeignCurrencies);
    }


    public Money calculateTax() {
        if (this.amountInLocalCurrency.isNegative()) {
            return new Money(0, this.amountInLocalCurrency.currency);
        }
        return (this.amountInLocalCurrency.multiply(0.2));
    }
}
