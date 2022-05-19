package FarmerSimulation;

public class Fertilizer {
    private String fertilizerId;
    private String name;
    private String unitType;

    public Fertilizer(String fertilizerId, String name, String unitType) {
        this.fertilizerId = fertilizerId;
        this.name = name;
        this.unitType = unitType;
    }

    public String toString() {
        String str = "Fertilizer ID: " + fertilizerId + ", Fertilizer Name: " + name + ", Unit Type: "
                + unitType ;
        return str;
    }
}
