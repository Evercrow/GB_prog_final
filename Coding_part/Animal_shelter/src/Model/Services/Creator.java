package Model.Services;

import Model.*;

import java.util.ArrayList;
import java.util.Collections;


public class Creator {

    public Animal createAnimal(int Id,int classChoice, ArrayList<String> data) throws AddException {
            return switch (classChoice) {
                case 1 -> new Cat(Id, data.get(0), data.get(1), data.get(2));
                case 2 -> new Dog(Id, data.get(0), data.get(1), data.get(2));
                case 3 -> new Hamster(Id, data.get(0), data.get(1), data.get(2));
                case 4 -> new Horse(Id, data.get(0), data.get(1), data.get(2));
                case 5 -> new Donkey(Id, data.get(0), data.get(1), data.get(2));
                case 6 -> new Camel(Id, data.get(0), data.get(1), data.get(2));
                default -> throw new AddException();
            };
    }

}
