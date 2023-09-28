package Model;

import java.util.ArrayList;

public abstract class HomeAnimal extends Animal{

    protected String groupName ;
    public HomeAnimal(){
        super();
        this.groupName = "Домашнее";
    }

    public HomeAnimal(int Id,String animalName , String birthDate, ArrayList<String> commands){
        super(Id,animalName,birthDate,commands);
        this.groupName = "Домашнее";
    }
}
