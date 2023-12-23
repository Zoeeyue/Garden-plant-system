package monitor;
//监测人员表
public class staff {
	String staffID;//员工编号
	String staffName;//员工姓名
	String staffPwd;//员工密码

    public staff(String staffID, String staffName, String staffPwd) {  
        this.staffID = staffID;  
        this.staffName = staffName;  
        this.staffPwd = staffPwd;  
    }  
  
    public String get_staffID() {  
        return this.staffID;  
    }  
    
    public void set_staffID(String staffID) {  
        this.staffID = staffID;  
    }  
    
    public String get_staffName() {  
        return this.staffName;  
    }    
    
    public void set_staffName(String staffName) {  
        this.staffName = staffName;  
    }  
  
    public String get_staffPwd() {  
        return this.staffPwd;  
    }  
  
    public void set_staffPwd(String staffPwd) {  
        this.staffPwd = staffPwd;  
    }  
}
