package FarmerSimulation;

import java.util.Random;
import java.sql.*;
import java.util.*;
import java.sql.ResultSet;

interface FarmerSimulatorInterface{
    Farmer[] generateFarmers(int numberOfFarmers);
}

public class FarmerSimulator implements FarmerSimulatorInterface {

    static public synchronized void simulateActivity(String farmer) {
        try {

            // connect with our local database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();
            
            //to get random farm for selected userId
            String userId = farmer;
            String sqlStrRandFarm = "select U.*,F._id from users U join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId where U._id="
                    + userId + " order by rand() limit 1";
            ResultSet rsRandFarm = st.executeQuery(sqlStrRandFarm);

            //set a starting date for the first activity 
            String sDate1 = "2020-12-31";
            DateGenerator dg = new DateGenerator(sDate1);
            String date = "";
            int n = 1;

            if (rsRandFarm.next()) {
                String randFarmId = rsRandFarm.getString("F._id");

                while (n <= 1000) { // if wan to generate 1000 activities change to 1000
                    String sqlLastAcId = "select max(cast(`_id` as unsigned)) as `actId` from activities";
                    ResultSet rsLastAcId = st.executeQuery(sqlLastAcId);
                    String nextAcId = "1";
                    if (rsLastAcId.next()) {
                        int lastAcId = rsLastAcId.getInt("actId");
                        nextAcId = String.valueOf(lastAcId + 1);
                    }

                    String action = "";
                    int randQty = getFieldRowQty();
                    int randField = getFieldRowQty();
                    int randRow = getFieldRowQty();

                    String randType = "", randUnit = "";

                    //used stored procedure to handle the sequence of the activity actions
                    //the stored procedure is stored in the database
                    //we set the sequence as sowing > pesticide > fertilizer > harvest > sales
                    //we use callable statement to use the stored procedure in our database
                    CallableStatement statement = conn.prepareCall("{call setNextAction(?,?,?)}");
                    statement.setString(1, randFarmId);
                    statement.setInt(2, randField);
                    statement.setInt(3, randRow);
                    boolean hadResults = statement.execute();
                    if (hadResults) {
                        ResultSet rsGetNextAction = statement.getResultSet();
                        if (rsGetNextAction.next()) {
                            action = rsGetNextAction.getString("next_activity");
                            sDate1 = rsGetNextAction.getString("act_date");
                            dg = new DateGenerator(sDate1);
                            randType = rsGetNextAction.getString("plant");
                            randUnit = rsGetNextAction.getString("pl_unit");
                        }
                    }
                    statement.close();

                    //we set a range for to randomly get a duration for the next activity
                    //only duration for harvest is set as 7-14 days
                    //other actions' duration are set as 1-2 days
                    int randDays;
                    if (action.equals("harvest")) {
                        randDays = getRandDays(7, 7);
                        dg.setDays(randDays);
                        date = dg.addDays();
                    } else {
                        randDays = getRandDays(2, 1);
                        dg.setDays(randDays);
                        date = dg.addDays();
                    }

                    //if it is a sowing action, randomly get the plant to be sowed
                    if (action.equals("sowing")) {
                        String sqlStrRandType = "select F.*,PL.* from farms F " +
                                "left join farmplants FPL on F._id=FPL.farmId " +
                                "inner join plants PL on FPL.plantId=PL._id " +
                                "where F._id=" + randFarmId + " order by rand() limit 1";
                        ResultSet rsRandType = st.executeQuery(sqlStrRandType);

                        if (rsRandType.next()) {
                            randType = rsRandType.getString("PL.name");
                            randUnit = rsRandType.getString("PL.unitType");

                        }

                    }

                    //if it is a pesticide action, randomly get the pesticide
                    else if (action.equals("pesticide")) {
                        String sqlStrRandType = "select F.*,PE.* from farms F " +
                                "left join farmpesticide FPE on F._id=FPE.farmId " +
                                "inner join pesticide PE on FPE.pesticideId=PE._id " +
                                "where F._id=" + randFarmId + " order by rand() limit 1";
                        ResultSet rsRandType = st.executeQuery(sqlStrRandType);

                        if (rsRandType.next()) {
                            randType = rsRandType.getString("PE.name");
                            randUnit = rsRandType.getString("PE.unitType");

                        }
                    }

                    //if it is a fertilizer action, randomly get the fertilizer
                    else if (action.equals("fertilizer")) {
                        String sqlStrRandType = "select F.*,FE.* from farms F " +
                                "left join farmfertilizers FFE on F._id=FFE.farmId " +
                                "inner join fertilizers FE on FFE.fertilizerId=FE._id " +
                                "where F._id=" + randFarmId + " order by rand() limit 1";
                        ResultSet rsRandType = st.executeQuery(sqlStrRandType);

                        if (rsRandType.next()) {
                            randType = rsRandType.getString("FE.name");
                            randUnit = rsRandType.getString("FE.unitType");

                        }
                    }

                    //COMMENTED LINES TO PRINT THE DATA IN THE TERMINAL TO BE ADDED TO THE ACTIVITY TABLE

                    // System.out.println("User id: " + userId);
                    // System.out.println("Activity id: " + nextAcId);
                    // System.out.println("Random farm id: " + randFarmId);
                    // System.out.println("Action: " + action);
                    // System.out.println("After how many days: " + randDays);
                    // System.out.println("Date: " + date);
                    // System.out.println("Type: " + randType);
                    // System.out.println("Unit: " + randUnit);
                    // System.out.println("Quantity: " + randQty);
                    // System.out.println("Field: " + randField);
                    // System.out.println("Row: " + randRow);

                    Activity activity = new Activity(nextAcId, userId, randFarmId, date, action, randType,
                            randUnit,
                            randQty, randField, randRow);
                    activity.store();
                    activity.toString();

                    n++;
                }
            }

            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }

    }


