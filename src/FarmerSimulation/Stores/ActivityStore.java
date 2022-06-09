package FarmerSimulation.Stores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import FarmerSimulation.Activity;

public class ActivityStore {
    public List<Activity> findActivitiesByUserId(int id) {
        List<Activity> arrayOfActivity = new ArrayList<Activity>();
        DateFormat formattter = DateFormat.getDateInstance();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ifarm", "root", "");
            String query = "SELECT * FROM activities WHERE userId= " + Integer.toString(id) + "";
            // String sqlStrUser = "select U.*,F._id from users U join farmusers FU on
            // U._id=FU.userId join farms F on F._id=FU.farmId order by rand() limit " +
            // numberOfFarmers + "";
            System.out.println("" + id);
            System.out.println(query);
            Statement st = conn.createStatement();
            // ps.setInt(1, id);
            // ps.setString(1, Integer.toString(id));
            ResultSet result = st.executeQuery(query);
            // System.out.println("Here " + result);
            while (result.next()) {
                arrayOfActivity.add(new Activity(result.getString("_id"), result.getString("userId"),
                        result.getString("farmId"), formattter.format(result.getDate("date")),
                        result.getString("action"),
                        result.getString("type"), result.getString("unit"), result.getDouble("quantity"),
                        result.getInt("field"), result.getInt("row")));
            }
            System.out.println(arrayOfActivity.size());
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return arrayOfActivity;
    }

    public List<Activity> findActivitiesByFarmId(int id) {
        List<Activity> arrayOfActivity = new ArrayList<Activity>();
        DateFormat formattter = DateFormat.getDateInstance();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ifarm", "root", "");
            String query = "SELECT * FROM `activities` WHERE `activities`.`farmId`=" + id;

            System.out.println(query);
            Statement st = conn.createStatement();
            // ps.setInt(1, id);
            // ps.setString(1, Integer.toString(id));
            ResultSet result = st.executeQuery(query);
            // System.out.println("Here " + result);
            while (result.next()) {
                arrayOfActivity.add(new Activity(result.getString("_id"), result.getString("userId"),
                        result.getString("farmId"), formattter.format(result.getDate("date")),
                        result.getString("action"),
                        result.getString("type"), result.getString("unit"), result.getDouble("quantity"),
                        result.getInt("field"), result.getInt("row")));
            }
            System.out.println(arrayOfActivity.size());
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return arrayOfActivity;
    }
}
