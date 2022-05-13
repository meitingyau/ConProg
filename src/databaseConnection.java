import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class databaseConnection {
    public static void main(String args[]) {\
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8111/ifarm", "root", "");
            System.out.print("Database is connected !");
            Statement st = conn.createStatement();
            String sqlStr = "select * from farms";
            ResultSet rs = st.executeQuery(sqlStr);
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
    }
}