package monitor;
//监测设备表
public class device {
	String deviceID;//设备编号
	String deviceName;//设备名称
	String indexID;//指标编号
	
    public device(String deviceID, String deviceName, String indexID) {  
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.indexID = indexID;
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
  
    public String get_indexID() {  
        return this.indexID;  
    }  
  
    public void set_indexID(String indexID) {  
        this.indexID = indexID;  
    }  
}
