package bean;

public class UpkeepStaff {

    private String UpkeepSid;
    private String UpkeepSname;
    private String UpkeepPwd;

    // Constructors, getters, and setters

    public UpkeepStaff(String UpkeepSid, String UpkeepSname, String UpkeepPwd) {
        this.UpkeepSid = UpkeepSid;
        this.UpkeepSname = UpkeepSname;
        this.UpkeepPwd = UpkeepPwd;
    }

    public String getUpkeepSid() {
        return UpkeepSid;
    }

    public void setUpkeepSid(String UpkeepSid) {
        this.UpkeepSid = UpkeepSid;
    }

    public String getUpkeepSname() {
        return UpkeepSname;
    }

    public void setUpkeepSname(String UpkeepSname) {
        this.UpkeepSname = UpkeepSname;
    }

    public String getUpkeepPwd() {
        return UpkeepPwd;
    }

    public void setUpkeepPwd(String UpkeepPwd) {
        this.UpkeepPwd = UpkeepPwd;
    }

    // Other methods if needed
}
