package bean;
//异常表
public class error {
	private String indexID;//异常指标编号
	
    public error( String indexID) {  
        this.indexID = indexID;
    }
  
    public String get_indexID() {  
        return this.indexID;  
    }  
  
    public void set_indexID(String indexID) {  
        this.indexID = indexID;  
    } 
}