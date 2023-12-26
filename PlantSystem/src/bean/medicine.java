package bean;
//药剂表
public class medicine {
	private String medicineID;//药剂编号
	private String medicineName;//药剂名称
	private String medicineDosage;//药剂用量
	private String medicineDuration;//作用期限
	private String treatmentID;//方法编号
	
    public medicine(String medicineID, String medicineName, String medicineDosage, String medicineDuration, String treatmentID) {  
        this.medicineID = medicineID;
        this.medicineName = medicineName;  
        this.medicineDosage = medicineDosage;
        this.medicineDuration = medicineDuration;
        this.treatmentID = treatmentID;
    }  
	
    public String get_medicineID() {
        return this.medicineID;
    }
    public void set_medicineID(String medicineID) {
        this.medicineID = medicineID;
    }
    public String get_medicineName() {
        return this.medicineName;
    }
    public void set_medicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    public String get_medicineDosage() {
        return this.medicineDosage;
    }
	public void set_medicineDosage(String medicineDosage) {
		this.medicineDosage = medicineDosage;
	}
	public String get_medicineDuration() {
		return this.medicineDuration;
	}
	public void set_medicineDuration(String medicineDuration) {
		this.medicineDuration = medicineDuration;
	}
	public String get_treatmentID() {  
        return this.treatmentID;  
    }  
  
    public void set_treatmentID(String treatmentID) {  
        this.treatmentID = treatmentID;  
    } 
}
