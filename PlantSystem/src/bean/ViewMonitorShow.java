package bean;

public class ViewMonitorShow {
    private String id;
    private String plant_name;
    private String indexName;

    // 有参构造函数
    public ViewMonitorShow(String id, String plant_name, String indexName) {
        this.id = id;
        this.plant_name = plant_name;
        this.indexName = indexName;
    }

    // Getter 和 Setter 方法
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void toPrint() {
        System.out.println(id+"\t"+plant_name+"\t"+indexName);
    }
}
