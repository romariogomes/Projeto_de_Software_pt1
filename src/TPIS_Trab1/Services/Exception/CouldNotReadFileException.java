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
public class CouldNotReadFileException extends Exception {

    public CouldNotReadFileException(String message) {
        super(message);
    }

    public CouldNotReadFileException(String message, Exception e) {
        super(message, e);
    }

}
