import Controller.Controller;
import Model.Animal;
import Model.Camel;
import View.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Animal cam = new Camel(1,"Tema","23-10-2000","Spit,sit");


//        List<String> stringData = new ArrayList<>(Arrays.asList(
//            "|  0|Животное|Домашнее|Кошка     |Мурзик      |23-11-2012|домой                                 |",
//                "|  3|Животное|Домашнее||Собака     |Шарик      |23-11-2012|есть спать                        |"
//        ));

        Controller animalShelter = new Controller();
        animalShelter.start();

    }

}
