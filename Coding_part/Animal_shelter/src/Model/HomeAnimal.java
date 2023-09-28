package Model;

import java.util.ArrayList;

public abstract class HomeAnimal extends Animal{

    private String groupName ;
    public HomeAnimal(){
        super();
        this.groupName = "Домашнее";
    }

    public HomeAnimal(String animalName , String birthDate, ArrayList<String> commands){
        super(animalName,birthDate,commands);
        this.groupName = "Домашнее";
    }
}
