package Model;

import java.util.ArrayList;

public abstract class BurdenAnimal extends Animal{
    protected String groupName ;
    public BurdenAnimal(){
        super();
        this.groupName = "Вьючное";
    }

    public BurdenAnimal(int Id,String animalName , String birthDate, ArrayList<String> commands){
        super(Id,animalName,birthDate,commands);
        this.groupName = "Вьючное";
    }
}
