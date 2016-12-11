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

    public int getInt(Integer value) {
        try {
            String line = this.getString();

            // Se a entrada for vazia e o houver valor padrão
            // retorna o valor padrão
            if (line.isEmpty() && value != null) {
                return value;
            }

            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.print("Digite um número inteiro: ");
            return getInt(value);
        }
    }

    public int getInt() {
        return getInt(null);
    }

    public String getString(String value) {
        String line = input.nextLine().trim();

        // Se a entrada for vazia e o houver valor padrão
        // retorna o valor padrão
        if (line.isEmpty() && value != null) {
            return value;
        }
        
        if (line.isEmpty()) {
            System.out.print("Digite um valor e pressione Enter: ");
            return getString(value);
        }

        return line;
    }
    
    public String getString() {
        return getString(null);
    }

    public Date getDate( String pattern, Date value ) {
        // Pega uma string e confere se é uma data
        String line = getString();
        
        // Se a entrada for vazia e o houver valor padrão
        // retorna o valor padrão
        if (line.isEmpty() && value != null) {
            return value;
        }
        
        LocalDate date;
        try {
            date = LocalDate.parse(line, DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            System.out.print("Digite uma data no formato '" + pattern.toLowerCase() + "': ");
            return getDate(pattern, value);
        }

        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public Date getDate(String pattern) {
        return getDate( pattern, null );
    }
    
    public Date getDate() {
        String pattern = "dd/MM/yyyy";
        return getDate(pattern);
    }

    public Object getOption(Map<String, Object> options) {
        String option;
        do {
            option = this.getString().trim().toLowerCase();
        } while (!options.keySet().contains(option));

        return options.get(option);
    }
}
