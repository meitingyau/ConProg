package FarmerSimulation;

import java.util.Arrays;

public class Farmer implements Runnable {
    volatile boolean exit;
    String userId;
    private String[] farmIds;
    private String name;
    private String email;
    private String password;
    private String phoneNum;
    private String str;

    public Farmer(String userId, String[] farmIds, String name, String email, String password, String phoneNum) {
        this.userId = userId;
        this.farmIds = farmIds;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        // this.exit = false;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.name;
    }

    public String[] getFarmIds() {
        return farmIds;
    }

    public String toString() {
        str = "User ID: " + userId + ", Farm IDs: " + Arrays.toString(farmIds) + ", Name: " + name + ", Email: "
                + email + " Phone Num: " + phoneNum;
        // UseFileLogger.logInfoMessage(str);
        return str;
    }

    public void run() {
        //  while (!exit) {
            try {
                FarmerSimulator.simulateActivity(this.userId.toString());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        // }
        // System.out.println("Stop");
    }

    public void stop() {
        exit = true;
    }
}
