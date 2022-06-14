package FarmerSimulation;

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
    private String str;

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
        //Store activity to database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");

            PreparedStatement ps = con.prepareStatement(
                    "insert into activities (_id, farmId, userId, date, action, type, unit, quantity, field, row) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, this.activityId);
            ps.setString(2, this.farmId);
            ps.setString(3, this.userId);
            ps.setString(4, this.date);
            ps.setString(5, this.action);
            ps.setString(6, this.type);
            ps.setString(7, this.unit);
            ps.setDouble(8, this.quantity);
            ps.setInt(9, this.field);
            ps.setInt(10, this.row);

            int i = ps.executeUpdate();
            System.out.println(i + " record(s) affected");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        str = "Activity ID: " + activityId + ", User ID: " + userId + ", Farm ID: " + farmId + ", Date: " + date
                + ", Action: "
                + action + ", Type: " + type + ", Unit: " + unit + ", Quantity: " + quantity + ", Field: " + field
                + ", Row: " + row;

        FileLoggerMessage.logInfoMessage(str);
        return str;
    }

    public String getUserId() {
        return userId;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public int getField() {
        return field;
    }

    public int getRow() {
        return row;
    }

}
