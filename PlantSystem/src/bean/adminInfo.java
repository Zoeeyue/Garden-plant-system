package bean;


public class adminInfo {
    private String adminId; //管理员编号
    private String name;//管理员姓名
    private String password;//管理员密码

    public adminInfo() {

    }
    public adminInfo(String adminId,String name,String password) {
        this.adminId = adminId;
        this.name=name;
        this.password=password;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}