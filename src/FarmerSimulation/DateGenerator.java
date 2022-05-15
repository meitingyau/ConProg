package FarmerSimulation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.text.*;

public class DateGenerator {

    private Date date1;
    private Date date2;
    private Date randomDate;
    // Initializing the date converter to convert string into date data type
    private SimpleDateFormat converter = new SimpleDateFormat("dd/MM/yyyy");
    // Initializing the date formatter to do formatting & convert back the date into
    // string for database
    private DateFormat formattter = DateFormat.getDateInstance();

    DateGenerator(String date1, String date2) throws ParseException {
        this.date1 = converter.parse(date1);
        this.date2 = converter.parse(date2);
    }

    public String getRandomDate() {
        randomDate = new Date(ThreadLocalRandom.current().nextLong(date1.getTime(), date2.getTime()));
        return formattter.format(randomDate);
    }

    public static void main(String[] args) throws ParseException {
        // Example using the getRandomDate method
        // Need to have two string date in format "dd/MM/yyyy"
        String sDate1 = "31/12/2000";
        String sDate2 = "13/05/2022";

        // Create DateGenerator class instance here with Parameter 2 > Parameter 1
        // since sDate2 larger than sDate1 so put it as below
        DateGenerator dg = new DateGenerator(sDate1, sDate2);
        String randomDate = dg.getRandomDate();
        System.out.println("The random date is : " + randomDate);

    }
}