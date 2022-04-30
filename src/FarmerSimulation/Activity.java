package FarmerSimulation;

public class Activity {
    private String activityId;
    private String userId;
    private String farmId;
    private String date;
    private String action;
    private String type;
    private String unit;
    private double quantity;
    private int field;
    private int row;

    public Activity(String activityId, String userId, String farmId, String date, String action, String type,
            String unit, double quantity, int field, int row) {
        this.activityId = activityId;
        this.userId = userId;
        this.farmId = farmId;
        this.date = date;
        this.action = action;
        this.type = type;
        this.unit = unit;
        this.quantity = quantity;
        this.field = field;
        this.row = row;
    }

}
