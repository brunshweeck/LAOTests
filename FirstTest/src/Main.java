import room.Product;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *  Creation d'un progaramme fonctionnant sur console pour la gestion des
 *  Produit sachant q'un produit est caracterisé par:
 *  <li>Un code</li>
 *  <li>Un nom (designation)</li>
 *  <li>Un prix</li>
 *
 *  Etapes:
 *  <li>1er étape: Creation de la classe Produit (qui sera nommer {@link room.Product <b>Product</b>})
 *  <p>
 *      Caracteristiques
 *      <ol>
 *          <li>code: {@code int}</li>
 *          <li>designation: {@code String}</li>
 *          <li>price: {@code long}</li>
 *      </ol>
 *  </p>
 *  </li>
 */
public class Main {
    public static void main(String[] args) {
        Set<Product> products = new HashSet<>();
        String[] names = new String[]{"orange", "pineapple", "yaourt", "ice water", "juice", "beer"};
        int[] prices = new int[]{100, 300, 750, 200, 500, 1200};
        for (int i = 1; i < names.length; i++) {
            products.add(new Product(i ^ 1234, names[i], prices[i]));
        }

        Scanner scanner = new Scanner(System.in);
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.NOON))
            System.out.print("Bonsoir, ");
        else
            System.out.print("Bonjour, ");
        for (int i = 0; ; ) {
            System.out.println("Quel operation desirer vous realiser aujourd'hui ?");
            System.out.println("(1) Avoir la liste des produits desiponibles");
            System.out.println("(2) Passer une commande");
            System.out.println("(3) Ne rien faire");
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.print("Entrer le selection: ");
            long request = 0;
            try {
                request = scanner.nextLong();
            } catch (Throwable ignored) {
            }
            if (request < 1 || request > 3) {
                System.out.println("Entrer invalide");
                continue;
            }
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            switch ((int) request) {
                case 1:
                    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println("Liste des produit disponibles");
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    break;
                case 2:
                    System.out.println("Quel produit desirer vous ?");
                    System.out.print("Veillez entrer son code: ");
                    try {
                        request = scanner.nextLong();
                    } catch (Throwable ignored) {
                    }
                    Product p = null;
                    for (Product product : products) {
                        if (product.getCode() == request) {
                            p = product;
                            break;
                        }
                    }
                    if (p == null) {
                        System.out.println("Désolé nous ne disposont pas de de code produit");
                        continue;
                    }
                    System.out.println("Quel quantité desirer vous ? ");
                    System.out.print("Veuillez entrer la quantité: ");
                    try {
                        request = scanner.nextLong();
                    } catch (Throwable ignored) {
                    }
                    System.out.printf(":: Nouvel Commande: %03d articles (%s)%n", request, p.getDesignation());
                    System.out.printf(":: Coût: %d Franc cfa%n", request * p.getPrice());
                    continue;
                case 3:
                    return;
            }
        }
    }
}