package bean;

public class ViewDisease {
    private String id;
    private String plant_name;
    private String diseaseName;
    private String treatmentName;
    private String medicineName;
    private String medicineDosage;
    private String medicineDuration;

    // 有参构造函数
    public ViewDisease(String id, String plant_name, String diseaseName, String treatmentName,
                       String medicineName, String medicineDosage, String medicineDuration) {
        this.id = id;
        this.plant_name = plant_name;
        this.diseaseName = diseaseName;
        this.treatmentName = treatmentName;
        this.medicineName = medicineName;
        this.medicineDosage = medicineDosage;
        this.medicineDuration = medicineDuration;
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

    public String getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(String medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public String getMedicineDuration() {
        return medicineDuration;
    }

    public void setMedicineDuration(String medicineDuration) {
        this.medicineDuration = medicineDuration;
    }
    
    public void toPrint() {
        System.out.println("编号: " + id);
        System.out.println("植物名称: " + plant_name);
        System.out.println("病虫害名: " + diseaseName);
        System.out.println("防治方法: " + treatmentName);
        System.out.println("药剂名称: " + medicineName);
        System.out.println("药剂用量: " + medicineDosage);
        System.out.println("作用期限: " + medicineDuration);
        System.out.println("——————————————————————————————————————");
    }
}
