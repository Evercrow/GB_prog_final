package View;
import Model.Animal;
import java.util.Collection;
import java.util.Scanner;



/**
 * отображение и форматирование вывода на экране, сканер ввода
 */
public class View {
    public static final String TABLE_ROW = "|%3s|%-8s|%8s|%-10s|%-12s|%10s|%-40s|";
    public static final String TABLE_HEADER = String.format(TABLE_ROW
            , "id"
            , "  Класс"
            , "Группа"
            , "   Вид"
            , "   Кличка"
            , "Дата рожд."
            , "              Команды"
    );
    public static final String TABLE_BORDER = String.format("+%3s+%8s+%8s+%10s+%12s+%10s+%40s+"
                                                                , "-".repeat(3)
                                                                , "-".repeat(8)
                                                                , "-".repeat(8)
                                                                , "-".repeat(10)
                                                                , "-".repeat(12)
                                                                , "-".repeat(10)
                                                                , "-".repeat(40));

    Scanner scan;

    public View(){
        this.scan = new Scanner(System.in);
    }
    public void showIntro(){
        System.out.println("Добро пожаловать в питомник \"Цифровое Царство\"! ");
        System.out.println("Для навигации по меню введите номер нужного пункта и нажмите Enter");
    }

    public void showOutro(){
        System.out.println("Работа завершена");
        System.out.println("Спасибо за использование нашей программы!");
    }


    public void showMainMenu() {
        System.out.println();
        System.out.println("1 - посмотреть весь список животных");
        System.out.println("2 - добавить новое животное");
        System.out.println("3 - работа с одним животным");
        System.out.println("4 - сохранить состояние реестра");
        System.out.println("5 - выйти из программы");
    }

    public void showAnimalMenu(Animal a){
        System.out.println("Вы работаете со следующим животным:");
        System.out.println(a);
        System.out.println("1 - научить новым командам");
        System.out.println("2 - переименовать");
        System.out.println("3 - удалить из реестра");
        System.out.println("4 - назад");
    }

    public void wrongMenu(){
        System.out.println("Неверный номер меню,повторите ввод");
    }
    public Integer IdFromUser(){
        System.out.print("Введите id животного: ");
        return Integer.parseInt(scan.nextLine()) ;
    }

    public String getNewCommand(){
        System.out.print("Введите новую команду: ");
        return scan.nextLine();
    }

    public String getNewName(){
        System.out.print("Введите новую кличку: ");
        return scan.nextLine();
    }

    public void operationResult(boolean res){
        if(res){
            System.out.println("операция успешна");
        }
    }
    public int classMenu(){
        System.out.println("Выберите какое животное вы хотите добавить");
        System.out.println("1 - Кошка");
        System.out.println("2 - Собака");
        System.out.println("3 - Хомяк");
        System.out.println("4 - Лошадь");
        System.out.println("5 - Осел");
        System.out.println("6 - Верблюд");
        return menuChoice();
    }

    public int menuChoice() {
        System.out.print("Ваш ввод: ");
        String inputLine = scan.nextLine();
        return switch (inputLine) {
            case ("1") -> 1;
            case ("2") -> 2;
            case ("3") -> 3;
            case ("4") -> 4;
            case ("5") -> 5;
            case ("6") -> 6;
            default -> 0;
        };
    }



    public String newAnimalInput(){
        System.out.println("Введите имя, дату рождения, выученные команды животного через пробел:");
        return scan.nextLine();
    }

    private void printTableLine() {
        System.out.println(TABLE_BORDER);
    }

    public void printDB(Collection<Animal> animalList) {
        printTableLine();
        System.out.println(TABLE_HEADER);
            for (Animal animal: animalList) {
                System.out.println(animal);
            }
        printTableLine();
    }

    public void wrongId(int id){
        System.out.printf("Записи с id-номером %d нет в реестре%n",id);
    }

}
