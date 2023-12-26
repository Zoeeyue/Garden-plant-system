package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlantDetailView {
    private String plantId;
    private String plantName;
    private String features;
    private String cultivation_tech;
    private String value;
    private String creater;
    private Date createdTime;
    private Date updatedTime;
    private String familyName;
    private String alias;
    private String imageId;
    private String filename;
    private String place;
    private String detail;
    private String createdBy;
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getCultivation_tech() {
		return cultivation_tech;
	}
	public void setCultivation_tech(String cultivation_tech) {
		this.cultivation_tech = cultivation_tech;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreatedTime() {
        // 创建一个SimpleDateFormat对象，指定日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 使用format方法将Date对象转换为字符串
        String dateString = dateFormat.format(createdTime);
		return dateString;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getUpdatedTime() {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        // 使用format方法将Date对象转换为字符串
	        String dateString = dateFormat.format(updatedTime);
			return dateString;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    
    
    // 其他属性...

    // 省略构造函数和Getter/Setter方法
}
