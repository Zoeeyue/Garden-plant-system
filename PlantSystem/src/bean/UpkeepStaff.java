package bean;

// 定义UpkeepStaff实体类
public class UpkeepStaff {
    // 定义私有属性，对应数据库表的字段
    private String upkeepSiD; // 养护人员编号，主键
    private String upkeepSname; // 养护人员姓名
    private String upkeepSpwd; // 养护人员密码

    // 定义构造方法，用于创建对象
    public UpkeepStaff() {} // 空参构造方法
    public UpkeepStaff(String upkeepSiD, String upkeepSname, String upkeepSpwd) { // 全参构造方法
        this.upkeepSiD = upkeepSiD;
        this.upkeepSname = upkeepSname;
        this.upkeepSpwd = upkeepSpwd;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getUpkeepSiD() {
        return upkeepSiD;
    }
    public void setUpkeepSiD(String upkeepSiD) {
        this.upkeepSiD = upkeepSiD;
    }
    public String getUpkeepSname() {
        return upkeepSname;
    }
    public void setUpkeepSname(String upkeepSname) {
        this.upkeepSname = upkeepSname;
    }
    public String getUpkeepSpwd() {
        return upkeepSpwd;
    }
    public void setUpkeepSpwd(String upkeepSpwd) {
        this.upkeepSpwd = upkeepSpwd;
    }
}

