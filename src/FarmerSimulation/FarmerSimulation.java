package FarmerSimulation;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class FarmerSimulation {

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8111/ifarm", "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();
            // String sqlStrFarm = "select F.* from farms F";
            // ResultSet rsFarm = st.executeQuery(sqlStrFarm);
            // while (rsFarm.next()) {
            // // System.out.println(rsFarm.getString("name"));
            // String farmId = rsFarm.getString("_id");
            // String FarmName = rsFarm.getString("name");
            // String address = rsFarm.getString("address");
            // // String name = rsFarm.getString("name");
            // // String name = rsFarm.getString("name");

            // // Farm obj = new Farm(farmId, FarmName, address);
            // }
            String sqlStrUser = "select U.*,F._id from users U join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId order by U._id limit 20";
            ResultSet rsUser = st.executeQuery(sqlStrUser);
            List<String> farms = new ArrayList<String>();
            String tempUserId = "";
            String tempUserName = "";
            String tempUserEmail = "";
            String tempUserPW = "";
            String tempUserPhone = "";
            String tempFarmId = "";
            while (rsUser.next()) {
                String userId = rsUser.getString("U._id");
                // System.out.println("Current: " + userId);
                // System.out.println("Temp: " + tempUserId);
                if (tempUserId == "") {
                    farms.add(rsUser.getString("F._id"));
                }
                // else if (tempUserId != userId){
                // farms.add(rsUser.getString("F._id"));
                // }
                if (userId.equals(tempUserId)) {
                    farms.add(rsUser.getString("F._id"));
                    continue;
                }
                if (tempUserId != "" && farms.contains(tempFarmId) == false) {
                    farms.add(tempFarmId);
                }
                if (!tempUserId.equals(userId) && tempUserId != "") {
                    String[] farmIds = new String[farms.size()];
                    farms.toArray(farmIds);
                    User user = new User(tempUserId, farmIds, tempUserName, tempUserEmail, tempUserPW, tempUserPhone);
                    farms.clear();
                    System.out.println(user.toString());
                }
                if (rsUser.next() == false && userId.equals(tempUserId)) {
                    farms.add(rsUser.getString("F._id"));
                    System.out.println(123);
                }
                if (rsUser.next() == false && tempUserId != "" && farms.contains(tempFarmId) == false) {
                    farms.add(tempFarmId);
                }
                tempUserId = userId;

                // farms.add(rsUser.getString("F._id"));
                String name = rsUser.getString("U.name");
                String email = rsUser.getString("U.email");
                String password = rsUser.getString("U.password");
                String phoneNum = rsUser.getString("U.phoneNumber");
                String farmId = rsUser.getString("F._id");
                // User obj = new User(userId, farmIds, name, email, password, phoneNum);

                tempUserName = name;
                tempUserEmail = email;
                tempUserPW = password;
                tempUserPhone = phoneNum;
                tempFarmId = farmId;
                // rsUser.next();
                // if (tempUserId != rsUser.getString("U._id")) {
                // String[] farmIds = new String[farms.size()];
                // farms.toArray(farmIds);
                // User user = new User(userId, farmIds, name, email, password, phoneNum);
                // farms.clear();
                // System.out.println(user.toString());
                // }
                // rsUser.previous();
            }
            // String sqlStrFertilizer = "select * from fertilizers";
            // ResultSet rsFertilizer = st.executeQuery(sqlStrFertilizer);
            // while (rsFertilizer.next()) {
            // String fertilizerId = rsFertilizer.getString("_id");
            // String Fname = rsFertilizer.getString("name");
            // String unitType = rsFertilizer.getString("unitType");
            // Fertilizer obj = new Fertilizer(fertilizerId, Fname, unitType );
            // }

            // String sqlStrFertilizer = "select * from fertilizers";
            // ResultSet rsFertilizer = st.executeQuery(sqlStrFertilizer);
            // while (rsFertilizer.next()) {
            // String fertilizerId = rsFertilizer.getString("_id");
            // String Fname = rsFertilizer.getString("name");
            // String unitType = rsFertilizer.getString("unitType");
            // Fertilizer ferti = new Fertilizer(fertilizerId, Fname, unitType );
            // }

            // String sqlStrPesticide = "select * from pesticide limit 20";
            // ResultSet rsPesticide = st.executeQuery(sqlStrPesticide);
            // while (rsPesticide.next()) {
            // String pesticideId = rsPesticide.getString("_id");
            // String Pname = rsPesticide.getString("name");
            // String PunitType = rsPesticide.getString("unitType");
            // Pesticide pest = new Pesticide(pesticideId, Pname, PunitType);
            // System.out.println(pest.toString());
            // }

            // String sqlStrPlant = "select * from plants";
            // ResultSet rsPlant = st.executeQuery(sqlStrPlant);
            // while (rsPlant.next()) {
            // String plantId = rsPlant.getString("_id");
            // String plantName = rsPlant.getString("name");
            // String plunitType = rsPlant.getString("unitType");
            // Plant plant = new Plant(plantId, plantName, plunitType );
            // }

            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
    }
}