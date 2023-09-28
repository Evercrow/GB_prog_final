package Model.Services;

public class AddException extends RuntimeException{
    public AddException() {
        super("Такого класса нет в меню");
    }
}
