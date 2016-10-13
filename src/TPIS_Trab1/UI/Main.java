/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPIS_Trab1.UI;

import TPIS_Trab1.Domain.CatalogController;
import TPIS_Trab1.Domain.Product;
import TPIS_Trab1.Services.InputManager;
import TPIS_Trab1.Services.FileManager;
import java.util.ArrayList;
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

    public static final int OPT_PROCURAR_POR_CODIGO = 1;
    public static final int OPT_PROCURAR_POR_NOME = 1;
    public static final int OPT_PROCURAR_POR_INICIO = 1;

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
        while (option != 0) {
            System.out.println("Selecione uma opção");
            System.out.println("1 - Por Código");
            System.out.println("2 - Por Nome");
            System.out.println("3 - Por Data de Início");
            System.out.println("0 - Cancelar");

            ArrayList<Product> products = new ArrayList<>();

            // Faz a execução da opção
            option = inputManager.getInt();
            switch (option) {
                case OPT_PROCURAR_POR_CODIGO:
                    System.out.print("Digite o código do produto: ");
                    int codigo = inputManager.getInt();
                    products = new ArrayList(catalogController.searchProductsById(codigo));
                    break;
                case OPT_PROCURAR_PRODUTOS:
                    searchProduct();
                    break;
                default:
                    System.out.println("Selecione uma opção válida.");
                    break;
            }

            for (Product product : products) {
                System.out.println(product);
            }

            // Dá uma limpada na tela
            System.out.println("\n\n\n");
        }
    }

    private static void menu() {
        System.out.println("Bem Vindo ao Catálogo de Produtos.");

        int option = -1;
        while (option != 0) {
            System.out.println("Selecione uma opção");
            System.out.println("1 - Cadastrar Produtos");
            System.out.println("2 - Procurar Produtos");
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