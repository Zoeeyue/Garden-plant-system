package bean;

import java.util.Date;

public class PlantInfo {
	    private String plantId;
	    private String plantName;
	    private String diseaseID;//植物病编号
	    private String features;
	    private String cultivationTechniques;
	    private String value;
	    private String imageId;
	    private String createdBy;
	    private Date createdTime;
	    private Date updatedTime;

	  
	    public PlantInfo() {
	        
	    }

	    public PlantInfo(String plantId, String plantName, String diseaseID,
	                     String features, String cultivationTechniques, String value,
	                     String imageId, String createdBy, Date createdTime, Date updatedTime) {
	        this.plantId = plantId;
	        this.plantName = plantName;
	        this.diseaseID=diseaseID;
	        this.features = features;
	        this.cultivationTechniques = cultivationTechniques;
	        this.value = value;
	        this.imageId = imageId;
	        this.createdBy = createdBy;
	        this.createdTime = createdTime;
	        this.updatedTime = updatedTime;
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

	    public String getImageId() {
	        return imageId;
	    }

	    public void setImageId(String imageId) {
	        this.imageId = imageId;
	    }

	    public String getCreatedBy() {
	        return createdBy;
	    }

	    public void setCreatedBy(String createdBy) {
	        this.createdBy = createdBy;
	    }

	    public Date getCreatedTime() {
	        return createdTime;
	    }

	    public void setCreatedTime(Date createdTime) {
	        this.createdTime = createdTime;
	    }

	    public Date getUpdatedTime() {
	        return updatedTime;
	    }

	    public void setUpdatedTime(Date updatedTime) {
	        this.updatedTime = updatedTime;
	    }

		public String getDiseaseID() {
			return diseaseID;
		}

		public void setDiseaseID(String diseaseID) {
			this.diseaseID = diseaseID;
		}
	}

