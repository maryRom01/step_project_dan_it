package utils.general;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

public class Shared {
    public static int generateRandomNumber(int range) {
        double number = Math.random() * range;
        long numberRounded = Math.round(number);
        return (int) numberRounded;
    }

    public static int generateRandomNumberMinMax(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min) + min;
        return randomNumber;
    }

    public static int getRandomDay() {
        LocalDate current = LocalDate.now();
        int day = LocalDate.parse(current.toString()).plusDays(generateRandomNumber(30)).getDayOfMonth();
        return day;
    }

    public static int getCurrentYear() {
        LocalDate current = LocalDate.now();
        return current.getYear();
    }

    public static Month getCurrentMonth() {
        LocalDate current = LocalDate.now();
        Month month = current.getMonth();
        return month;
    }
}
