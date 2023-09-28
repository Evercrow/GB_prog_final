package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Hamster extends HomeAnimal {
    private String species;

    public Hamster(String animalName, String birthDate, String commands) {
        super(  animalName,
                birthDate,
                new ArrayList<>(Arrays.asList(commands.split(SPLIT_REGEX))));
        this.species = "Хомяк";
    }
}
