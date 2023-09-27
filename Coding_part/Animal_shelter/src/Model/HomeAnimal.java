package Model;

public abstract class HomeAnimal extends Animal{

    private String groupName ;
    protected HomeAnimal(){
        super();
        this.groupName = "Домашнее";
    }
}
