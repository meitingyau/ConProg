package FarmerSimulation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateGenerator {

    private Date date1;
    private int days;
    private SimpleDateFormat converter = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar c = Calendar.getInstance();    

    DateGenerator(String date1) throws ParseException {
        this.date1 = converter.parse(date1);
    }

    public void setDays(int days){
        this.days = days;
    }

    public String addDays() {
        c.setTime(date1);
        c.add(Calendar.DATE, days);
        return converter.format(c.getTime());
    }

}