/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPIS_Trab1.Domain.Exception;

/**
 *
 * @author maico
 */
public class InvalidProductNameException extends ProductException {
    
    public InvalidProductNameException(String message) {
        super(message);
    }
    
}
