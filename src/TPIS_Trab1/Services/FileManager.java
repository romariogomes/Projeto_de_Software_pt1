/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPIS_Trab1.Services;

import java.io.EOFException;
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

/**
 *
 * @author maico
 */
public class FileManager {

    private final String fileName;

    public FileManager(String fileName) {
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
            e.printStackTrace();
            System.exit(1);
        }
        
        this.fileName = fileName;
    }

    public Collection<Object> load() {
        ArrayList<Object> objectList = new ArrayList<>();

        try {
            FileInputStream fileInput = new FileInputStream(fileName);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            objectList = (ArrayList<Object>) objectInput.readObject();

            objectInput.close();
            fileInput.close();
        } catch (EOFException e) {
            // DO NOTHING
        } catch (IOException e) {
            e.printStackTrace();
//            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
//            System.exit(1);
        }

        return objectList;
    }

    public void save(Collection<Object> objects) {
        ArrayList<Object> objectList = new ArrayList<>(objects);

        try {
            FileOutputStream fileOutput = new FileOutputStream(fileName);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(objectList);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
