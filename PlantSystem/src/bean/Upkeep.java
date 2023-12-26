package bean;

import java.util.Date;

public class Upkeep {

    private String UpkeepId;
    private String UTaskId;
    private String plant_id;
    private String UpkeepName;
    private String UpkeepDes;
    private Date UpkeepTime;
    private String UpkeepPlace;
    private String UpkeepSiD;

    // Constructors

    public Upkeep() {
        // 默认构造函数
    }

    public Upkeep(String UpkeepId, String UTaskId, String plant_id, String UpkeepName, String UpkeepDes, Date UpkeepTime, String UpkeepPlace, String UpkeepSiD) {
        this.UpkeepId = UpkeepId;
        this.UTaskId = UTaskId;
        this.plant_id = plant_id;
        this.UpkeepName = UpkeepName;
        this.UpkeepDes = UpkeepDes;
        this.UpkeepTime = UpkeepTime;
        this.UpkeepPlace = UpkeepPlace;
        this.UpkeepSiD = UpkeepSiD;
    }

    // Getter and setter methods

    public String getUpkeepId() {
        return UpkeepId;
    }

    public void setUpkeepId(String UpkeepId) {
        this.UpkeepId = UpkeepId;
    }

    public String getUTaskId() {
        return UTaskId;
    }

    public void setUTaskId(String UTaskId) {
        this.UTaskId = UTaskId;
    }

    public String getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(String plant_id) {
        this.plant_id = plant_id;
    }

    public String getUpkeepName() {
        return UpkeepName;
    }

    public void setUpkeepName(String UpkeepName) {
        this.UpkeepName = UpkeepName;
    }

    public String getUpkeepDes() {
        return UpkeepDes;
    }

    public void setUpkeepDes(String UpkeepDes) {
        this.UpkeepDes = UpkeepDes;
    }

    public Date getUpkeepTime() {
        return UpkeepTime;
    }

    public void setUpkeepTime(Date UpkeepTime) {
        this.UpkeepTime = UpkeepTime;
    }

    public String getUpkeepPlace() {
        return UpkeepPlace;
    }

    public void setUpkeepPlace(String UpkeepPlace) {
        this.UpkeepPlace = UpkeepPlace;
    }

    public String getUpkeepSiD() {
        return UpkeepSiD;
    }

    public void setUpkeepSiD(String UpkeepSiD) {
        this.UpkeepSiD = UpkeepSiD;
    }

    // Other methods if needed
}
