package profitcalculatorkata;

import java.util.Map;

public class ExchangeRates {
    public Map<Currency,Double> values;

    public ExchangeRates(Map<Currency,Double> value) {
        this.values = value;
    }

    public ExchangeRates() {
    }

    public void isValidCurrency(Currency currency) {
        if (!this.values.containsKey(currency)) throw new IllegalArgumentException("Invalid Currency "+ currency.toString());
    }

    public Double ratio(Currency c1, Currency c2) {
        isValidCurrency(c1);
        isValidCurrency(c2);
        return this.values.get(c1) / this.values.get(c2);
    }


}
