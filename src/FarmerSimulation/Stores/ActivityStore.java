package FarmerSimulation.Stores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Source;

import FarmerSimulation.Activity;

public class ActivityStore {

    private SimpleDateFormat converter = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat formattter = DateFormat.getDateInstance();

    public List<Activity> findByUserId(int id) {
        // find activity logs by farmer/user id, put the sorted result into a array list
        List<Activity> listOfActivity = new ArrayList<Activity>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT * FROM `activities` WHERE `activities`.`userId` = " + id + "  ORDER BY date";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                listOfActivity.add(new Activity(result.getString("_id"), result.getString("userId"),
                        result.getString("farmId"), formattter.format(result.getDate("date")),
                        result.getString("action"),
                        result.getString("type"), result.getString("unit"), result.getDouble("quantity"),
                        result.getInt("field"), result.getInt("row")));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return listOfActivity;
    }

    public List<Activity> findByFarmId(int id) {
        // find activity logs by farm id, put the sorted result into a array list
        List<Activity> listOfActivity = new ArrayList<Activity>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT `activities`.* FROM `activities` WHERE `activities`.`farmId` = " + id
                    + "  ORDER BY date";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                listOfActivity.add(new Activity(result.getString("_id"), result.getString("userId"),
                        result.getString("farmId"), formattter.format(result.getDate("date")),
                        result.getString("action"),
                        result.getString("type"), result.getString("unit"), result.getDouble("quantity"),
                        result.getInt("field"), result.getInt("row")));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return listOfActivity;
    }

    public List<Activity> findByFarmIdAndPlantId(int farmId, int plantId) {
        // find activity logs by farm id & plant id, put the sorted result into a array
        // list
        List<Activity> listOfActivity = new ArrayList<Activity>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT `activities`.* FROM `activities` LEFT JOIN `plants` ON `plants`._id =  " + plantId
                    + " WHERE `activities`.`farmId` = " + farmId
                    + " AND `activities`.`type` = `plants`.`name` ORDER BY date";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                listOfActivity.add(new Activity(result.getString("_id"), result.getString("userId"),
                        result.getString("farmId"), formattter.format(result.getDate("date")),
                        result.getString("action"),
                        result.getString("type"), result.getString("unit"), result.getDouble("quantity"),
                        result.getInt("field"), result.getInt("row")));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return listOfActivity;
    }

    public List<Activity> findByFarmIdAndFertilizerId(int farmId, int fertilizerId) {
        // find activity logs by farm id & fertilizer id, put the sorted result into a
        // array list
        List<Activity> listOfActivity = new ArrayList<Activity>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT `activities`.* FROM `activities` LEFT JOIN `fertilizers` ON `fertilizers`._id =  "
                    + fertilizerId
                    + " WHERE `activities`.`farmId` = " + farmId
                    + " AND `activities`.`type` = `fertilizers`.`name` ORDER BY date";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                listOfActivity.add(new Activity(result.getString("_id"), result.getString("userId"),
                        result.getString("farmId"), formattter.format(result.getDate("date")),
                        result.getString("action"),
                        result.getString("type"), result.getString("unit"), result.getDouble("quantity"),
                        result.getInt("field"), result.getInt("row")));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return listOfActivity;
    }

    public List<Activity> findByFarmIdAndPesticideId(int farmId, int pesticideId) {
        // find activity logs by farm id & pesticide id, put the sorted result into a
        // array list
        List<Activity> listOfActivity = new ArrayList<Activity>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT `activities`.* FROM `activities` LEFT JOIN `pesticide` ON `pesticide`._id =  "
                    + pesticideId
                    + " WHERE `activities`.`farmId` = " + farmId
                    + " AND `activities`.`type` = `pesticide`.`name` ORDER BY date";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                listOfActivity.add(new Activity(result.getString("_id"), result.getString("userId"),
                        result.getString("farmId"), formattter.format(result.getDate("date")),
                        result.getString("action"),
                        result.getString("type"), result.getString("unit"), result.getDouble("quantity"),
                        result.getInt("field"), result.getInt("row")));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return listOfActivity;
    }

    public List<Date> findEarliestAndLatestDateByFarmIdAndType(int farmId, String type) {
        // find earliest & latest activity logs by farm id & type , put the result array
        // list
        List<Date> listOfDate = new ArrayList<Date>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = " SELECT  (SELECT `activities`.`date` FROM `activities` WHERE `activities`.`farmId` = "
                    + farmId
                    + " AND `activities`.`type` = '" + type
                    + "' ORDER BY date asc limit 1) AS earliest_date, (SELECT `activities`.`date` FROM `activities` WHERE `activities`.`farmId` = "
                    + farmId + " AND `activities`.`type` = '" + type + "' ORDER BY date desc limit 1) AS latest_date";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            if (result.next()) {
                System.out.println(converter.format(result.getDate("earliest_date")) + " to "
                        + converter.format(result.getDate("latest_date")));
                listOfDate.add(result.getDate("earliest_date"));
                listOfDate.add(result.getDate("latest_date"));
            } else {
                System.out.println("There is no date range data");
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return listOfDate;
    }

    public List<Activity> findByFarmIdAndTypeAndDateRange(int farmId, String type, String startDate,
            String endDate) {
        // find activity logs by farm id & type & date range, put the sorted result into
        // a arraylist
        List<Activity> listOfActivity = new ArrayList<Activity>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT `activities`.* FROM `activities` "
                    + " WHERE ( `activities`.`date` BETWEEN '" + startDate + "' AND '" + endDate
                    + "' ) AND `activities`.`farmId` = " + farmId
                    + " AND `activities`.`type` = '" + type + "' ORDER BY date";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                listOfActivity.add(new Activity(result.getString("_id"), result.getString("userId"),
                        result.getString("farmId"), formattter.format(result.getDate("date")),
                        result.getString("action"),
                        result.getString("type"), result.getString("unit"), result.getDouble("quantity"),
                        result.getInt("field"), result.getInt("row")));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return listOfActivity;
    }

    public List<Integer> findFieldsByFarmIdAndTypeAndDateRange(int farmId, String type, String startDate,
            String endDate) {
        // find fields by farm id & type & date range, put the sorted result into
        // a arraylist
        List<Integer> fields = new ArrayList<Integer>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT `activities`.`field` FROM `activities` WHERE ( `activities`.`date` BETWEEN '"
                    + startDate + "' AND '" + endDate + "' ) AND `activities`.`farmId` = " + farmId
                    + " AND `activities`.`TYPE` = '" + type
                    + "' GROUP BY `activities`.`field` ORDER BY `activities`.`field` ASC";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                fields.add(result.getInt("field"));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return fields;
    }

    public List<Integer> findRowsByFarmIdAndTypeAndDateRangeAndField(int farmId, String type, String startDate,
            String endDate, int field) {
        // find rows by farm id & type & date range, put the sorted result into
        // a arraylist
        List<Integer> rows = new ArrayList<Integer>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT `activities`.`row` FROM `activities` WHERE ( `activities`.`date` BETWEEN '"
                    + startDate + "' AND '" + endDate + "' ) AND `activities`.`farmId` = " + farmId
                    + " AND `activities`.`TYPE` = '" + type + "' AND `activities`.`field` = " + field
                    + " GROUP BY `activities`.`row` ORDER BY `activities`.`row` ASC";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                rows.add(result.getInt("row"));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return rows;
    }

    public String printSummarizedLogs(int farmId, String type, int field, int row, String startDate, String endDate) {
        // print summarized logs by farm id & type & date range & field & row
        String contents = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            String query = "SELECT `activities`.`farmId`, `activities`.`action`, `activities`.`type`, `activities`.`unit`, `activities`.`field`, `activities`.`row`, SUM(`activities`.`quantity`) AS summary FROM `activities` WHERE ( DATE BETWEEN '"
                    + startDate + "' AND '" + endDate + "' ) AND `activities`.`farmId` = " + farmId
                    + " AND `activities`.`TYPE` = '" + type + "' AND `activities`.`FIELD` = " + field
                    + " AND `activities`.`ROW` = " + row
                    + " GROUP BY `activities`.`farmId`, `activities`.`action`, `activities`.`type`, `activities`.`unit`, `activities`.`field`, `activities`.`row`";
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                System.out.println(result.getString("action") + " " + result.getString("type")
                        + " Field "
                        + result.getInt("field") + " Row " + result.getInt("row") + " "
                        + result.getDouble("summary") + " " + result.getString("unit"));
                contents = result.getString("action") + " " + result.getString("type")
                        + " Field "
                        + result.getInt("field") + " Row " + result.getInt("row") + " "
                        + result.getDouble("summary") + " " + result.getString("unit");
            }
            if (contents.isEmpty()){
                System.out.println("There is no data");
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return contents;
    }

}
