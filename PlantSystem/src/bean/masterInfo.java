package bean;


public class masterInfo {
    private String masterId; //主管编号
    private String master_name;//主管姓名
    private String master_password;//主管密码

    public masterInfo() {

    }
    public masterInfo(String masterId,String master_name,String master_password) {
        this.masterId = masterId;
        this.setMaster_name(master_name);
        this.setMaster_password(master_password);
    }
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getMaster_name() {
		return master_name;
	}
	public void setMaster_name(String master_name) {
		this.master_name = master_name;
	}
	public String getMaster_password() {
		return master_password;
	}
	public void setMaster_password(String master_password) {
		this.master_password = master_password;
	}

}