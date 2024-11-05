package core.store;

import core.utility.Price;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Command {
    private final Product product;
    private int quantity;

    public Command(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public Price getUnityPrice() {
        return product.getPrice();
    }

    public Price getTotalPrice() {
        return new Price(product.getPrice().getAmount() * getQuantity(), getProduct().getPrice().getCurrency());
    }

    public long getCode() {
        return getProduct().getCode();
    }

    public String getDesignation() {
        return getProduct().getName();
    }

    public String toString() {
        NumberFormat format = DecimalFormat.getIntegerInstance();
        return String.format(Locale.FRENCH, " %s | %s | %s | %s | %s ",
                formatString(format.format(getCode()), 10),
                formatString(getDesignation(), 30),
                formatString(getUnityPrice().toString(), 20),
                formatString(format.format(getQuantity()), 10),
                formatString(getTotalPrice().toString(), 20)
        );
    }

    public static String formatString(String s, int limit) {
        int length = s.length();
        if (length < limit) {
            String spaces = " ".repeat((limit - length) >> 1);
            return spaces + s + spaces;
        }
        return s;
    }
}
