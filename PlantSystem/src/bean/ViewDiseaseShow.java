package bean;

public class ViewDiseaseShow {
    private String id;
    private String plant_name;
    private String diseaseName;
    private String treatmentName;
    private String medicineName;

    // 有参构造函数
    public ViewDiseaseShow(String id, String plant_name, String diseaseName, String treatmentName,String medicineName) {
        this.id = id;
        this.plant_name = plant_name;
        this.diseaseName = diseaseName;
        this.treatmentName = treatmentName;
        this.medicineName = medicineName;
    }

    // Getter 和 Setter 方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void toPrint() {
        System.out.println(id+"\t"+plant_name+"\t"+diseaseName+"\t"+treatmentName+"\t"+medicineName);
    }
}
