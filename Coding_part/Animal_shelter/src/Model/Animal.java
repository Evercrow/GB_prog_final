package Model;

import java.util.ArrayList;

public abstract class Animal {
    protected  int Id;
    public static final String SPLIT_REGEX = "[ ,]";
    protected String topName;
    protected String animalName;
    protected String birthDate;
    protected ArrayList<String> commands;

    public Animal() {
        this.topName = "Животное";
        this.Id = 0;
    }

    public Animal(int Id, String animalName,String birthDate, ArrayList<String> commands) {
        this.Id = Id;
        this.animalName = animalName;
        this.birthDate = birthDate;
        this.commands = commands;
        this.topName = "Животное";
    }

    public int getId() {
        return Id;
    }


    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setCommands(String newCommand) {
        this.commands.add(newCommand);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
