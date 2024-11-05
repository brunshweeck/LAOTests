package core.store;

import core.utility.Price;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private final int code;
    private final String name;
    private final Price price;

    private static final AtomicInteger codeGenerator = new AtomicInteger(0);

    public Product(String name, Price price) {
        this(codeGenerator.incrementAndGet(), name, price);
    }

    public Product(int code, String name, Price price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public void print() {
        NumberFormat format = DecimalFormat.getIntegerInstance();
        System.out.printf(" %s | %s | %s %n",
                Command.formatString(format.format(getCode()), 10),
                Command.formatString(getName(), 30),
                Command.formatString(getPrice().toString(), 20)
        );
    }

    private static Map<Integer, Product> store;

    public static Map<Integer, Product> getStore() {
        if (store == null) {
            store = new TreeMap<>();
            String[] names = new String[]{
                    "BANANA", "PINEAPPLE", "ORANGE", "LEMON", "COCOA", "PEANUT", "BEAK", "APPLE",
                    "IPHONE 6", "IPHONE 6+", "IPHONE 6S+", "IPHONE 7", "IPHONE X", "IPHONE XR",
                    "SAMSUNG A6", "SAMSUNG A10", "SAMSUNG A20", "SAMSUNG GALAXY",
                    "ITEL P32", "ITEL P33", "ITEL P50", "ITEL P55", "ITEL P60", "ITEL P70",
                    "ADIDAS SHOES", "ALL START SHOES", "AIR DANCE SHOES", "AIR FORCE SHOES", "NIKE SHOES",
                    "33 EXPORT BEER", "KADJI BEER", "MUZIK BEER", "CASTEL BEER", "DOPEL BEER", "ISEMBEC BEER",
                    "GUINNESS BEER", "GUINNESS BIG BEER", "SMOOTH BEER", "SMOOTH BIG BEER",
                    "RED LABEL WHISKY", "BLACK LABEL WHISKY", "MARTINI WHISKY", "BLUE LABEL WHISKY",
            };

            long[] prices = new long[]{
                    100, 300, 75, 75, 250, 100, 150, 200,
                    35_000, 50_000, 70_000, 85_000, 90_000,  110_000,
                    80_000, 90_000, 150_000, 50_000,
                    55_000, 60_000, 60_000, 70_000, 85_000, 90_000,
                    10_000, 6_000, 10_000, 15_000, 5_000,
                    650, 650, 650, 650, 650, 750,
                    650, 1100, 650, 900,
                    15_000, 15_000, 20_000, 50_000,
            };
            Locale.setDefault(Locale.forLanguageTag("fr-CM"));
            for (int i = 0; i < names.length; i++) {
                Product product = new Product(names[i], new Price(prices[i]));
                store.put(product.getCode(), product);
            }
        }
        return store;
    }
}
