package core;

import core.store.Bill;
import core.store.Command;
import core.store.Product;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String r = "-".repeat(120);

    public static void main(String[] args) {
        Bill facture = null;
        var store = Product.getStore();
        if (LocalTime.now().isAfter(LocalTime.NOON))
            System.out.println("Good Afternoon sir,");
        else
            System.out.println("Good Morning sir,");
        while (true) {
            System.out.println(r);
            System.out.println(Command.formatString("[ MAIN MENU ]", 120));
            System.out.println(" 0. EXIT ");
            System.out.println(" 1. VIEW STORE ");
            System.out.println(" 2. NEW COMMAND ");
            System.out.println(" 3. MY FACTURE ");
            System.out.println(Command.formatString("[ ENTER YOUR CHOICE ]", 120));
            int choice = -1;
            while (true) {
                try {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        System.err.println(Command.formatString("[ EMPTY CHOICE, TRY AGAIN ]", 120));
                        continue;
                    }
                    if (line.endsWith("\r\n"))
                        line = line.substring(0, line.length() - 2);
                    else if (line.endsWith("\n"))
                        line = line.substring(0, line.length() - 1);
                    choice = Integer.parseInt(line);
                } catch (Throwable ignored) {
                }
                if (choice < 0 || choice > 3) {
                    System.err.println(Command.formatString("[ INVALID CHOICE, TRY AGAIN ]", 120));
                    continue;
                }
                break;
            }
            switch (choice) {
                case 0:
                    System.out.println(Command.formatString("[ GOOD BYE ]", 120));
                    System.exit(0);
                case 1:
                    System.out.println(r);
                    System.out.printf(" %s | %s | %s %n",
                            Command.formatString("CODE", 10),
                            Command.formatString("NOM", 30),
                            Command.formatString("PRICE", 20)
                    );
                    for (Product product : store.values()) {
                        System.out.println(r);
                        product.print();
                    }
                    System.out.println(r);
                    continue;
                case 2:
                    System.out.println(Command.formatString("[ ENTER PRODUCT CODE ]", 120));
                    choice = -1;
                    while (true) {
                        try {
                            String line = scanner.nextLine();
                            if (line.isEmpty()) {
                                System.err.println(Command.formatString("[ EMPTY CODE, TRY AGAIN ]", 120));
                                continue;
                            }
                            if (line.endsWith("\r\n"))
                                line = line.substring(0, line.length() - 2);
                            else if (line.endsWith("\n"))
                                line = line.substring(0, line.length() - 1);
                            choice = Integer.parseInt(line);
                        } catch (Throwable ignored) {
                            System.err.println(Command.formatString("[ INVALID CODE, TRY AGAIN ]", 120));
                            continue;
                        }
                        if (!store.containsKey(choice)) {
                            System.err.println(Command.formatString("[ CODE NOT FOUND ]", 120));
                            choice = -1;
                        }
                        break;
                    }
                    Product p = store.get(choice);
                    if (p == null)
                        continue;
                    System.out.println(Command.formatString("[ ENTER THE QUANTITY ]", 120));
                    choice = -1;
                    while (true) {
                        try {
                            String line = scanner.nextLine();
                            if (line.isEmpty()) {
                                System.err.println(Command.formatString("[ EMPTY INPUT, TRY AGAIN ]", 120));
                                continue;
                            }
                            if (line.endsWith("\r\n"))
                                line = line.substring(0, line.length() - 2);
                            else if (line.endsWith("\n"))
                                line = line.substring(0, line.length() - 1);
                            choice = Integer.parseInt(line);
                        } catch (Throwable ignored) {
                            System.err.println(Command.formatString("[ INVALID INPUT, TRY AGAIN ]", 120));
                            continue;
                        }
                        if (choice < 0) {
                            System.err.println(Command.formatString("[ NEGATIVE INPUT, TRY AGAIN ]", 120));
                            continue;
                        }
                        break;
                    }
                    if (facture == null)
                        facture = new Bill();
                    facture.addCommand(new Command(p, choice));
                    System.out.println(Command.formatString("[ NEW COMMAND ADDED. ]", 120));
                    break;
                case 3:
                    System.out.println(r);
                    if (facture == null) {
                        System.out.println(Command.formatString("[ YOUR NOT HAVE FACTURE ]", 120));
                        continue;
                    }
                    facture.print();
                    System.out.println(r);
                    break;
                default:
                    assert false;
            }
        }
    }
}