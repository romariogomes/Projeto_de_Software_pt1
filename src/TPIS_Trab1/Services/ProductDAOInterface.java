/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPIS_Trab1.Services;

import TPIS_Trab1.Domain.Product;
import TPIS_Trab1.Services.Exception.CouldNotSaveProductException;
import java.util.Collection;

/**
 *
 * @author maico
 */
public interface ProductDAOInterface {
    
    public Collection<Product> getProducts();
    
    public Product getProduct(Integer id);
    
    public void addProduct(Product product) throws CouldNotSaveProductException;
    
    
}
