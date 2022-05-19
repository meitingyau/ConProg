package FarmerSimulation;

public class Pesticide {
    private String pesticideId;
    private String name;
    private String unitType;

    public Pesticide(String pesticideId, String name, String unitType) {
        this.pesticideId = pesticideId;
        this.name = name;
        this.unitType = unitType;
    }

    public String toString() {
        String str = "Pesticide: " + pesticideId + ", Name: " + name + ", Unit Type: " + unitType;
        return str;
    }
}