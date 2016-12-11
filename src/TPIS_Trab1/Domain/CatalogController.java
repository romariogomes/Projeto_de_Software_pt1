package TPIS_Trab1.Domain;

import TPIS_Trab1.Domain.Exception.InvalidEndDateException;
import TPIS_Trab1.Domain.Exception.InvalidStartDateException;
import TPIS_Trab1.Domain.Exception.EmptyProductDescriptionException;
import TPIS_Trab1.Domain.Exception.EmptyProductNameException;
import TPIS_Trab1.Domain.Exception.InvalidDateComparisonException;
import TPIS_Trab1.Domain.Exception.InvalidProductIdException;
import TPIS_Trab1.Domain.Exception.ProductException;
import TPIS_Trab1.Domain.Exception.ProductIdAlreadyExistsException;
import TPIS_Trab1.Services.Exception.CouldNotSaveProductsException;
import TPIS_Trab1.Services.Exception.CouldNotLoadProductsException;
import TPIS_Trab1.Services.Exception.CouldNotReadFileException;
import TPIS_Trab1.Services.FileManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class CatalogController {

    private static Catalog catalog;
    private final FileManager fileManager;

    public CatalogController(FileManager fileManager) 
            throws CouldNotReadFileException, 
                   CouldNotLoadProductsException {
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
            int productId, String name, String description, Date startDate, Date endDate) 
            throws ProductException, 
                   CouldNotSaveProductsException {

        Product product = new Product();
        // Se o id do produto for menor ou igual a zero, tem que lancar exceção
        if (productId <= 0) {
            throw new InvalidProductIdException("O ID " + productId + " é inválido");
        }
        // Se retornar resultados para uma busca pelo id do produto
        // Tem que lançar uma exceção
        if (!this.searchProductsById(productId).isEmpty()) {
            throw new ProductIdAlreadyExistsException("Já existe um produto com o ID " + productId);
        }
        product.setProductId(productId);
        
        // Se o nome do produto for vazio, lança uma exceção
        name = name.trim();
        if (name.length() < 5) {
            throw new EmptyProductNameException("O nome do produto deve possuir pelo menos 5 caracteres.");
        }
        product.setName(name);
        
        // Se a descrição for vazia, lança uma exceção
        description = description.trim();
        if (description.length() < 10) {
            throw new EmptyProductDescriptionException("A descricao do produto deve possuir pelo menos 10 caracteres.");
        }
        product.setDescription(description);
        
        // Pega as datas máxima e mínima
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        Date begin = calendar.getTime();

        // Se a data de início for menor que a de fim, lança uma exceção
        if (startDate.before(endDate)) {
            throw new InvalidDateComparisonException("A data de início deve ser menor que a data de fim.");
        }
        
        // Se a data inicial for antes do começo ou antes de hoje, lança uma exceção
        if (startDate.before(begin) || startDate.after(today)) {
            throw new InvalidStartDateException("A data de inicio deve ser entre 01/01/1970T00:00:00 e a data atual.");
        }
        product.setStartDate(startDate);
        
        // Se a data finalfor antes do começo ou antes de hoje, lança uma exceção
        if (endDate.before(begin) || startDate.after(today)) {
            throw new InvalidEndDateException("A data de fim deve ser entre 01/01/1970T00:00:00 e a data atual.");
        }
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
