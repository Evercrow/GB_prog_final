package Model.Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
public class DataValidator {
    DateTimeFormatter datef ;

    public DataValidator(){
        this.datef = DateTimeFormatter.ofPattern("d[-][.][/]M[-][.][/]yyyy");
    }
    public DataValidator(String datePattern){
        this.datef = DateTimeFormatter.ofPattern(datePattern);
    }


    public String formatDate(String dateStr) throws DateTimeParseException{
        return LocalDate.parse(dateStr,this.datef)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy",Locale.getDefault()));
    }
}
