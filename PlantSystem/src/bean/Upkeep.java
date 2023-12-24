package bean;

import java.util.Date;

// 定义Upkeep实体类
public class Upkeep {
    // 定义私有属性，对应数据库表的字段
    private String upkeepId; // 任务编号，主键
    private String upkeepName; // 任务名称
    private Date upkeepEtime; // 执行时间
    private String upkeepEplace; // 执行地点
    private String upkeepSiD; // 执行人员
    private String upkeepDes; // 任务描述
    private String plantId; // 养护对象
    private String upkeepProcess; // 养护进程

    // 定义构造方法，用于创建对象
    public Upkeep() {} // 空参构造方法
    public Upkeep(String upkeepId, String upkeepName, Date upkeepEtime, String upkeepEplace, String upkeepSiD, String upkeepDes, String plantId, String upkeepProcess) { // 全参构造方法
        this.upkeepId = upkeepId;
        this.upkeepName = upkeepName;
        this.upkeepEtime = upkeepEtime;
        this.upkeepEplace = upkeepEplace;
        this.upkeepSiD = upkeepSiD;
        this.upkeepDes = upkeepDes;
        this.plantId = plantId;
        this.upkeepProcess = upkeepProcess;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getUpkeepId() {
        return upkeepId;
    }
    public void setUpkeepId(String upkeepId) {
        this.upkeepId = upkeepId;
    }
    public String getUpkeepName() {
        return upkeepName;
    }
    public void setUpkeepName(String upkeepName) {
        this.upkeepName = upkeepName;
    }
    public Date getUpkeepEtime() {
        return upkeepEtime;
    }
    public void setUpkeepEtime(Date upkeepEtime) {
        this.upkeepEtime = upkeepEtime;
    }
    public String getUpkeepEplace() {
        return upkeepEplace;
    }
    public void setUpkeepEplace(String upkeepEplace) {
        this.upkeepEplace = upkeepEplace;
    }
    public String getUpkeepSiD() {
        return upkeepSiD;
    }
    public void setUpkeepSiD(String upkeepSiD) {
        this.upkeepSiD = upkeepSiD;
    }
    public String getUpkeepDes() {
        return upkeepDes;
    }
    public void setUpkeepDes(String upkeepDes) {
        this.upkeepDes = upkeepDes;
    }
    public String getPlantId() {
        return plantId;
    }
    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
    public String getUpkeepProcess() {
        return upkeepProcess;
    }
    public void setUpkeepProcess(String upkeepProcess) {
        this.upkeepProcess = upkeepProcess;
    }
}

