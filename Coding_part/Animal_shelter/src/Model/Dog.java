package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Dog extends HomeAnimal {
    private String species;

    public Dog(String animalName, String birthDate, String commands) {
        super(  animalName,
                birthDate,
                new ArrayList<>(Arrays.asList(commands.split(SPLIT_REGEX))));
        this.species = "Собака";

    }
}
