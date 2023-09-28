package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Cat extends HomeAnimal {

    private String species;

    public Cat(String animalName, String birthDate, String commands) {
        super(  animalName,
                birthDate,
                new ArrayList<>(Arrays.asList(commands.split(SPLIT_REGEX))));
        this.species = "Кошка";

    }

}
