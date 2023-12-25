package bean;

public class Sort {

    private String sortId;
    private String genusId;
    private String countyId;
    private String plantId;
    private String growEnv;
    private String alias;

    public Sort() {
        // 默认构造函数
    }

    public Sort(String sortId, String genusId, String countyId, String plantId, String growEnv, String alias) {
        this.sortId = sortId;
        this.genusId = genusId;
        this.countyId = countyId;
        this.plantId = plantId;
        this.growEnv = growEnv;
        this.alias = alias;
    }

    // Getter and Setter methods

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getGenusId() {
        return genusId;
    }

    public void setGenusId(String genusId) {
        this.genusId = genusId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getGrowEnv() {
        return growEnv;
    }

    public void setGrowEnv(String growEnv) {
        this.growEnv = growEnv;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    // You can add other methods or overrides as needed
}
