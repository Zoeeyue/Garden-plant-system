package bean;

// 定义Genus实体类
public class Genus {
    // 定义私有属性，对应数据库表的字段
    private String genusName; // 属名
    private String genusId; // 属编号，主键
    private String familyId; // 所属科编号，外键

    // 定义构造方法，用于创建对象
    public Genus() {} // 空参构造方法
    public Genus(String genusName, String genusId, String familyId) { // 全参构造方法
        this.genusName = genusName;
        this.genusId = genusId;
        this.familyId = familyId;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getGenusName() {
        return genusName;
    }
    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }
    public String getGenusId() {
        return genusId;
    }
    public void setGenusId(String genusId) {
        this.genusId = genusId;
    }
    public String getFamilyId() {
        return familyId;
    }
    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }
}
