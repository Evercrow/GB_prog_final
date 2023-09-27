package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Camel extends BurdenAnimal {
    private String animalName;
    private String birthDate;
    private ArrayList<String> commands;
    private String species;

    public Camel(String animalName, String birthDate, String commands) {
        super();
        this.species = "Верблюд";
        this.animalName = animalName;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>( Arrays.asList(commands.split(" " )));
    }
}
