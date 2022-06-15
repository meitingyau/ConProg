package FarmerSimulation;

import java.sql.DriverManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class PrintDataFromDatabase {

    public static void main(String args[]) {
        try {
            try {
                FileLogger.setupFileInDB();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Problems with creating the log files");
            }
            // connect with our local database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();

            // print data for farms in the database
            String farmm = "\n=============================================\nFarms\n\n";
            System.out.println(farmm);
            FileLoggerMessage.logInfoMessage(farmm);
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
                String farmName = rsFarm.getString("F.name");
                String farmAdd = rsFarm.getString("F.address");
                String pId = rsFarm.getString("plants");
                String ferId = rsFarm.getString("fertilizers");
                String pesId = rsFarm.getString("pesticides");

                if (tempFId == "") { 
                    plants.add(rsFarm.getString("plants"));
                    fertilizers.add(rsFarm.getString("fertilizers"));
                    pesticides.add(rsFarm.getString("pesticides"));
                }

                if (farmId.equals(tempFId) && rsFarm.isLast() == false) { 
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
                    
                    farm.toString();
                }
                if (rsFarm.isLast() == true && farmId.equals(tempFId) && fertilizers.contains(tempFerId) == true && pesticides.contains(tempPesId) == true) {
                    plants.add(rsFarm.getString("plants"));
                }

                if (rsFarm.isLast() == true) {
                    String[] plantIds = new String[plants.size()];
                    plants.toArray(plantIds);
                    plants.clear();
                    String[] ferIds = new String[fertilizers.size()];
                    fertilizers.toArray(ferIds);
                    fertilizers.clear();
                    String[] pesIds = new String[pesticides.size()];
                    pesticides.toArray(pesIds);
                    pesticides.clear();
                    Farm farm = new Farm(farmId, farmName, farmAdd, plantIds, ferIds, pesIds);
                    farm.toString();
                }
                tempFId = farmId;
                tempFarmName = farmName;
                tempFarmAdd = farmAdd;
                tempPlantId = pId;
                tempFerId = ferId;
                tempPesId = pesId;

            }

            // print data for Farmers in the database
            String far = "\n=============================================\nFarmers\n\n";
            System.out.println(far);
            FileLoggerMessage.logInfoMessage(far);
            String sqlStrUser = "select U.*,F._id from users U join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId order by U._id limit 10";
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
                String name = rsUser.getString("U.name");
                String email = rsUser.getString("U.email");
                String password = rsUser.getString("U.password");
                String phoneNum = rsUser.getString("U.phoneNumber");
                String farmId = rsUser.getString("F._id");
                
                if (tempUserId == "") { 
                    farms.add(rsUser.getString("F._id"));
                }

                if (userId.equals(tempUserId) && rsUser.isLast() == false) { 
                    farms.add(rsUser.getString("F._id"));
                    continue; 
                }

                if (tempUserId != "" && farms.contains(tempFarmId) == false) {
                    farms.add(tempFarmId);
                }

                if ((!tempUserId.equals(userId)) && tempUserId != "") {
                    String[] farmIds = new String[farms.size()];
                    farms.toArray(farmIds);
                    Farmer user = new Farmer(tempUserId, farmIds, tempUserName, tempUserEmail, tempUserPW, tempUserPhone);
                    farms.clear();
                    user.toString();
                }
                if (rsUser.isLast() == true && userId.equals(tempUserId)) {
                    farms.add(rsUser.getString("F._id"));
                }
                if (rsUser.isLast() == true && tempUserId != "" && farms.contains(tempFarmId) == false) {
                    farms.add(tempFarmId);
                }

                if (rsUser.isLast() == true) {

                    String[] farmIds = new String[farms.size()];
                    farms.toArray(farmIds);
                    Farmer user = new Farmer(userId, farmIds, name, email, password, phoneNum);
                    farms.clear();
                    user.toString();
                }

                tempUserId = userId;
                tempUserName = name;
                tempUserEmail = email;
                tempUserPW = password;
                tempUserPhone = phoneNum;
                tempFarmId = farmId;

            }

            // print data for pesticides in the database
            String pes = "\n=============================================\nPesticides\n\n";
            System.out.println(pes);
            FileLoggerMessage.logInfoMessage(pes);
            // select only 20 rows to show the output first
            String sqlStrPesticide = "select * from pesticide limit 20";
            ResultSet rsPesticide = st.executeQuery(sqlStrPesticide);

            while (rsPesticide.next()) {
                String pesticideId = rsPesticide.getString("_id");
                String Pname = rsPesticide.getString("name");
                String PunitType = rsPesticide.getString("unitType");
                Pesticide pest = new Pesticide(pesticideId, Pname, PunitType);
                pest.toString();
            }

            // print data for fertilizers in the database
            String fer = "\n=============================================\nFertilizers\n\n";
            System.out.println(fer);
            FileLoggerMessage.logInfoMessage(fer);
            // select only 20 rows to show the output first
            String sqlStrFertilizer = "select * from fertilizers limit 20";
            ResultSet rsFertilizer = st.executeQuery(sqlStrFertilizer);

            while (rsFertilizer.next()) {
                String fertilizerId = rsFertilizer.getString("_id");
                String Fname = rsFertilizer.getString("name");
                String unitType = rsFertilizer.getString("unitType");
                Fertilizer fertilizer = new Fertilizer(fertilizerId, Fname, unitType);
                fertilizer.toString();
            }

            // print data for plants in the database
            String pl = "\n=============================================\nPlants\n\n";
            System.out.println(pl);
            FileLoggerMessage.logInfoMessage(pl);
            // select only 20 rows to show the output first
            String sqlStrPlant = "select * from plants limit 20";
            ResultSet rsPlant = st.executeQuery(sqlStrPlant);

            while (rsPlant.next()) {
                String plantId = rsPlant.getString("_id");
                String plantName = rsPlant.getString("name");
                String plunitType = rsPlant.getString("unitType");
                Plant plant = new Plant(plantId, plantName, plunitType);
                plant.toString();
            }

            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
    }
}