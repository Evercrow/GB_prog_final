package Model.Services;

import Controller.Controller;
import Model.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;


public class Creator {

    DataValidator dv;

    public Creator(){
        this.dv = new DataValidator();
    }
    public Animal createAnimal(int Id,int classChoice, ArrayList<String> data) throws AddException,DateTimeParseException {
            String bd = dv.formatDate(data.get(1));
            return switch (classChoice) {
                case 1 -> new Cat(Id, data.get(0), bd, data.get(2));
                case 2 -> new Dog(Id, data.get(0), bd, data.get(2));
                case 3 -> new Hamster(Id, data.get(0), bd, data.get(2));
                case 4 -> new Horse(Id, data.get(0), bd, data.get(2));
                case 5 -> new Donkey(Id, data.get(0), bd, data.get(2));
                case 6 -> new Camel(Id, data.get(0), bd, data.get(2));
                default -> throw new AddException();
            };
    }

}
