package DAO;

import java.util.List;
import java.util.Map;
import bean.adminInfo;


public interface admin_infoDAO {

    /**
     * 增加管理员基本信息到数据库
     * @param 
     * @return
     */
    public boolean insertAdmin(adminInfo a) throws Exception;


    /**
     * 根据管理员id删除管理员数据
     * @param 
     * @throws Exception
     */
    public void deleteAdmin(String admin_id) throws Exception;

    /**
     * 更新管理员数据
     * @param 
     * @return
     * @throws Exception
     */
    public void updateAdmin(adminInfo a) throws Exception;

    /**
     * 根据管理员id查找管理员
     * @param admin_id
     * @return
     * @throws Exception
     */
    public adminInfo getAdminById(String admin_id) throws Exception;

    /**
     * 根据管理员用户名和密码进行登录验证
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public adminInfo login(String username, String password) throws Exception;
     



}