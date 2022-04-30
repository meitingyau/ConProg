package FarmerSimulation;

public class User {
    private String userId;
    private String[] farmIds;
    private String name;
    private String email;
    private String password;
    private String phoneNum;

    public User(String userId, String[] farmsIDs, String name, String email, String password, String phoneNum) {
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

}
