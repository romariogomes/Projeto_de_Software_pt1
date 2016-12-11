package TPIS_Trab1.Services;

import TPIS_Trab1.Services.Exception.CouldNotReadFileException;
import TPIS_Trab1.Services.Exception.CouldNotSaveProductsException;
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

public class FileManager {

    private final String fileName;

    public FileManager(String fileName) 
            throws CouldNotReadFileException {
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
    }

    public Collection<Object> load() 
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

        return objectList;
    }

    public void save(Collection<Object> objects) 
            throws CouldNotSaveProductsException {
        ArrayList<Object> objectList = new ArrayList<>(objects);

        try {
            FileOutputStream fileOutput = new FileOutputStream(fileName);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(objectList);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e) {
            throw new CouldNotSaveProductsException(
                    "O arquivo não existe ou não pode ser aberto.", e);
        }
    }

}
