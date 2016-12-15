
package TPIS_Trab1.Services;

import TPIS_Trab1.Domain.Product;
import TPIS_Trab1.Services.Exception.CouldNotReadFileException;
import TPIS_Trab1.Services.Exception.CouldNotSaveProductException;
import TPIS_Trab1.Services.Exception.CouldNotLoadProductsException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class FileManager implements ProductDAOInterface {

    private final String fileName;
    private Collection<Product> products;

    public FileManager(String fileName)
            throws CouldNotReadFileException,
            CouldNotLoadProductsException {
        // Pega o caminho do arquivo
        String absolutePath = new File(".").getAbsolutePath();
        Path path = Paths.get(absolutePath, fileName);
        File file = new File(path.toUri());

        try {
            // Se o arquivo não existir, cria o diretório e o arquivo
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            fileName = file.getCanonicalPath();
        } catch (IOException e) {
            throw new CouldNotReadFileException(
                    "O arquivo não existe ou não pode ser aberto.", e);
        }

        this.fileName = fileName;

        this.load();
    }

    public void load()
            throws CouldNotReadFileException,
            CouldNotLoadProductsException {
        ArrayList<Object> objectList = new ArrayList<>();

        try {
            FileInputStream fileInput = new FileInputStream(fileName);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            objectList = (ArrayList<Object>) objectInput.readObject();

            objectInput.close();
            fileInput.close();
        } catch (IOException e) {
            throw new CouldNotReadFileException(
                    "O arquivo não existe ou não pode ser aberto.", e);
        } catch (ClassNotFoundException e) {
            throw new CouldNotLoadProductsException(
                    "O arquivo não existe ou não pode ser aberto.", e);
        }

        // Converte os objetos para produtos
        products = new ArrayList<>();
        for (Object o : objectList) {
            products.add((Product) o);
        }

    }

    public void save(Collection<Object> objects)
            throws CouldNotSaveProductException {
        ArrayList<Object> objectList = new ArrayList<>(objects);

        try {
            FileOutputStream fileOutput = new FileOutputStream(fileName);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(objectList);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e) {
            throw new CouldNotSaveProductException(
                    "O arquivo não existe ou não pode ser aberto.", e);
        }
    }

    @Override
    public Collection<Product> getProducts() {
        return this.products;
    }

    @Override
    public Product getProduct(Integer productId) {
        for (Product product : this.products) {
            if (productId.equals(product.getProductId())) {
                return product;
            }
        }

        return null;
    }

    @Override
    public void addProduct(Product product) throws CouldNotSaveProductException {
        if (getProduct(product.getProductId()) != null) {
            throw new CouldNotSaveProductException("Já existe um produto com o ID " + product.getProductId());
        }

        products.add(product);
        this.save(new ArrayList<>(products));
    }

}
