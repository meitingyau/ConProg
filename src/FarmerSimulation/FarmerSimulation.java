package FarmerSimulation;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class FarmerSimulation {

    public static void main(String args[]) {
        try {
            //connect with our local database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8111/ifarm", "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();

            //connect farm class with database
            System.out.println("=============================================\nFarms");
            //sql query for selecting all related columns and order according to farm id
            String sqlStrFarm = "select F.*,PL._id as plants,FE._id as fertilizers,PE._id as pesticides from farms F " +
                    "left join farmplants FPL on F._id=FPL.farmId " +
                    "inner join plants PL on FPL.plantId=PL._id " +
                    "left join farmfertilizers FFE on F._id=FFE.farmId " +
                    "inner join fertilizers FE on FFE.fertilizerId=FE._id " +
                    "left join farmpesticide FPE on F._id=FPE.farmId " +
                    "inner join pesticide PE on FPE.pesticideId=PE._id order by F._id";
            ResultSet rsFarm = st.executeQuery(sqlStrFarm);

            //create lists to store the plant, fertilizer and pesticide for respective farm id
            List<String> plants = new ArrayList<String>();
            List<String> fertilizers = new ArrayList<String>();
            List<String> pesticides = new ArrayList<String>();

            //create temporary variables to store previous row data
            String tempFId = "";
            String tempFarmName = "";
            String tempFarmAdd = "";
            String tempPlantId = "";
            String tempFerId = "";
            String tempPesId = "";

            //run through each row in the table
            while (rsFarm.next()) {
                //get the farm id for each row
                String farmId = rsFarm.getString("F._id");

                if (tempFId == "") {    //if it is the first row
                    //add current row data to respective lists
                    plants.add(rsFarm.getString("plants"));
                    fertilizers.add(rsFarm.getString("fertilizers"));
                    pesticides.add(rsFarm.getString("pesticides"));
                }
                
                if (farmId.equals(tempFId)) {   //if current row has the same id with previous row,
                                                    //means current row data are under the same id as previous
                    //if the list does not contain current row data, add the current data to list
                    if (plants.contains(rsFarm.getString("plants")) == false) {                        
                        plants.add(rsFarm.getString("plants"));
                    }
                    if (fertilizers.contains(rsFarm.getString("fertilizers")) == false) {
                        fertilizers.add(rsFarm.getString("fertilizers"));
                    }
                    if (pesticides.contains(rsFarm.getString("pesticides")) == false) {
                        pesticides.add(rsFarm.getString("pesticides"));
                    }
                    continue;       //jump to next row
                }

                //if the previous row data does not exist in the list, add previous row data to the list
                if (tempFId != "" && plants.contains(tempPlantId) == false) {
                    plants.add(tempPlantId);
                }
                if (tempFId != "" && fertilizers.contains(tempFerId) == false) {
                    fertilizers.add(tempFerId);
                }
                if (tempFId != "" && pesticides.contains(tempPesId) == false) {
                    pesticides.add(tempPesId);
                }

                //if it is not the first row and current row id is different from previous row id
                if (!tempFId.equals(farmId) && tempFId != "") {
                    //convert the lists to array
                    String[] plantIds = new String[plants.size()];
                    plants.toArray(plantIds);
                    String[] ferIds = new String[fertilizers.size()];
                    fertilizers.toArray(ferIds);
                    String[] pesIds = new String[pesticides.size()];
                    pesticides.toArray(pesIds);

                    //create new farm object for previous row id
                    Farm farm = new Farm(tempFId, tempFarmName, tempFarmAdd, plantIds, ferIds, pesIds);
                    //empty the lists
                    plants.clear();
                    fertilizers.clear();
                    pesticides.clear();
                    //print the object
                    System.out.println(farm.toString());
                }
                //before going to next row, set current row id as temp id
                tempFId = farmId;
                
                //get all column data for current row
                String farmName = rsFarm.getString("F.name");
                String farmAdd = rsFarm.getString("F.address");
                String plantId = rsFarm.getString("plants");
                String ferId = rsFarm.getString("fertilizers");
                String pesId = rsFarm.getString("pesticides");

                //set current row data as temp data which will be used when checking the farm id at next row
                tempFarmName = farmName;
                tempFarmAdd = farmAdd;
                tempPlantId = plantId;
                tempFerId = ferId;
                tempPesId = pesId;

            }


            //connect user class with database
            System.out.println("=============================================\nUsers");
            //sql query for selecting all related columns and order according to user id
            String sqlStrUser = "select U.*,F._id from users U join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId order by U._id limit 11";
            ResultSet rsUser = st.executeQuery(sqlStrUser);

            //create lists to store the farms for respective user id
            List<String> farms = new ArrayList<String>();
            //create temporary variables to store previous row data
            String tempUserId = "";
            String tempUserName = "";
            String tempUserEmail = "";
            String tempUserPW = "";
            String tempUserPhone = "";
            String tempFarmId = "";

            //run through each row in the table
            while (rsUser.next()) {
                //get the user id for each row
                String userId = rsUser.getString("U._id");
                //get all column data for current row

                String name = rsUser.getString("U.name");
                String email = rsUser.getString("U.email");
                String password = rsUser.getString("U.password");
                String phoneNum = rsUser.getString("U.phoneNumber");
                String farmId = rsUser.getString("F._id");
                if (tempUserId == "") {     //if it is the first row
                    //add current row data to farm list
                    farms.add(rsUser.getString("F._id"));
                }

                if (userId.equals(tempUserId)) {    //if current row has the same id with previous row,
                                                //means current row data are under the same id as previous
                    //add current row data to farm list
                    farms.add(rsUser.getString("F._id"));
                    continue;   //jump to next row
                }

                //if the previous row data does not exist in the farm list, add to the list
                if (tempUserId != "" && farms.contains(tempFarmId) == false) {
                    farms.add(tempFarmId);
                }

                //if it is not the first row and current row id is different from previous row id
                if (!tempUserId.equals(userId) && tempUserId != "") {
                    //convert the lists to array
                    String[] farmIds = new String[farms.size()];
                    farms.toArray(farmIds);
                    //create new user object for previous row id
                    User user = new User(tempUserId, farmIds, tempUserName, tempUserEmail, tempUserPW, tempUserPhone);
                    //clear the farm list
                    farms.clear();
                    //print the user object
                    System.out.println(user.toString());
                }
                if (rsUser.isLast() == true && userId.equals(tempUserId)) {
                    farms.add(rsUser.getString("F._id"));
                    System.out.println(45678);
                }
                if (rsUser.isLast() == true && tempUserId != "" && farms.contains(tempFarmId) == false) {
                    farms.add(tempFarmId);
                }
                if (rsUser.isLast() == true && !tempUserId.equals(userId) && tempUserId != "") {
                    //convert the lists to array
                    String[] farmIds = new String[farms.size()];
                    farms.toArray(farmIds);
                    //create new user object for previous row id
                    User user = new User(userId, farmIds, name, email, password, phoneNum);
                    //clear the farm list
                    farms.clear();
                    //print the user object
                    System.out.println(user.toString());
                }


                // farms.add(rsUser.getString("F._id"));


                //before going to next row, set current row id as temp id
                tempUserId = userId;
                
                
                // User obj = new User(userId, farmIds, name, email, password, phoneNum);

                
                //set current row data as temp data
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

            //connect pesticide class with database
            System.out.println("=============================================\nPesticides");
            //select only 20 rows to show the output first
            String sqlStrPesticide = "select * from pesticide limit 20";
            ResultSet rsPesticide = st.executeQuery(sqlStrPesticide);
            //run through each row in pesticide table and create pesticide object
            while (rsPesticide.next()) {
                String pesticideId = rsPesticide.getString("_id");
                String Pname = rsPesticide.getString("name");
                String PunitType = rsPesticide.getString("unitType");
                Pesticide pest = new Pesticide(pesticideId, Pname, PunitType);
                System.out.println(pest.toString());
            }

            //connect fertilizer class with database
            System.out.println("=============================================\nFertilizers");
            //select only 20 rows to show the output first
            String sqlStrFertilizer = "select * from fertilizers limit 20";
            ResultSet rsFertilizer = st.executeQuery(sqlStrFertilizer);
            //run through each row in fertilizer table and create fertilizer object
            while (rsFertilizer.next()) {
                String fertilizerId = rsFertilizer.getString("_id");
                String Fname = rsFertilizer.getString("name");
                String unitType = rsFertilizer.getString("unitType");
                Fertilizer fertilizer = new Fertilizer(fertilizerId, Fname, unitType);
                System.out.println(fertilizer.toString());
            }

            //connect plant class with database
            System.out.println("=============================================\nPlants");
            //select only 20 rows to show the output first
            String sqlStrPlant = "select * from plants limit 20";
            ResultSet rsPlant = st.executeQuery(sqlStrPlant);
            //run through each row in plant table and create plant object
            while (rsPlant.next()) {
                String plantId = rsPlant.getString("_id");
                String plantName = rsPlant.getString("name");
                String plunitType = rsPlant.getString("unitType");
                Plant plant = new Plant(plantId, plantName, plunitType);
                System.out.println(plant.toString());
            }

            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
    }
}