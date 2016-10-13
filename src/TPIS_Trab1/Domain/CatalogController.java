/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPIS_Trab1.Domain;

import TPIS_Trab1.Services.FileManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author maico
 */
public class CatalogController {

    private static Catalog catalog;
    private final FileManager fileManager;

    public CatalogController(FileManager fileManager) {
        this.fileManager = fileManager;

        catalog = new Catalog();

        Collection<Object> objects = fileManager.load();
        Collection<Product> products = new ArrayList<>();
        for (Object o : objects) {
            products.add((Product) o);
        }
        catalog.setProducts(products);
    }

    public void addProduct(
            int productId, String name, String description, Date startDate, Date endDate) {

        Product product = new Product();
        product.setProductId(productId);
        product.setName(name);
        product.setDescription(description);
        product.setStartDate(startDate);
        product.setEndDate(endDate);
        
        catalog.addProduct(product);

        Collection<Product> products = catalog.getProducts();
        Collection<Object> objects = new ArrayList<>(products);
        fileManager.save(objects);
    }

    public Collection<Product> searchProductsById(int productId) {
        Collection<Product> products = new ArrayList<>();
        for (Product product : catalog.getProducts()) {
            if (product.getProductId() == productId) {
                products.add(product);
            }
        }
        
        return products;
    }

    public Collection<Product> searchProductsByName(String name) {
    	Collection<Product> products = new ArrayList<>();
        for (Product product : catalog.getProducts()) {
            if (product.getName().contains(name)) {
                products.add(product);
            }
        }
        
        return products;
    }

    public Collection<Product> searchProductsByStartDate(Date startDate) {
    	Collection<Product> products = new ArrayList<>();
        for (Product product : catalog.getProducts()) {
            if (product.getStartDate().equals(startDate)) {
                products.add(product);
            }
        }
        
        return products;
    }

    public Collection<Product> getProducts() {
        return catalog.getProducts();
    }

}
