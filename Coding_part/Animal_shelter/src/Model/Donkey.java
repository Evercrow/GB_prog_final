package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Donkey extends BurdenAnimal {
    private String animalName;
    private String birthDate;
    private ArrayList<String> commands;
    private String species;

    public Donkey(String animalName, String birthDate, String commands) {
        super();
        this.species = "Осёл";
        this.animalName = animalName;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>( Arrays.asList(commands.split(" " )));
    }
}
