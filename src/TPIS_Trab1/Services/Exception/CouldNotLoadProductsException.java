
package TPIS_Trab1.Services.Exception;

public class CouldNotLoadProductsException extends Exception {

    public CouldNotLoadProductsException(String message) {
        super(message);
    }
    
    public CouldNotLoadProductsException(String message, Exception e) {
        super(message, e);
    }
    
}
