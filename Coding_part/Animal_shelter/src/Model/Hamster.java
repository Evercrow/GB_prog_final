package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Hamster extends HomeAnimal {
    private String animalName;
    private String birthDate;
    private ArrayList<String> commands;
    private String species;

    public Hamster(String animalName, String birthDate, String commands) {
        super();
        this.species = "Хомяк";
        this.animalName = animalName;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>( Arrays.asList(commands.split(" " )));
    }
}
