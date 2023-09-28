package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Donkey extends BurdenAnimal {
    private String species;

    public Donkey(String animalName, String birthDate, String commands) {
        super(  animalName,
                birthDate,
                new ArrayList<>(Arrays.asList(commands.split(SPLIT_REGEX))));
        this.species = "Осёл";
    }
}
