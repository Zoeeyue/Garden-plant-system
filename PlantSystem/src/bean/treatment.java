package bean;
//防治方法表
public class treatment {
	private String treatmentID;//方法编号
	private String treatmentName;//方法名称
	private String treatmentCont;//方法内容
	private String diseaseID;//病虫害编号
	
    public treatment(String treatmentID, String treatmentName, String treatmentCont, String diseaseID) {  
        this.treatmentID = treatmentID;  
        this.treatmentName = treatmentName;
        this.treatmentName = treatmentCont;
        this.diseaseID = diseaseID;
    }  
	
	public String get_treatmentID() {  
        return this.treatmentID;  
    }  
  
    public void set_treatmentID(String treatmentID) {  
        this.treatmentID = treatmentID;  
    }  
  
    public String get_treatmentName() {  
        return this.treatmentName;  
    }  
  
    public void set_treatmentName(String treatmentName) {  
        this.treatmentName = treatmentName;  
    }  
  
    public String get_treatmentCont() {  
        return this.treatmentCont;  
    }  
  
    public void set_treatmentCont(String treatmentCont) {  
        this.treatmentCont = treatmentCont;  
    }  
  
    public String get_diseaseID() {  
        return this.diseaseID;  
    }  
  
    public void set_diseaseID(String diseaseID) {  
        this.diseaseID = diseaseID;  
    }  
}
