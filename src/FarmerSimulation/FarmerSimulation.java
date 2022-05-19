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
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();
            String sqlStrFarm = "select F.*,PL._id as plants,FE._id as fertilizers,PE._id as pesticides from farms F " +
                    "left join farmplants FPL on F._id=FPL.farmId " +
                    "inner join plants PL on FPL.plantId=PL._id " +
                    "left join farmfertilizers FFE on F._id=FFE.farmId " +
                    "inner join fertilizers FE on FFE.fertilizerId=FE._id " +
                    "left join farmpesticide FPE on F._id=FPE.farmId " +
                    "inner join pesticide PE on FPE.pesticideId=PE._id order by F._id";
            ResultSet rsFarm = st.executeQuery(sqlStrFarm);
            List<String> plants = new ArrayList<String>();
            List<String> fertilizers = new ArrayList<String>();
            List<String> pesticides = new ArrayList<String>();
            String tempFId = "";
            String tempFarmName = "";
            String tempFarmAdd = "";
            String tempPlantId = "";
            String tempFerId = "";
            String tempPesId = "";

            while (rsFarm.next()) {
                
                String farmId = rsFarm.getString("F._id");
                if (tempFId == "") {
                    plants.add(rsFarm.getString("plants"));
                    fertilizers.add(rsFarm.getString("fertilizers"));
                    pesticides.add(rsFarm.getString("pesticides"));
                }
                
                if (farmId.equals(tempFId)) {
                    if (plants.contains(rsFarm.getString("plants")) == false) {                        
                        plants.add(rsFarm.getString("plants"));
                    }
                    if (fertilizers.contains(rsFarm.getString("fertilizers")) == false) {
                        fertilizers.add(rsFarm.getString("fertilizers"));
                    }
                    if (pesticides.contains(rsFarm.getString("pesticides")) == false) {
                        pesticides.add(rsFarm.getString("pesticides"));
                    }
                    continue;
                }

                if (tempFId != "" && plants.contains(tempPlantId) == false) {
                    plants.add(tempPlantId);
                }
                if (tempFId != "" && fertilizers.contains(tempFerId) == false) {
                    fertilizers.add(tempFerId);
                }
                if (tempFId != "" && pesticides.contains(tempPesId) == false) {
                    pesticides.add(tempPesId);
                }

                if (!tempFId.equals(farmId) && tempFId != "") {
                    String[] plantIds = new String[plants.size()];
                    plants.toArray(plantIds);
                    String[] ferIds = new String[fertilizers.size()];
                    fertilizers.toArray(ferIds);
                    String[] pesIds = new String[pesticides.size()];
                    pesticides.toArray(pesIds);
                    Farm farm = new Farm(tempFId, tempFarmName, tempFarmAdd, plantIds, ferIds, pesIds);
                    plants.clear();
                    fertilizers.clear();
                    pesticides.clear();
                    System.out.println(farm.toString());
                }
                tempFId = farmId;
                
                String farmName = rsFarm.getString("F.name");
                String farmAdd = rsFarm.getString("F.address");
                String plantId = rsFarm.getString("plants");
                String ferId = rsFarm.getString("fertilizers");
                String pesId = rsFarm.getString("pesticides");

                
                tempFarmName = farmName;
                tempFarmAdd = farmAdd;
                tempPlantId = plantId;
                tempFerId = ferId;
                tempPesId = pesId;

            }





            // System.out.println("=============================================\nUsers");
            // String sqlStrUser = "select U.*,F._id from users U join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId order by U._id limit 20";
            // ResultSet rsUser = st.executeQuery(sqlStrUser);
            // List<String> farms = new ArrayList<String>();
            // String tempUserId = "";
            // String tempUserName = "";
            // String tempUserEmail = "";
            // String tempUserPW = "";
            // String tempUserPhone = "";
            // String tempFarmId = "";
            // while (rsUser.next()) {
            //     String userId = rsUser.getString("U._id");
            //     // System.out.println("Current: " + userId);
            //     // System.out.println("Temp: " + tempUserId);
            //     if (tempUserId == "") {
            //         farms.add(rsUser.getString("F._id"));
            //     }
            //     // else if (tempUserId != userId){
            //     // farms.add(rsUser.getString("F._id"));
            //     // }
            //     if (userId.equals(tempUserId)) {
            //         farms.add(rsUser.getString("F._id"));
            //         continue;
            //     }
            //     if (tempUserId != "" && farms.contains(tempFarmId) == false) {
            //         farms.add(tempFarmId);
            //     }
            //     if (!tempUserId.equals(userId) && tempUserId != "") {
            //         String[] farmIds = new String[farms.size()];
            //         farms.toArray(farmIds);
            //         User user = new User(tempUserId, farmIds, tempUserName, tempUserEmail, tempUserPW, tempUserPhone);
            //         farms.clear();
            //         System.out.println(user.toString());
            //     }
            //     tempUserId = userId;

            //     // farms.add(rsUser.getString("F._id"));
            //     String name = rsUser.getString("U.name");
            //     String email = rsUser.getString("U.email");
            //     String password = rsUser.getString("U.password");
            //     String phoneNum = rsUser.getString("U.phoneNumber");
            //     String farmId = rsUser.getString("F._id");
                

            //     tempUserName = name;
            //     tempUserEmail = email;
            //     tempUserPW = password;
            //     tempUserPhone = phoneNum;
            //     tempFarmId = farmId;
            //     // rsUser.next();
            //     // if (tempUserId != rsUser.getString("U._id")) {
            //     // String[] farmIds = new String[farms.size()];
            //     // farms.toArray(farmIds);
            //     // User user = new User(userId, farmIds, name, email, password, phoneNum);
            //     // farms.clear();
            //     // System.out.println(user.toString());
            //     // }
            //     // rsUser.previous();
            // }
            // System.out.println("=============================================\nPesticides");

            // String sqlStrPesticide = "select * from pesticide limit 20";
            // ResultSet rsPesticide = st.executeQuery(sqlStrPesticide);
            // while (rsPesticide.next()) {
            //     String pesticideId = rsPesticide.getString("_id");
            //     String Pname = rsPesticide.getString("name");
            //     String PunitType = rsPesticide.getString("unitType");
            //     Pesticide pest = new Pesticide(pesticideId, Pname, PunitType);
            //     System.out.println(pest.toString());
            // }
            // System.out.println("=============================================\nFertilizers");
            // String sqlStrFertilizer = "select * from fertilizers limit 20";
            // ResultSet rsFertilizer = st.executeQuery(sqlStrFertilizer);
            // while (rsFertilizer.next()) {
            //     String fertilizerId = rsFertilizer.getString("_id");
            //     String Fname = rsFertilizer.getString("name");
            //     String unitType = rsFertilizer.getString("unitType");
            //     Fertilizer fertilizer = new Fertilizer(fertilizerId, Fname, unitType);
            //     System.out.println(fertilizer.toString());
            // }
            // System.out.println("=============================================\nPlants");

            // String sqlStrPlant = "select * from plants limit 20";
            // ResultSet rsPlant = st.executeQuery(sqlStrPlant);
            // while (rsPlant.next()) {
            //     String plantId = rsPlant.getString("_id");
            //     String plantName = rsPlant.getString("name");
            //     String plunitType = rsPlant.getString("unitType");
            //     Plant plant = new Plant(plantId, plantName, plunitType);
            //     System.out.println(plant.toString());
            // }

            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
    }
}