package FarmerSimulation;

import java.util.Arrays;

public class Farm {
    private String farmId;
    private String name;
    private String address;
    private String[] plantIds;
    private String[] fertilizerIds;
    private String[] pesticideIds;

    public Farm(String farmId, String name, String address,
            String[] plantIds, String[] fertilizerIds, String[] pesticideIds) {
        this.farmId = farmId;
        this.name = name;
        this.address = address;
        this.plantIds = plantIds;
        this.fertilizerIds = fertilizerIds;
        this.pesticideIds = pesticideIds;
    }

    public String[] getPlantIds() {
        return plantIds;
    }

    public String[] getFertilizerIds() {
        return fertilizerIds;
    }

    public String[] getPesticideIds() {
        return pesticideIds;
    }

    public String toString() {
        String str = "Farm ID: " + farmId + ", Farm Name: " + name + ", Address: " + address + ", \nPlant IDs: "
                + Arrays.toString(plantIds) + ", Fertilizer IDs: " + Arrays.toString(fertilizerIds) +
                ", Pesticide IDs: " + Arrays.toString(pesticideIds) + "\n";
        FileLoggerMessage.logInfoMessage(str);
        return str;
    }
}
