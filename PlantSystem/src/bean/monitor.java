package monitor;
//监测表
public class monitor {
	private String monitorTime;//监测时间
	private String monitorAddr;//监测地点
	private String deviceID;//设备编号
	private String plantID;//对象编号
	private String staffID;//员工编号
	
    public monitor(String monitorTime, String monitorAddr, String deviceID, String plantID, String staffID) {  
        this.monitorTime = monitorTime;  
        this.monitorAddr = monitorAddr;
        this.deviceID = deviceID;  
        this.plantID = plantID;  
        this.staffID = staffID;  
    }  
	
	public String get_monitorTime() {  
        return this.monitorTime;  
    }  
  
    public void set_monitorTime(String monitorTime) {  
        this.monitorTime = monitorTime;  
    }  
  
    public String get_monitorAddr() {  
        return this.monitorAddr;  
    }  
  
    public void set_monitorAddr(String monitorAddr) {  
        this.monitorAddr = monitorAddr;  
    }  
  
    public String get_deviceID() {  
        return this.deviceID;  
    }  
  
    public void set_deviceID(String deviceID) {  
        this.deviceID = deviceID;  
    }  
  
    public String get_plantID() {  
        return this.plantID;  
    }  
  
    public void set_plantID(String plantID) {  
        this.plantID = plantID;  
    }  
  
    public String get_staffID() {  
        return this.staffID;  
    }  
  
    public void set_staffID(String staffID) {  
        this.staffID = staffID;  
    }  
}
