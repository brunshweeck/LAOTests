package core.utility;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

public class Price {
    long amount;
    Currency currency;

    public Price(long amount) {
        this(amount, Currency.getInstance(Locale.getDefault()));
    }

    public Price(long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return DecimalFormat.getIntegerInstance().format(getAmount()) + " " + getCurrency().getSymbol();
    }
}