    //generateFarmers method to randomly generate an array of Farmer object
    public Farmer[] generateFarmers(int numberOfFarmers) {
        Farmer[] farmers;
        farmers = new Farmer[numberOfFarmers];
        try {

            // connect with our local database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm",
                    "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();
            String sqlStrUser = "select U.*,F._id from users U join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId order by rand() limit "
                    + numberOfFarmers + "";
            ResultSet rsUser = st.executeQuery(sqlStrUser);

            // create lists to store the farms for respective user id
            List<String> farms = new ArrayList<String>();
            List<Farmer> farmer = new ArrayList<Farmer>();

            String tempUserId = "";
            String tempUserName = "";
            String tempUserEmail = "";
            String tempUserPW = "";
            String tempUserPhone = "";
            String tempFarmId = "";

            // run through each row in the table
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
                    // convert the lists to array
                    String[] farmIds = new String[farms.size()];
                    farms.toArray(farmIds);
                    Farmer user = new Farmer(tempUserId, farmIds, tempUserName, tempUserEmail, tempUserPW,
                            tempUserPhone);
                    farms.clear();
                    farmer.add(user);
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
                    farmer.add(user);
                }

                tempUserId = userId;
                tempUserName = name;
                tempUserEmail = email;
                tempUserPW = password;
                tempUserPhone = phoneNum;
                tempFarmId = farmId;
            }

            Farmer[] farmerArr = new Farmer[farmer.size()];
            farmer.toArray(farmerArr);
            conn.close();
            farmers = farmerArr;
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return farmers;
    }

    //method to get random field/row quantity, set each farm has 10 rows and 10 fields
    public static int getFieldRowQty() {
        Random r = new Random();
        int rand = r.nextInt(10) + 1;
        return rand;
    }

    //method to randomly get the duration for next activity
    public static int getRandDays(int range, int startNum) {
        Random r = new Random();
        int rand = r.nextInt(range) + startNum;
        return rand;
    }
}
