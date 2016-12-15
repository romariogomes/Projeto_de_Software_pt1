
package TPIS_Trab1.Services.Exception;

public class CouldNotSaveProductException extends Exception {

    public CouldNotSaveProductException(String message) {
        super(message);
    }
    
    public CouldNotSaveProductException(String message, Exception e) {
        super(message, e);
    }
    
}
