
package TPIS_Trab1.Services;

import TPIS_Trab1.Domain.Product;
import TPIS_Trab1.Services.Exception.CouldNotSaveProductException;
import java.util.Collection;

public interface ProductDAOInterface {

    public Collection<Product> getProducts();

    public Product getProduct(Integer productId);

    public void addProduct(Product product) throws CouldNotSaveProductException;

}
