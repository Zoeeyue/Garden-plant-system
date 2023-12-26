package bean;

public class PlantSortView {

    private String plantId;
    private String plantName;
    private String alias;
    private String familyName;
    private String genusName;
    private String provinceName;
    private String cityName;
    private String countyName;
    private String growEnv;

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGenusName() {
        return genusName;
    }

    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getGrowEnv() {
        return growEnv;
    }

    public void setGrowEnv(String growEnv) {
        this.growEnv = growEnv;
    }

    public void displayProperties() {
        System.out.println("植物名称: " + plantName);
        System.out.println("别名: " + alias);
        System.out.println("科: " + familyName);
        System.out.println("属: " + genusName);
        System.out.println("省: " + provinceName);
        System.out.println("市: " + cityName);
        System.out.println("县: " + countyName);
        System.out.println("生长环境: " + growEnv);
    }

    public void displayProperties2() {
        System.out.println(plantId+" "+plantName+" "+growEnv);
    }

}
