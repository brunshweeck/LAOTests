package core.manager;

import java.util.Locale;

public class Command {
    private final Product product;
    private final int quantity;

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

    public int getUnityPrice() {
        return product.getPrice();
    }

    public int getTotalPrice() {
        return product.getPrice() * getQuantity();
    }

    public int getCode() {
        return product.getCode();
    }

    public String getDesignation() {
        return product.getName();
    }

    public String toString() {
        return String.format(Locale.FRENCH, "%05d | %s | %d | %02d | %d ", getCode(), formatString(getDesignation()), getUnityPrice(), getQuantity(), getTotalPrice());
    }

    public static String formatString(String s) {
        int length = s.length();
        if (length < 30) {
            String spaces = " ".repeat((30 - length) >> 1);
            return spaces + s + spaces;
        }
        return s;
    }
}
