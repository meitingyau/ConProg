package FarmerSimulation.Stores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PesticideStore {
    public HashMap<String, String> getPesticideByFarm(int farmId) throws SQLException {
        // String sql = "SELECT _id, name FROM `farms`";
        String sql = "select PE.* from farms F " +
                "left join farmpesticide FPE on F._id=FPE.farmId " +
                "inner join pesticide PE on FPE.pesticideId=PE._id " +
                "where F._id=" + Integer.toString(farmId);
        ResultSet result = null;
        HashMap<String, String> plantsWithNameAndId = new HashMap<String, String>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ifarm", "root", "");
            Statement st = conn.createStatement();
            result = st.executeQuery(sql);
            while (result.next()) {
                plantsWithNameAndId.put(result.getString("_id"), result.getString("name"));
            }
            List<Map.Entry<String, String>> list = new LinkedList<Map.Entry<String, String>>(
                    plantsWithNameAndId.entrySet());
            // Sort the list
            Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));
            // put data from sorted list to hashmap
            HashMap<String, String> temp = new LinkedHashMap<String, String>();
            for (Entry<String, String> aa : list) {
                temp.put(aa.getKey(), aa.getValue());
            }
            plantsWithNameAndId = temp;
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return plantsWithNameAndId;
    }
}
