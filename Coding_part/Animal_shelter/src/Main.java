import Controller.Controller;
import Model.Animal;
import Model.Camel;
import Model.Services.DataValidator;
import View.View;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        String date1 = "6.12.1000";
//        String date2 = "30-2-1900";
//        String date3 = "30/12/2013";
//
//        DataValidator df = new DataValidator();
//        try {
//            System.out.println(df.formatDate(date1));
//            System.out.println(df.formatDate(date2));
//            System.out.println(df.formatDate(date3));
//        }catch(DateTimeParseException t){
//            System.out.println(t.getMessage());}


        Controller animalShelter = new Controller();
        animalShelter.start();

    }

}
