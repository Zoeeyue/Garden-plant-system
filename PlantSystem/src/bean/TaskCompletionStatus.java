package bean;

public class TaskCompletionStatus {
    private String UTaskId;
    private String UTaskSid;

    public TaskCompletionStatus(String UTaskId, String UTaskSid) {
        this.UTaskId = UTaskId;
        this.UTaskSid = UTaskSid;
    }

    public String getUTaskId() {
        return UTaskId;
    }

    public void setUTaskId(String UTaskId) {
        this.UTaskId = UTaskId;
    }

    public String getUTaskSid() {
        return UTaskSid;
    }

    public void setUTaskSid(String UTaskSid) {
        this.UTaskSid = UTaskSid;
    }

    @Override
    public String toString() {
        return "任务ID: " + UTaskId + ", 负责人员ID: " + UTaskSid;
    }
}
