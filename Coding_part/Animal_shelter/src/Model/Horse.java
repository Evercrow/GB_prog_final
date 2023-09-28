package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Horse extends BurdenAnimal {
    private String species;

    public Horse(String animalName, String birthDate, String commands) {
        super(  animalName,
                birthDate,
                new ArrayList<>(Arrays.asList(commands.split(SPLIT_REGEX))));
        this.species = "Лошадь";
    }
}
