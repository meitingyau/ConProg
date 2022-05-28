package FarmerSimulation;

import java.util.Arrays;

public class User {
    private String userId;
    private String[] farmIds;
    private String name;
    private String email;
    private String password;
    private String phoneNum;
    private String str;

    public User(String userId, String[] farmIds, String name, String email, String password, String phoneNum) {
        this.userId = userId;
        this.farmIds = farmIds;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
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
}
