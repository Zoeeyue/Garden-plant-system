package bean;

// 定义City实体类
public class City {
    // 定义私有属性，对应数据库表的字段
    private String cityId; // 城市编号，主键
    private String cityName; // 城市名称，非空
    private String provinceId; // 所属省份编号，外键

    // 定义构造方法，用于创建对象
    public City() {} // 空参构造方法
    public City(String cityId, String cityName, String provinceId) { // 全参构造方法
        this.cityId = cityId;
        this.cityName = cityName;
        this.provinceId = provinceId;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getCityId() {
        return cityId;
    }
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
