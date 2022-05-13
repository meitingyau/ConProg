package FarmerSimulation;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateGenerator {
    public static void main(String[] args) {
        Date d1 = new Date();
        Date d2 = new Date();
        System.out.println("d1 : " + d1);
        System.out.println("d2 : " + d2);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();
        System.out.println("Now : " + dateFormatter.format(now));

        Date randomDate = new Date(ThreadLocalRandom.current()
                .nextLong(d1.getTime(), d2.getTime()));
        System.out.println("The random date is : " + randomDate);
    }
}