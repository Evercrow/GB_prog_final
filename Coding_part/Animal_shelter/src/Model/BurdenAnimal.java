package Model;

import java.util.ArrayList;

public abstract class BurdenAnimal extends Animal{
    private String groupName ;
    public BurdenAnimal(){
        super();
        this.groupName = "Вьючное";
    }

    public BurdenAnimal(String animalName , String birthDate, ArrayList<String> commands){
        super(animalName,birthDate,commands);
        this.groupName = "Вьючное";
    }
}
