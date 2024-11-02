package core;


import core.manager.Command;
import core.manager.Facture;
import core.manager.Product;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static TreeMap<Integer, Product> store = null;
    private static final Random rng;
    private static final Scanner scanner;

    static {
        rng = new Random();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        initializeStore();
        Facture facture = null;
        if (LocalTime.now().isAfter(LocalTime.NOON))
            System.out.println("Good Afternoon sir,");
        else
            System.out.println("Good Morning sir,");
        while (true) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("---------------------------[ MAIN MENU ]-----------------------------");
            System.out.println(" 0. EXIT ");
            System.out.println(" 1. VIEW STORE ");
            System.out.println(" 2. NEW COMMAND ");
            System.out.println(" 3. MY FACTURE ");
            System.out.println("-----------------------[ ENTER YOUR CHOICE ]------------------------");
            int choice = -1;
            while (true) {
                try {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        System.err.println("-----------------------[ EMPTY CHOICE, TRY AGAIN ]------------------------");
                        continue;
                    }
                    if (line.endsWith("\r\n"))
                        line = line.substring(0, line.length() - 2);
                    else if (line.endsWith("\n"))
                        line = line.substring(0, line.length() - 1);
                    choice = Integer.parseInt(line);
                } catch (Throwable ignored) {}
                if (choice < 0 || choice > 3) {
                    System.err.println("-----------------------[ INVALID CHOICE, TRY AGAIN ]------------------------");
                    continue;
                }
                break;
            }
            switch (choice) {
                case 0:
                    System.out.println("-----------------------[ GOOD BYE ]------------------------");
                    System.exit(0);
                case 1:
                    System.out.println("--------------------------------------------");
                    System.out.println(" CODE |        PRODUCT NAME          | PRICE ");
                    for (Product p : store.values()) {
                        System.out.println("--------------------------------------------");
                        System.out.printf("%05d | %s | %d%n", p.getCode(), Command.formatString(p.getName()), p.getPrice());
                    }
                    System.out.println("--------------------------------------------");
                    continue;
                case 2:
                    System.out.println("-----------------------[ ENTER PRODUCT CODE ]------------------------");
                    choice = -1;
                    while (true) {
                        try {
                            String line = scanner.nextLine();
                            if (line.isEmpty()) {
                                System.err.println("-----------------------[ EMPTY CODE, TRY AGAIN ]------------------------");
                                continue;
                            }
                            if (line.endsWith("\r\n"))
                                line = line.substring(0, line.length() - 2);
                            else if (line.endsWith("\n"))
                                line = line.substring(0, line.length() - 1);
                            choice = Integer.parseInt(line);
                        } catch (Throwable ignored) {
                            System.err.println("-----------------------[ INVALID CODE, TRY AGAIN ]------------------------");
                            continue;
                        }
                        if (!store.containsKey(choice)) {
                            System.err.println("-----------------------[ CODE NOT FOUND ]------------------------");
                            choice = -1;
                        }
                        break;
                    }
                    Product p = store.get(choice);
                    if (p == null)
                        continue;
                    System.out.println("-----------------------[ ENTER THE QUANTITY ]------------------------");
                    choice = -1;
                    while (true) {
                        try {
                            String line = scanner.nextLine();
                            if (line.isEmpty()) {
                                System.err.println("-----------------------[ EMPTY INPUT, TRY AGAIN ]------------------------");
                                continue;
                            }
                            if (line.endsWith("\r\n"))
                                line = line.substring(0, line.length() - 2);
                            else if (line.endsWith("\n"))
                                line = line.substring(0, line.length() - 1);
                            choice = Integer.parseInt(line);
                        } catch (Throwable ignored) {
                            System.err.println("-----------------------[ INVALID INPUT, TRY AGAIN ]------------------------");
                            continue;
                        }
                        if (choice < 0){
                            System.err.println("-----------------------[ NEGATIVE INPUT, TRY AGAIN ]------------------------");
                            continue;
                        }
                        break;
                    }
                    if (facture == null)
                        facture = new Facture();
                    facture.addCommand(new Command(p, choice));
                    System.out.println("-----------------------[ NEW COMMAND ADDED. ]------------------------");
                    break;
                case 3:
                    System.out.println("-------------------------------------------------------------------------");
                    if (facture == null) {
                        System.out.println("-----------------------[ YOUR NOT HAVE FACTURE ]------------------------");
                        continue;
                    }
                    facture.printFacture();
                    System.out.println("-------------------------------------------------------------------------");
                    break;
                default:
                    assert false;
            }
        }
    }

    private static void initializeStore() {
        if (store == null) {
            store = new TreeMap<>();
        }
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

        int[] prices = new int[]{
                100, 300, 75, 75, 250, 100, 150, 200,
                35_000, 50_000, 70_000, 85_000, 90_000,  110_000,
                80_000, 90_000, 150_000, 50_000,
                55_000, 60_000, 60_000, 70_000, 85_000, 90_000,
                10_000, 6_000, 10_000, 15_000, 5_000,
                650, 650, 650, 650, 650, 750,
                650, 1100, 650, 900,
                15_000, 15_000, 20_000, 50_000,
        };
        for (int i = 0; i < names.length; i++) {
            int code = rng.nextInt();
            code = ((code >>> 16) ^ code) & 0xFFFF;
            // For unique code
            while (store.containsKey(code)) {
                code = rng.nextInt();
                code = ((code >>> 16) ^ code) & 0xFFFF;
            }
            store.put(code, new Product(code, names[i], prices[i]));
        }
    }
}