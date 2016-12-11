package TPIS_Trab1.Services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

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

    public Object getOption(Map<String, Object> options) {
        String option;
        do {
            option = this.getString().trim().toLowerCase();
        } while( !options.keySet().contains(option) );
        
        return options.get(option);
    }
}
