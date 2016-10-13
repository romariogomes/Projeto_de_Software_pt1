/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPIS_Trab1.Services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author maico
 */
public class InputManager {

    private Scanner input;

    public InputManager(Scanner input) {
        this.input = input;
    }

    public int getInt() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.print("Digite um número inteiro: ");
            return getInt();
        }
    }

    public String getString() {
        String retorno = input.nextLine().trim();

        if (retorno.length() == 0) {
            System.out.print("Digite um valor e pressione Enter: ");
            return getString();
        }

        return retorno;
    }

    public Date getDate() {
        String pattern = "dd/MM/yyyy";
        // Pega uma string e confere se é uma data
        String entrada = getString();

        LocalDate date;
        try {
            date = LocalDate.parse(entrada, DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            System.out.print("Digite uma data no formato '" + pattern.toLowerCase() + "': ");
            return getDate();
        }

        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
