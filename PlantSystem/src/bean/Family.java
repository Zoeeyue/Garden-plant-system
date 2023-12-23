package bean;

// 定义Family实体类
public class Family {
    // 定义私有属性，对应数据库表的字段
    private String familyName; // 科名
    private String familyId; // 科编号，主键

    // 定义构造方法，用于创建对象
    public Family() {} // 空参构造方法
    public Family(String familyName, String familyId) { // 全参构造方法
        this.familyName = familyName;
        this.familyId = familyId;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public String getFamilyId() {
        return familyId;
    }
    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }
}
