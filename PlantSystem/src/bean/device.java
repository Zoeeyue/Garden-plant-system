package bean;
//监测设备表
public class device {
	private String deviceID;//设备编号
	private String deviceName;//设备名称
	
    public device(String deviceID, String deviceName) {  
        this.deviceID = deviceID;
        this.deviceName = deviceName;
    }  
	
    public String get_deviceID() {  
        return this.deviceID;  
    }
  
    public void set_deviceID(String deviceID) {  
        this.deviceID = deviceID;  
    }  
  
    public String get_deviceName() {  
        return this.deviceName;  
    }  
  
    public void set_deviceName(String deviceName) {  
        this.deviceName = deviceName;  
    }  
}
