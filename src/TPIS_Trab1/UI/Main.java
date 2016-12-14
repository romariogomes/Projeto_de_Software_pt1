package TPIS_Trab1.UI;

import TPIS_Trab1.Domain.CatalogController;
import TPIS_Trab1.Domain.Exception.ProductException;
import TPIS_Trab1.Services.InputManager;
import TPIS_Trab1.Services.FileManager;
import TPIS_Trab1.Services.Exception.CouldNotLoadProductsException;
import TPIS_Trab1.Services.Exception.CouldNotReadFileException;
import TPIS_Trab1.Services.Exception.CouldNotSaveProductException;
import TPIS_Trab1.Services.ProductDAOInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static InputManager inputManager;
    private static CatalogController catalogController;

    public static final int OPT_CADASTRAR_PRODUTOS = 1;
    public static final int OPT_PROCURAR_PRODUTOS = 2;
    public static final int OPT_LISTAR_PRODUTOS = 3;

    public static final int OPT_PROCURAR_POR_CODIGO = 1;
    public static final int OPT_PROCURAR_POR_NOME = 2;
    public static final int OPT_PROCURAR_POR_INICIO = 3;
    public static final int OPT_PROCURAR_CANCELAR = 0;

    public static final String FILE_CATALOG_DATA = "/resources/catalog.data";

    public static void main(String[] args) {
        // Inicializa as dependencias
        init();
        // Exibe a tela inicial
        menu();

    }

    private static void init() {
        // Cria a instancia do InputManager
        inputManager = new InputManager(new Scanner(System.in));

        try {
            // Cria a instancia do FileManager
            ProductDAOInterface productDao = new FileManager(FILE_CATALOG_DATA);
            
            // Cria a instancia do controlador do catálogo
            catalogController = new CatalogController(productDao);
        } catch (CouldNotReadFileException ex) {
            System.out.println("ERR: Não foi possível abrir o arquivo.");
            System.out.println("Verifique se ele não está sendo usado por outra aplicação.");
        } catch (CouldNotLoadProductsException ex) {
            System.out.println("ERR: Não foi possível carregar as informações do arquivo.");
            System.out.println("O deve estar corrompido ou foi alterado durante a execução.");
        }
    }

    private static void menu() {
        System.out.println("Bem Vindo ao Catálogo de Produtos.");

        int option = -1;
        while (option != 0) {
            System.out.println("Selecione uma opção");
            System.out.println("1 - Cadastrar Produtos");
            System.out.println("2 - Procurar Produtos");
            System.out.println("3 - Listar Produtos");
            System.out.println("0 - Sair");

            // Faz a execução da opção
            option = inputManager.getInt();
            switch (option) {
                case OPT_CADASTRAR_PRODUTOS:
                    insertProduct();
                    break;
                case OPT_PROCURAR_PRODUTOS:
                    searchProduct();
                    break;
                case OPT_LISTAR_PRODUTOS:
                    listProducts();
                    break;
                default:
                    System.out.println("Selecione uma opção válida.");
                    break;
            }

            // Dá uma limpada na tela
            System.out.println("\n\n\n");
        }

        System.out.println("Finalizando sistema. Até mais.");
    }

    private static void insertProduct(
            Integer productId,
            String name,
            String description,
            Date startDate,
            Date endDate) {
        System.out.println("### Cadatrar Produtos");

        askUser("Código: ", productId);
        productId = inputManager.getInt(productId);

        askUser("Nome: ", name);
        name = inputManager.getString(name);

        askUser("Descrição: ", description);
        description = inputManager.getString(description);

        String datePattern = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        
        String startDateFormated = startDate != null ? dateFormat.format(startDate) : null;
        String endDateFormated = endDate != null ? dateFormat.format(endDate) : null;
        
        askUser("Data de Início (" + datePattern.toLowerCase() + "): ", startDateFormated);
        startDate = inputManager.getDate(datePattern, startDate);

        askUser("Data de Finalização (" + datePattern.toLowerCase() + "): ", endDateFormated);
        endDate = inputManager.getDate(datePattern, endDate);

        try {
            catalogController.addProduct(productId, name, description, startDate, endDate);
        } catch (CouldNotSaveProductException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Encerrando atividade do sistema.");
            System.exit(-1);

            // Utilização de polimorfismo para não ter que adicionar vários catchs
            // ou mesmo várias exceções em um catch
            // As exceções do produto foram agrupadas em uma exceção mais generalizada
        } catch (ProductException ex) {
            System.out.println("### " + ex.getMessage());

            System.out.print("Você deseja continuar com o cadastro? [s/N] ");
            Map<String, Object> options = new HashMap();
            options.put("s", new Boolean(true));
            options.put("n", new Boolean(false));

            Boolean _continue = (Boolean) inputManager.getOption(options);

            if (_continue) {
                // Chama a insersão de produtos novamente
                insertProduct(productId, name, description, startDate, endDate);
            }
        }
    }

    private static void insertProduct() {
        insertProduct(null, null, null, null, null);
    }

    private static void searchProduct() {
        System.out.println("### Procurar Produtos");

        int option = -1;
        while (option == -1) {
            System.out.println("Selecione uma opção");
            System.out.println("1 - Por Código");
            System.out.println("2 - Por Nome");
            System.out.println("3 - Por Data de Início");
            System.out.println("0 - Cancelar");

            ArrayList<Object> products = new ArrayList<>();

            // Faz a execução da opção
            option = inputManager.getInt();
            switch (option) {
                case OPT_PROCURAR_POR_CODIGO:
                    System.out.print("Digite o código do produto: ");
                    int codigo = inputManager.getInt();
                    products = new ArrayList(catalogController.searchProductsById(codigo));
                    break;

                case OPT_PROCURAR_POR_NOME:
                    System.out.print("Digite o nome do produto: ");
                    String nome = inputManager.getString();
                    products = new ArrayList(catalogController.searchProductsByName(nome));
                    break;

                case OPT_PROCURAR_POR_INICIO:
                    System.out.print("Digite a data de inicio do produto: ");
                    Date date = inputManager.getDate();
                    products = new ArrayList(catalogController.searchProductsByStartDate(date));
                    break;

                case OPT_PROCURAR_CANCELAR:
                    // DO NOTHING
                    break;

                default:
                    option = -1;
                    System.out.println("Selecione uma opção válida.");
                    break;
            }

            listProducts(products);
        }
    }

    private static void listProducts() {
        listProducts(new ArrayList(catalogController.getProducts()));
    }

    private static void listProducts(Collection<Object> products) {
        System.out.println("### Resultado:");
        System.out.println("Qtd Produtos: " + products.size());

        if (products.isEmpty()) {
            System.out.println("Não há produtos para listar");
            return;
        }

        for (Object product : products) {
            System.out.println(product);
        }

    }

    private static void askUser(String message, Object value) {
        System.out.print(message);
        if (value != null) {
            System.out.print("[" + value + "] ");
        }
    }

}
