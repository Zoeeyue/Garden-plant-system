package bean;

public class ViewMonitor {
    private String monitorID;
    private String plant_name;
    private String staffName;
    private String monitorTime;
    private String monitorAddr;
    private String deviceName;
    private String indexName;
    private String indexValue;

    // 有参构造函数
    public ViewMonitor(String monitorID, String plant_name, String staffName, String monitorTime,
                       String monitorAddr, String deviceName, String indexName, String indexValue) {
        this.monitorID = monitorID;
        this.plant_name = plant_name;
        this.staffName = staffName;
        this.monitorTime = monitorTime;
        this.monitorAddr = monitorAddr;
        this.deviceName = deviceName;
        this.indexName = indexName;
        this.indexValue = indexValue;
    }

    // Getter 和 Setter 方法
    public String getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(String monitorID) {
        this.monitorID = monitorID;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getMonitorAddr() {
        return monitorAddr;
    }

    public void setMonitorAddr(String monitorAddr) {
        this.monitorAddr = monitorAddr;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public void toPrint() {
        System.out.println("监测编号: " + monitorID);
        System.out.println("监测对象: " + plant_name);
        System.out.println("监测人员: " + staffName);
        System.out.println("监测时间: " + monitorTime);
        System.out.println("监测地点: " + monitorAddr);
        System.out.println("检测设备: " + deviceName);
        System.out.println("监测指标: " + indexName);
        System.out.println("监测值: " + indexValue);
    }
}

