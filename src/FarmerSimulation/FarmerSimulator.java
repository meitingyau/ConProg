package FarmerSimulation;

// import java.sql.DriverManager;
// import java.sql.Connection;
// import java.sql.Statement;
import java.util.Random;
import java.sql.*;
import java.util.*;
import java.sql.ResultSet;

// public class FarmerSimulator implements FarmerSimulatorInterface{
public class FarmerSimulator {
    static public synchronized void simulateActivity(String farmer) {
        try {

            // connect with our local database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();
            //Farmer[] farmers = generateFarmers(4);

            //for (int i = 0; i < farmers.length; i++) {
                //String userId = farmers[i].userId.toString();

                String userId = farmer;
                String sqlStrRandFarm = "select U.*,F._id from users U where not U._id='-' join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId where U._id="
                        + userId + " order by rand() limit 1";
                ResultSet rsRandFarm = st.executeQuery(sqlStrRandFarm);

                String sDate1 = "2020-12-31";
                // String sDate2 = "2022-05-13";
                DateGenerator dg = new DateGenerator(sDate1);
                // String randDate = dg.getRandomDate();
                String date = "";
                int n = 1;

                if (rsRandFarm.next()) {
                    String randFarmId = rsRandFarm.getString("F._id");
                    // String randFarmId = "3";

                    while (n <= 10) { // if wan to generate 1000 activities change to 1000
                        String sqlLastAcId = "select max(cast(`_id` as unsigned)) as `actId` from activities";
                        ResultSet rsLastAcId = st.executeQuery(sqlLastAcId);
                        String nextAcId = "1";
                        if (rsLastAcId.next()) {
                            int lastAcId = rsLastAcId.getInt("actId");
                            nextAcId = String.valueOf(lastAcId + 1);
                        }
                        System.out.println("User id: " + userId);
                        System.out.println("Activity id: " + nextAcId);

                        System.out.println("Random farm id: " + randFarmId);

                        // System.out.println("Date: " + randDate);
                        String action = "";
                        int randQty = getFieldRowQty();
                        int randField = getFieldRowQty();
                        int randRow = getFieldRowQty();
                        // int randField = 10;
                        // int randRow = 8;

                        String randType = "", randUnit = "";

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
                        System.out.println("Action: " + action);

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
                        if (action.equals("sowing")/* || action.equals("harvest") || action.equals("sales") */) {
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
                        // else if (action.equals("harvest") || action.equals("sales")){

                        // }
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
                        // System.out.println(randFarmId);
                        System.out.println("After how many days: " + randDays);
                        System.out.println("Date: " + date);
                        System.out.println("Type: " + randType);
                        System.out.println("Unit: " + randUnit);
                        System.out.println("Quantity: " + randQty);
                        System.out.println("Field: " + randField);
                        System.out.println("Row: " + randRow);

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

    public static Farmer[] generateFarmers(int numberOfFarmers) {
        Farmer[] users;
        users = new Farmer[numberOfFarmers];
        try {

            // connect with our local database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm",
                    "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();
            String sqlStrUser = "select U.*,F._id from users U where not U._id = '-' join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId order by rand() limit "
                    + numberOfFarmers + "";
            ResultSet rsUser = st.executeQuery(sqlStrUser);

            // create lists to store the farms for respective user id
            List<String> farms = new ArrayList<String>();
            List<Farmer> farmer = new ArrayList<Farmer>();
            // create temporary variables to store previous row data
            String tempUserId = "";
            String tempUserName = "";
            String tempUserEmail = "";
            String tempUserPW = "";
            String tempUserPhone = "";
            String tempFarmId = "";

            // run through each row in the table
            while (rsUser.next()) {
                // get the user id for each row
                String userId = rsUser.getString("U._id");
                // get all column data for current row

                String name = rsUser.getString("U.name");
                String email = rsUser.getString("U.email");
                String password = rsUser.getString("U.password");
                String phoneNum = rsUser.getString("U.phoneNumber");
                String farmId = rsUser.getString("F._id");
                if (tempUserId == "") { // if it is the first row
                    // add current row data to farm list
                    farms.add(rsUser.getString("F._id"));
                }

                if (userId.equals(tempUserId) && rsUser.isLast() == false) { // if current row has the same id with
                                                                             // previous row,
                    // means current row data are under the same id as previous
                    // add current row data to farm list
                    farms.add(rsUser.getString("F._id"));
                    continue; // jump to next row
                }

                // if the previous row data does not exist in the farm list, add to the list
                if (tempUserId != "" && farms.contains(tempFarmId) == false) {
                    farms.add(tempFarmId);
                }

                // if it is not the first row and current row id is different from previous row
                // id
                if ((!tempUserId.equals(userId)) && tempUserId != "") {
                    // convert the lists to array
                    String[] farmIds = new String[farms.size()];
                    farms.toArray(farmIds);
                    // create new user object for previous row id
                    Farmer user = new Farmer(tempUserId, farmIds, tempUserName, tempUserEmail, tempUserPW, tempUserPhone);
                    // clear the farm list
                    farms.clear();
                    // print the user object
                    // System.out.println(user.toString());
                    farmer.add(user);
                }
                if (rsUser.isLast() == true && userId.equals(tempUserId)) {
                    farms.add(rsUser.getString("F._id"));
                }
                if (rsUser.isLast() == true && tempUserId != "" && farms.contains(tempFarmId) == false) {
                    farms.add(tempFarmId);
                }

                if (rsUser.isLast() == true /* && (!tempUserId.equals(userId) || tempUserId.equals(userId)) */) {

                    // convert the lists to array
                    String[] farmIds = new String[farms.size()];
                    farms.toArray(farmIds);
                    // create new user object for previous row id
                    Farmer user = new Farmer(userId, farmIds, name, email, password, phoneNum);
                    // clear the farm list
                    farms.clear();
                    farmer.add(user);
                }

                // before going to next row, set current row id as temp id
                tempUserId = userId;

                // set current row data as temp data

                tempUserName = name;
                tempUserEmail = email;
                tempUserPW = password;
                tempUserPhone = phoneNum;
                tempFarmId = farmId;
            }
            Farmer[] farmerArr = new Farmer[farmer.size()];
            farmer.toArray(farmerArr);
            conn.close();
            users = farmerArr;
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }
        return users;
    }

    // private static String getAction() {
    // Random r = new Random();
    // int rand = r.nextInt(5) + 1;
    // switch (rand) {
    // case 1:
    // return "sowing";
    // case 2:
    // return "pesticide";
    // case 3:
    // return "fertilizer";
    // case 4:
    // return "harvest";
    // case 5:
    // return "sales";
    // }
    // return "No action returned!!";
    // }

    public static int getFieldRowQty() {
        Random r = new Random();
        int rand = r.nextInt(10) + 1;
        return rand;
    }

    public static int getRandDays(int range, int startNum) {
        Random r = new Random();
        int rand = r.nextInt(range) + startNum;
        return rand;
    }
}
