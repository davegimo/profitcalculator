package profitcalculatorkata;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public final class ProfitCalculator {
    private static final Map<Currency, Double> EXCHANGE_RATES = ImmutableMap.<Currency, Double>builder()
            .put(new Currency("GBP"), 1.0)
            .put(new Currency("USD"), 1.6)
            .put(new Currency("EUR"), 1.2)
            .build();

    private final Currency localCurrency;
    private int localAmount = 0;
    private int foreignAmount = 0;

    public ProfitCalculator(Currency localCurrency) {
        this.localCurrency = localCurrency;
        Double exchangeRate = EXCHANGE_RATES.get(localCurrency);
        if (exchangeRate == null) {
            throw new IllegalArgumentException("Invalid currency.");
        }
    }

    public void add(int amount, Currency currency, boolean incoming) {
        int realAmount = amount;
        Double exchangeRate = EXCHANGE_RATES.get(currency) / EXCHANGE_RATES.get(localCurrency);
        if (exchangeRate != null) {
            realAmount /= exchangeRate;
        }
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
