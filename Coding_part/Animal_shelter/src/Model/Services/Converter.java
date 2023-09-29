package Model.Services;

import Model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * класс, призванный преобразовывать строки в объекты нужных классов животных
 */
public class Converter {
    final static String DELIMITER = "[| ]+";
    public static List<Animal> convert(List<String> stringData ){
        List<Animal> result = new ArrayList<>(stringData.size());
        for (String line:stringData){
            line = line.replaceFirst(DELIMITER,"").replaceAll(DELIMITER+"$","");
            String[] animalFields = line.split(DELIMITER,7);
            switch (animalFields[3]) {
                case "Кошка" ->
                        result.add(new Cat(Integer.parseInt(animalFields[0]), animalFields[4], animalFields[5], animalFields[6]));
                case "Собака" ->
                        result.add(new Dog(Integer.parseInt(animalFields[0]), animalFields[4], animalFields[5], animalFields[6]));
                case "Хомяк" ->
                        result.add(new Hamster(Integer.parseInt(animalFields[0]), animalFields[4], animalFields[5], animalFields[6]));
                case "Лошадь" ->
                        result.add(new Horse(Integer.parseInt(animalFields[0]), animalFields[4], animalFields[5], animalFields[6]));
                case "Осёл" ->
                        result.add(new Donkey(Integer.parseInt(animalFields[0]), animalFields[4], animalFields[5], animalFields[6]));
                case "Верблюд" ->
                        result.add(new Camel(Integer.parseInt(animalFields[0]), animalFields[4], animalFields[5], animalFields[6]));
                default -> System.out.printf("%s такой вид животного не найден, запись пропущена", animalFields[3]);
            }
        }
        return result;
    }
}
