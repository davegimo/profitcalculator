package profitcalculatorkata;

import com.google.common.collect.ImmutableMap;

public final class ProfitCalculator {



    private static final ExchangeRates EXCHANGE_RATES = new ExchangeRates(ImmutableMap.<Currency, Double>builder()
            .put(new Currency("GBP"), 1.0)
            .put(new Currency("USD"),1.6)
            .put(new Currency("EUR"), 1.2)
            .build());

    private final Currency localCurrency;
    private int localAmount = 0;
    private int foreignAmount = 0;

    public ProfitCalculator(Currency localCurrency) {
        this.localCurrency = localCurrency;
        EXCHANGE_RATES.isValidCurrency(localCurrency);
    }

    public void add(int amount, Currency currency, boolean incoming) {
        int realAmount = amount;
        Double exchangeRate = EXCHANGE_RATES.ratio(currency,localCurrency);
        realAmount /= exchangeRate;
        if (!incoming) {
            realAmount = -realAmount;
        }
        if (localCurrency.equals(currency)) {
            this.localAmount += realAmount;
        } else {
            this.foreignAmount += realAmount;
        }
    }

    public int calculateProfit() {
        return localAmount - calculateTax() + foreignAmount;
    }

    public int calculateTax() {
        if (localAmount < 0) {
            return 0;
        }

        return (int) (localAmount * 0.2);
    }
}
