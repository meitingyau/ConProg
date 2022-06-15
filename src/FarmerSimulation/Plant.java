package FarmerSimulation;

public class Plant {
    private String plantId;
    private String name;
    private String unitType;

    public Plant(String plantId,String name,String unitType){
        this.plantId=plantId;
        this.name=name;
        this.unitType=unitType;
    }
    public String toString() {
        String str = "Plant: " + plantId + ", Name: " + name + ", Unit Type: " + unitType + "\n";
        FileLoggerMessage.logInfoMessage(str);
        return str;
    }
}
