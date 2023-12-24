package Disease;
//病虫害表
public class disease {
	String diseaseID;//病虫害编号
	String diseaseName;//病虫害名称
	String treatmentID;//防治方法编号
	
    public disease(String diseaseID, String diseaseName, String treatmentID) {  
        this.diseaseID = diseaseID;
        this.diseaseName = diseaseName;
        this.treatmentID = treatmentID;  
    }  
	
	public String get_diseaseID() {
		return this.diseaseID;
	}
	public void set_diseaseID(String diseaseID) {
		this.diseaseID = diseaseID;
	}
	public String get_diseaseName() {
		return this.diseaseName;
	}
	public void set_diseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String get_treatmentID() {
		return this.treatmentID;
	}
	public void set_treatmentID(String treatmentID) {
		this.treatmentID = treatmentID;
	}
}
