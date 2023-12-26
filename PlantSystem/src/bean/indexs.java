package bean;
//监测指标
public class indexs {
	private String indexID;//指标编号
	private String indexName;//指标名称
	private String indexValue;//指标值
	private String monitorID;//监测编号
	
    public indexs(String indexID, String indexName, String indexValue, String monitorID) {  
        this.indexID = indexID;
        this.indexName = indexName;
        this.indexValue = indexValue;
        this.monitorID = monitorID;
    }  
	
    public String get_indexID() {  
        return this.indexID;  
    }  
  
    public void set_indexID(String indexID) {  
        this.indexID = indexID;  
    }  
  
    public String get_indexName() {  
        return this.indexName;  
    }  
  
    public void set_indexName(String indexName) {  
        this.indexName = indexName;  
    }  
  
    public String get_indexValue() {  
        return this.indexValue;  
    }  
  
    public void set_indexValue(String indexValue) {  
        this.indexValue = indexValue;  
    }  
	
    public String get_monitorID() {  
        return this.monitorID;  
    }  
  
    public void set_monitorID(String monitorID) {  
        this.monitorID = monitorID;  
    }  
}
