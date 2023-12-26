package bean;

public class ViewIndexs {
    private String indexName;
    private String maxValue;
    private String avgValue;

    // 有参构造函数
    public ViewIndexs(String indexName, String maxValue, String avgValue) {
        this.indexName = indexName;
        this.maxValue = maxValue;
        this.avgValue = avgValue;
    }

    // Getter 和 Setter 方法
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(String avgValue) {
        this.avgValue = avgValue;
    }

    public void toPrint() {
        System.out.println("监测指标名称: " + indexName);
        System.out.println("最大值: " + maxValue);
        System.out.println("平均值: " + avgValue);
    }
}
