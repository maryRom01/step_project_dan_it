package utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * This is a date converter from a given string to a long number
 * and from a long number to a given string format.
 *
 * @author Alexander Isai on 14.04.2024.
 */
public class DateConverter {

    public static long stringToLong(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDateLocal = LocalDate.parse(date, formatter);
        return birthDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String longToString(long date){
        LocalDate birthDateLocal = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthDateLocal.format(formatter);
    }

    public static Date stringToDate(String date) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.parse(date);
    }
}
