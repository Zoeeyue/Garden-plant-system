package bean;

// 定义Sort实体类
public class Sort {
    // 定义私有属性，对应数据库表的字段
    private String speciesId; // 物种编号，外键
    private String countyId; // 县级编号，外键
    private String plantId; // 所属植物，外键
    private String growEnv; // 生长环境
    private String alias; // 别名

    // 定义构造方法，用于创建对象
    public Sort() {} // 空参构造方法
    public Sort(String speciesId, String countyId, String plantId, String growEnv, String alias) { // 全参构造方法
        this.speciesId = speciesId;
        this.countyId = countyId;
        this.plantId = plantId;
        this.growEnv = growEnv;
        this.alias = alias;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getSpeciesId() {
        return speciesId;
    }
    public void setSpeciesId(String speciesId) {
        this.speciesId = speciesId;
    }
    public String getCountyId() {
        return countyId;
    }
    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }
    public String getPlantId() {
        return plantId;
    }
    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
    public String getGrowEnv() {
        return growEnv;
    }
    public void setGrowEnv(String growEnv) {
        this.growEnv = growEnv;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
}

