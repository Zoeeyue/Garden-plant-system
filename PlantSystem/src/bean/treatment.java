package Disease;
//防治方法表
public class treatment {
	private String treatmentID;//方法编号
	private String treatmentName;//方法名称
	private String treatmentCont;//方法内容
	private String medicineID;//药剂编号
	
    public treatment(String treatmentID, String treatmentName, String treatmentCont, String medicineID) {  
        this.treatmentID = treatmentID;  
        this.treatmentName = treatmentName;
        this.treatmentName = treatmentCont;
        this.medicineID = medicineID;
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
  
    public String get_medicineID() {  
        return this.medicineID;  
    }  
  
    public void set_medicineID(String medicineID) {  
        this.medicineID = medicineID;  
    }  
}
