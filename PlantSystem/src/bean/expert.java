package bean;

public class expert {
	private String expertID;//员工编号
	private String expertName;//员工姓名
	private String expertPwd;//员工密码
	
    public expert(String expertID, String expertName, String expertPwd) {  
        this.expertID = expertID;  
        this.expertName = expertName;  
        this.expertPwd = expertPwd;
    }  
	
	public String get_expertID() {
		return expertID;
	}
	public void set_expertID(String expertID) {
		this.expertID = expertID;
	}
	public String get_expertName() {
		return expertName;
	}
	public void set_expertName(String expertName) {
		this.expertName = expertName;
	}
	public String get_expertPwd() {
		return expertPwd;
	}
	public void set_expertPwd(String expertPwd) {
		this.expertPwd = expertPwd;
	}
}
