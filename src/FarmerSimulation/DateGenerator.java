package FarmerSimulation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.text.*;

public class DateGenerator {

    private Date date1;
    private int days;
    private Date randomDate;
    // Initializing the date converter to convert string into date data type
    private SimpleDateFormat converter = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar c = Calendar.getInstance();
    // Initializing the date formatter to do formatting & convert back the date into
    // string for database
    // private DateFormat formattter = DateFormat.getDateInstance();
    

    DateGenerator(String date1) throws ParseException {
        this.date1 = converter.parse(date1);
    }

    public void setDays(int days){
        this.days = days;
    }

    // public String getRandomDate() {
    //     randomDate = new Date(ThreadLocalRandom.current().nextLong(date1.getTime(), date2.getTime()));
    //     return converter.format(randomDate);
    // }

    public String addDays() {
        c.setTime(date1);
        c.add(Calendar.DATE, days);
        return converter.format(c.getTime());
    }

    public String addOneDay() {
        c.setTime(date1);
        c.add(Calendar.DATE, 1);
        return converter.format(c.getTime());
    }

    public String addFiveDays() {
        c.setTime(date1);
        c.add(Calendar.DATE, 5);
        return converter.format(c.getTime());
    }

    public static void main(String[] args) throws ParseException {
        // Example using the getRandomDate method
        // Need to have two string date in format "dd/MM/yyyy"
        String sDate1 = "2001-12-31";
        String sDate2 = "2022-05-13";
        int days = 3;

        // Create DateGenerator class instance here with Parameter 2 > Parameter 1
        // since sDate2 larger than sDate1 so put it as below
        DateGenerator dg = new DateGenerator(sDate1);
        // String randomDate = dg.getRandomDate();
        dg.setDays(days);
        String randomDate = dg.addDays();
        System.out.println("The random date is : " + randomDate);

    }
}