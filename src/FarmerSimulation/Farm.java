package FarmerSimulation;

public class Farm {
    private String farmId;
    private String name;
    private String address;
    private String[] plantIds;
    private String[] fertilizerIds;
    private String[] pesticideIds;

    public Farm(String farmId, String name, String address,
            String[] Plants, String[] fertilizers, String[] pesticides) {
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
}
