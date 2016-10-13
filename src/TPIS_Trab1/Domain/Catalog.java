package TPIS_Trab1.Domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Catalog {

    private Map<Integer, Product> products;

    public Catalog() {
        this.products = new HashMap<>();
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public void setProducts(Collection<Product> products) {
        this.products = new HashMap<>();
        for (Product p : products) {
            this.products.put(p.getProductId(), p);
        }
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Impressão do Catálogo\n");
        sb.append("Produtos: ").append(products.size()).append("\n");
        sb.append("\n");

        for (Product product : products.values()) {
            sb.append(product.toString()).append("\n");
        }

        sb.append("TOTAL: ").append(products.size()).append("\n");

        return sb.toString();
    }
}
