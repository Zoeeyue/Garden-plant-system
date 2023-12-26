package bean;

public class ViewError {
    private String plant_name;
    private String indexName;
    private String indexValue;
    private String monitorTime;

    // 有参构造函数
    public ViewError(String plant_name, String indexName, String indexValue, String monitorTime) {
        this.plant_name = plant_name;
        this.indexName = indexName;
        this.indexValue = indexValue;
        this.monitorTime = monitorTime;
    }

    // Getter 和 Setter 方法
    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        this.monitorTime = monitorTime;
    }

    public void toPrint() {
        System.out.println("监测对象: " + plant_name);
        System.out.println("监测时间: " + monitorTime);
        System.out.println("监测指标: " + indexName);
        System.out.println("监测值: " + indexValue);
    }

}
