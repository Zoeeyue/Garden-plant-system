package bean;
//病虫害表
public class disease {
	private String diseaseID;//病虫害编号
	private String diseaseName;//病虫害名称
	private String plantID;//植物编号
	
    public disease(String diseaseID, String diseaseName, String plantID) {
        this.diseaseID = diseaseID;
        this.diseaseName = diseaseName;
        this.plantID = plantID;
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
	
	public String get_plantID() {
		return this.plantID;
	}
	public void set_plantID(String plantID) {
		this.plantID = plantID;
	}
}
