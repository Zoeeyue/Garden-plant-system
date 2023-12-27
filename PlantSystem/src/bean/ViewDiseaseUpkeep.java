package bean;
public class ViewDiseaseUpkeep {
    private String UTaskId;
    private String plant_name;
    private String diseaseName;
    private String treatmentName;
    private String medicineName;
    private String UTaskStatus;

    // 有参构造方法
    public ViewDiseaseUpkeep(String UTaskId, String plant_name, String diseaseName,
                              String treatmentName, String medicineName, String UTaskStatus) {
        this.UTaskId = UTaskId;
        this.plant_name = plant_name;
        this.diseaseName = diseaseName;
        this.treatmentName = treatmentName;
        this.medicineName = medicineName;
        this.UTaskStatus = UTaskStatus;
    }

    // Getter 方法
    public String getUTaskId() {
        return UTaskId;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getUTaskStatus() {
        return UTaskStatus;
    }

    // Setter 方法
    public void setUTaskId(String UTaskId) {
        this.UTaskId = UTaskId;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setUTaskStatus(String UTaskStatus) {
        this.UTaskStatus = UTaskStatus;
    }

	public void toPrint() {
        System.out.println("养护任务: " + UTaskId);
        System.out.println("植物名称: " + plant_name);
        System.out.println("病虫害名: " + diseaseName);
        System.out.println("防治方法: " + treatmentName);
        System.out.println("药剂名称: " + medicineName);
        System.out.println("养护状态: " + UTaskStatus);
        System.out.println("——————————————————————————————————————");
		
	}
}
