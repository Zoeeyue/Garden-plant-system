package bean;
//异常表
public class error {
	private String plantID;//监测对象编号
	private String indexID;//异常指标编号
	
    public error(String plantID, String indexID) {  
        this.plantID = plantID;
        this.indexID = indexID;
    }  
	
    public String get_plantID() {  
        return this.plantID;  
    }  
  
    public void set_plantID(String plantID) {  
        this.plantID = plantID;  
    }  
  
    public String get_indexID() {  
        return this.indexID;  
    }  
  
    public void set_indexID(String indexID) {  
        this.indexID = indexID;  
    }  
}
