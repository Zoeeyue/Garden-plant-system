package bean;

// 定义County实体类
public class County {
    // 定义私有属性，对应数据库表的字段
    private String countyId; // 县级编号，主键
    private String countyName; // 县级名称，非空
    private String cityId; // 所属城市编号，外键

    // 定义构造方法，用于创建对象
    public County() {} // 空参构造方法
    public County(String countyId, String countyName, String cityId) { // 全参构造方法
        this.countyId = countyId;
        this.countyName = countyName;
        this.cityId = cityId;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getCountyId() {
        return countyId;
    }
    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }
    public String getCountyName() {
        return countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    public String getCityId() {
        return cityId;
    }
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}

