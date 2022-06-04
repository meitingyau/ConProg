package FarmerSimulation;

// import java.sql.DriverManager;
// import java.sql.Connection;
// import java.sql.Statement;
import java.util.Random;
import java.sql.*;

import java.sql.ResultSet;

public class FarmerSimulator {
    static public void simulateActivity() {
        try {

            // connect with our local database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ifarm", "root", "");
            System.out.println("Database is connected !");
            Statement st = conn.createStatement();

            String sqlLastAcId = "select max(cast(`_id` as unsigned)) as `actId` from activities";
            ResultSet rsLastAcId = st.executeQuery(sqlLastAcId);
            String nextAcId = "1";
            if (rsLastAcId.next()) {
                int lastAcId = rsLastAcId.getInt("actId");
                nextAcId = String.valueOf(lastAcId + 1);
            }
            System.out.println("Activity id: " + nextAcId);

            String randUserId = "14";
            System.out.println("User id: " + randUserId);
            String sqlStrRandFarm = "select U.*,F._id from users U join farmusers FU on U._id=FU.userId join farms F on F._id=FU.farmId where U._id="
                    + randUserId + " order by rand() limit 1";
            ResultSet rsRandFarm = st.executeQuery(sqlStrRandFarm);

            String sDate1 = "2020-12-31";
            // String sDate2 = "2022-05-13";
            DateGenerator dg = new DateGenerator(sDate1);
            // String randDate = dg.getRandomDate();
            String date = "";

            if (rsRandFarm.next()) {
                // String randFarmId = rsRandFarm.getString("F._id");
                String randFarmId = "3";
                System.out.println("Random farm id: " + randFarmId);

                // System.out.println("Date: " + randDate);
                String action = getAction();
                int randQty = getFieldRowQty();
                int randRow = getFieldRowQty();
                int randField = getFieldRowQty();
                // int randRow = 3;
                // int randField = 6;
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
                }
                else {
                    randDays = getRandDays(2, 1);
                    dg.setDays(randDays);
                    date = dg.addDays();
                }
                if (action.equals("sowing")/*  || action.equals("harvest") || action.equals("sales")*/) {
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
                //  else if (action.equals("harvest") || action.equals("sales")){

                //  }
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

                Activity activity = new Activity(nextAcId, randUserId, randFarmId, date, action, randType, randUnit,
                        randQty, randField, randRow);
                activity.store();
                activity.toString();

            }

            conn.close();
        } catch (Exception e) {
            System.out.print("Database Connection Error: " + e);
        }

    }

    private static String getAction() {
        Random r = new Random();
        int rand = r.nextInt(5) + 1;
        switch (rand) {
            case 1:
                return "sowing";
            case 2:
                return "pesticide";
            case 3:
                return "fertilizer";
            case 4:
                return "harvest";
            case 5:
                return "sales";
        }
        return "No action returned!!";
    }

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
