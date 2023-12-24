package bean;


public class ImageInfo {
    private String imageId;
    private String filename;
    private String place;
    private String detail;
    private String createdBy;

    public ImageInfo() {

    }

    public ImageInfo(String imageId,String filename,String place,String detail,String createdBy) {
        this.imageId = imageId;
        this.filename=filename;
        this.place=place;
        this.detail=detail;
        this.createdBy=createdBy;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageId() {
        return imageId;
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
}
