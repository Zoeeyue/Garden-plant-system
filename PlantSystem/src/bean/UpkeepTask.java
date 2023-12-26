package bean;

public class UpkeepTask {

    private String UTaskId;
    private String plantId;
    private String UTaskSid;
    private String UTaskDes;
    private String UTaskStatus;

    public UpkeepTask(String UTaskId, String plantId, String UTaskSid, String UTaskDes, String UTaskStatus) {
        this.UTaskId = UTaskId;
        this.plantId = plantId;
        this.UTaskSid = UTaskSid;
        this.UTaskDes = UTaskDes;
        this.UTaskStatus = UTaskStatus;
    }

    public UpkeepTask() {
        // 可以在这里初始化一些默认值
    }

    public String getUTaskId() {
        return UTaskId;
    }

    public void setUTaskId(String UTaskId) {
        this.UTaskId = UTaskId;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getUTaskSid() {
        return UTaskSid;
    }

    public void setUTaskSid(String UTaskSid) {
        this.UTaskSid = UTaskSid;
    }

    public String getUTaskDes() {
        return UTaskDes;
    }

    public void setUTaskDes(String UTaskDes) {
        this.UTaskDes = UTaskDes;
    }

    public String getUTaskStatus() {
        return UTaskStatus;
    }

    public void setUTaskStatus(String UTaskStatus) {
        this.UTaskStatus = UTaskStatus;
    }

}
