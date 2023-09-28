package Model;

import java.util.ArrayList;

public abstract class Animal {

    public static final String SPLIT_REGEX = "[ ,]";
    private String topName;
    private String animalName;
    private String birthDate;
    private ArrayList<String> commands;

    public Animal() {
        this.topName = "Животное";
    }

    public Animal(String animalName,String birthDate, ArrayList<String> commands) {
        this.animalName = animalName;
        this.birthDate = birthDate;
        this.commands = commands;
        this.topName = "Животное";
    }
}
