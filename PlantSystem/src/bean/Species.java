package bean;

// 定义Species实体类
public class Species {
    // 定义私有属性，对应数据库表的字段
    private String speciesName; // 种名
    private String speciesId; // 种编号，主键
    private String genusId; // 所属属编号，外键

    // 定义构造方法，用于创建对象
    public Species() {} // 空参构造方法
    public Species(String speciesName, String speciesId, String genusId) { // 全参构造方法
        this.speciesName = speciesName;
        this.speciesId = speciesId;
        this.genusId = genusId;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getSpeciesName() {
        return speciesName;
    }
    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }
    public String getSpeciesId() {
        return speciesId;
    }
    public void setSpeciesId(String speciesId) {
        this.speciesId = speciesId;
    }
    public String getGenusId() {
        return genusId;
    }
    public void setGenusId(String genusId) {
        this.genusId = genusId;
    }
}
