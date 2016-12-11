/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPIS_Trab1.Services.Exception;

/**
 *
 * @author maico
 */
public class CouldNotSaveProductsException extends Exception {

    public CouldNotSaveProductsException(String message) {
        super(message);
    }
    
    public CouldNotSaveProductsException(String message, Exception e) {
        super(message, e);
    }
    
}
