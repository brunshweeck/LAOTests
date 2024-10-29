package room;

import java.util.Objects;

/**
 *
 * Represention d'un produit du magasin
 */
public class Product {
    private long code;
    private String designation;
    private long price;

    /**
     * @param code le code du produit
     * @param designation le nom du produit
     * @param price le prix du produit
     */
    public Product(long code, String designation, long price) {
        if (code <= 0)
            throw new IllegalArgumentException("Le code du produit est invalide");
        this.code = code;
        if (designation == null || designation.isEmpty())
            throw new IllegalArgumentException("Le nom du produit est invalide");
        this.designation = designation;
        if (price <= 0)
            throw new IllegalArgumentException("Le prix du produit est invalide");
        this.price = price;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getCode() == product.getCode() &&
                getPrice() == product.getPrice() &&
                Objects.equals(getDesignation(), product.getDesignation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getDesignation(), getPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", designation='" + designation + '\'' +
                ", price=" + price +
                '}';
    }
}
