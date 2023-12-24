package bean;

// 定义Province实体类
public class Province {
    // 定义私有属性，对应数据库表的字段
    private String provinceId; // 省份ID
    private String provinceName; // 省份名称

    // 定义构造方法，用于创建对象
    public Province() {} // 空参构造方法
    public Province(String provinceId, String provinceName) { // 全参构造方法
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }

    // 定义setter和getter方法，用于属性的赋值和取值
    public String getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
