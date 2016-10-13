/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPIS_Trab1.UI;

import TPIS_Trab1.Domain.CatalogController;
import TPIS_Trab1.Services.InputManager;
import TPIS_Trab1.Services.FileManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author maico
 */
public class Main {

    private static FileManager fileManager;
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
        // Cria a instancia do FileManager
        fileManager = new FileManager(FILE_CATALOG_DATA);

        // Cria a instancia do InputManager
        inputManager = new InputManager(new Scanner(System.in));

        // Cria a instancia do controlador do catálogo
        catalogController = new CatalogController(fileManager);
    }

    private static void insertProduct() {
        System.out.println("### Cadatrar Produtos");

        System.out.print("Código: ");
        int productId = inputManager.getInt();

        System.out.print("Nome: ");
        String name = inputManager.getString();

        System.out.print("Descrição: ");
        String description = inputManager.getString();

        System.out.print("Data de Início (dd/mm/yyyy): ");
        Date startDate = inputManager.getDate();

        System.out.print("Data de Finalização (dd/mm/yyyy): ");
        Date endDate = inputManager.getDate();

        catalogController.addProduct(productId, name, description, endDate, endDate);
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

}
