package Disease;
//药剂表
public class medicine {
	private String medicineID;//药剂编号
	private String medicineName;//药剂名称
	private String medicineDosage;//药剂用量
	private String medicineDuration;//作用期限
	
    public medicine(String medicineID, String medicineName, String medicineDosage, String medicineDuration) {  
        this.medicineID = medicineID;
        this.medicineName = medicineName;  
        this.medicineDosage = medicineDosage;
        this.medicineDuration = medicineDuration;
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
}
