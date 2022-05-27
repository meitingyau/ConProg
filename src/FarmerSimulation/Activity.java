package FarmerSimulation;

import java.io.*;
import java.sql.*;

public class Activity {
    private String activityId;
    private String userId;
    private String farmId;
    private String date;
    private String action;
    private String type;
    private String unit;
    private double quantity;
    private int field;
    private int row;

    public Activity(String activityId, String userId, String farmId, String date, String action, String type,
            String unit, double quantity, int field, int row) {
        this.activityId = activityId;
        this.userId = userId;
        this.farmId = farmId;
        this.date = date;
        this.action = action;
        this.type = type;
        this.unit = unit;
        this.quantity = quantity;
        this.field = field;
        this.row = row;
    }

    public void store() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "root", "");

            PreparedStatement ps = con.prepareStatement(
                    "insert into filetable values(?,?)");

            File f = new File("d:\\myfile.txt");
            FileReader fr = new FileReader(f);

            ps.setInt(1, 101);
            ps.setCharacterStream(2, fr, (int) f.length());
            int i = ps.executeUpdate();
            System.out.println(i + " records affected");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
