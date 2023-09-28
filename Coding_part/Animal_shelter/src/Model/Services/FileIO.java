package Model.Services;

import Model.Animal;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

/**
 * Класс, являющийся конечной и начальной точкой при работе с файлами. Можно передать специфичный путь к файлй в конструктор
 * иначе будет использовано имя и путь в константе SAVED_REGISTRY
 */
public class FileIO {
    public String SAVED_REGISTRY = "./shelter_registry.txt";

    public List<Animal> fileAnimals;

    public FileIO(){
        this.fileAnimals = readFile(SAVED_REGISTRY);
    }

    public List<Animal> readFile(String filepath) {
        try {
            return Converter.convert(
                    Files.readAllLines(Paths.get(filepath)));

        } catch(IOException e) {
            System.out.printf("Файл по пути %s не найден%n",filepath);
        }
        return List.of();
    }

    public boolean saveRegistry(Collection<Animal> animalList) {
        try(BufferedWriter wr = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(SAVED_REGISTRY), StandardCharsets.UTF_8)))
        {
            for (Animal a: animalList){
                wr.write(a.toString());
                wr.newLine();
            }
            return true;
        } catch(IOException e) {
            e.printStackTrace();
        }
    return false;
    }
}
