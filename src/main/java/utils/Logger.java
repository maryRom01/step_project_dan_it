package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description
 *
 * @author Alexander Isai on 13.04.2024.
 */
public class Logger {

    private static final String FILE_LOG = "application.log";

    private static final SimpleDateFormat DATE_LOG = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static void info(String message) {
        logging("INFO", message);
    }

    public static void error(String message) {
        logging("ERROR", message);
    }

    private static void logging(String type, String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_LOG, true))) {
            String timestamp = DATE_LOG.format(new Date());
            out.printf("%s [%s] %s%n", timestamp, type, message);
        } catch (IOException e) {
            System.err.println("Помилка при записі до файлу: " + e.getMessage());
        }
    }

}
