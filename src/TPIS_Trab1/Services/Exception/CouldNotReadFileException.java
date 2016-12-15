
package TPIS_Trab1.Services.Exception;

public class CouldNotReadFileException extends Exception {

    public CouldNotReadFileException(String message) {
        super(message);
    }

    public CouldNotReadFileException(String message, Exception e) {
        super(message, e);
    }

}
