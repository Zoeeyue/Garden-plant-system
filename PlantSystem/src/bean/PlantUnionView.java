package bean;


public class PlantUnionView {
    private String plantId;
    private String plantName;
    private String features;
    private String cultivationTechniques;
    private String value;
    private String createdBy;
    private String Filename;
    private String familyName;
    private String alias;
    private String growEnv;
    private String genusName;
    private String uTaskId;
    private String upkeepName;
    private String upkeepDes;
    private String upkeepPlace;
    private String upkeepSname;
    private String upkeepPwd;

    // 构造函数和 Getter/Setter 方法

    public PlantUnionView(String plantId, String plantName, String features, String cultivationTechniques,
                          String value, String createdBy, String familyName, String alias, String growEnv,
                          String genusName, String uTaskId, String upkeepName, String upkeepDes,
                         String upkeepPlace, String upkeepSname, String upkeepPwd) {
        this.setPlantId(plantId);
        this.setPlantName(plantName);
        this.setFeatures(features);
        this.setCultivationTechniques(cultivationTechniques);
        this.setValue(value);
        this.setCreatedBy(createdBy);
        this.setFamilyName(familyName);
        this.setAlias(alias);
        this.setGrowEnv(growEnv);
        this.setGenusName(genusName);
        this.setuTaskId(uTaskId);
        this.setUpkeepName(upkeepName);
        this.setUpkeepDes(upkeepDes);
        this.setUpkeepPlace(upkeepPlace);
        this.setUpkeepSname(upkeepSname);
        this.setUpkeepPwd(upkeepPwd);
    }

	public PlantUnionView() {
		// TODO Auto-generated constructor stub
	}



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

	public String getCultivationTechniques() {
		return cultivationTechniques;
	}

	public void setCultivationTechniques(String cultivationTechniques) {
		this.cultivationTechniques = cultivationTechniques;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getGrowEnv() {
		return growEnv;
	}

	public void setGrowEnv(String growEnv) {
		this.growEnv = growEnv;
	}

	public String getGenusName() {
		return genusName;
	}

	public void setGenusName(String genusName) {
		this.genusName = genusName;
	}

	public String getuTaskId() {
		return uTaskId;
	}

	public void setuTaskId(String uTaskId) {
		this.uTaskId = uTaskId;
	}

	public String getUpkeepName() {
		return upkeepName;
	}

	public void setUpkeepName(String upkeepName) {
		this.upkeepName = upkeepName;
	}

	public String getUpkeepDes() {
		return upkeepDes;
	}

	public void setUpkeepDes(String upkeepDes) {
		this.upkeepDes = upkeepDes;
	}

	public String getUpkeepPlace() {
		return upkeepPlace;
	}

	public void setUpkeepPlace(String upkeepPlace) {
		this.upkeepPlace = upkeepPlace;
	}

	public String getUpkeepSname() {
		return upkeepSname;
	}

	public void setUpkeepSname(String upkeepSname) {
		this.upkeepSname = upkeepSname;
	}

	public String getUpkeepPwd() {
		return upkeepPwd;
	}

	public void setUpkeepPwd(String upkeepPwd) {
		this.upkeepPwd = upkeepPwd;
	}

	public String getFilename() {
		// TODO Auto-generated method stub
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

    // 其他属性、构造函数、Getter/Setter 方法...

}
