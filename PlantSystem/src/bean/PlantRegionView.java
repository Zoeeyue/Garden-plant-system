package bean;

public class PlantRegionView {
    private String plantId;
    private String plantName;
    private String provinceName;
    private String cityName;
    private String countyName;

    // 空的构造函数
    public PlantRegionView() {
    }

    // 带参数的构造函数
    public PlantRegionView(String plantId, String plantName, String provinceName, String cityName, String countyName) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.countyName = countyName;
    }

    // Getter 和 Setter 方法
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

    public void displayProperties() {
        System.out.println("植物名称: " + plantName);
        System.out.println("省: " + provinceName);
        System.out.println("市: " + cityName);
        System.out.println("县: " + countyName);
    }
}
