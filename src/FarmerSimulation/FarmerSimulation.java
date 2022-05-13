package FarmerSimulation;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class FarmerSimulation {

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8111/ifarm", "root", "");
            System.out.print("Database is connected !");
            Statement st = conn.createStatement();
            String sqlStrFarm = "select F.* from farms F";
            ResultSet rsFarm = st.executeQuery(sqlStrFarm);
            while (rsFarm.next()) {
                // System.out.println(rsFarm.getString("name"));
                String farmId = rsFarm.getString("_id");
                String FarmName = rsFarm.getString("name");
                String address = rsFarm.getString("address");
                // String name = rsFarm.getString("name");
                // String name = rsFarm.getString("name");

                // Farm obj = new Farm(farmId, FarmName, address);
            }
            String sqlStrUser = "select U.*,F.* from users U join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId";
            ResultSet rsUser = st.executeQuery(sqlStrUser);
            while(rsUser.next()){
                String userId = rsUser.getString("U._id"); 
                String farmIds;               
                String name = rsUser.getString("U.name");
                String email = rsUser.getString("U.email");
                String password = rsUser.getString("U.password");
                String phoneNum = rsUser.getString("U.phoneNumber");
                // User obj = new User(userId, farmIds, name, email, password, phoneNum);
            }


            
            String sqlStrFertilizer = "select * from fertilizers";
            ResultSet rsFertilizer = st.executeQuery(sqlStrFertilizer);
            while (rsFertilizer.next()) {
                String fertilizerId = rsFertilizer.getString("_id");
                String Fname = rsFertilizer.getString("name");
                String unitType = rsFertilizer.getString("unitType");
                Fertilizer obj = new Fertilizer(fertilizerId, Fname, unitType );
            }

            String sqlStrPesticide = "select * from pesticides";
            ResultSet rsPesticide = st.executeQuery(sqlStrPesticide);
            while (rsPesticide.next()) {
                String pesticideId = rsPesticide.getString("_id");
                String Pname = rsPesticide.getString("name");
                String PunitType = rsPesticide.getString("unitType");
                Pesticide obj =  new Pesticide(pesticideId, Pname, PunitType);
            }

            String sqlStrPlant = "select * from plants";
            ResultSet rsPlant = st.executeQuery(sqlStrPlant);
            while (rsPlant.next()) {
                String plantId = rsPlant.getString("_id");
                String plantName = rsPlant.getString("name");
                String plunitType = rsPlant.getString("unitType");
                Plant obj = new Plant(plantId, plantName, plunitType );
            }
            
            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
    }
}