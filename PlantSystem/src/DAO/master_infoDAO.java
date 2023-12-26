package DAO;

import java.util.List;
import java.util.Map;
import bean.adminInfo;
import bean.masterInfo;


public interface master_infoDAO {

    /**
     * 增加上级主管基本信息到数据库
     * @param 
     * @return
     */
    public boolean insertMaster(masterInfo m) throws Exception;


    /**
     * 根据上级主管id删除管理员数据
     * @param 
     * @throws Exception
     */
    public void deleteMaster(String master_id) throws Exception;

    /**
     * 更新上级主管数据
     * @param 
     * @return
     * @throws Exception
     */
    public void updateMaster(masterInfo a) throws Exception;

    /**
     * 根据上级主管id查找管理员
     * @param 
     * @return
     * @throws Exception
     */
    public masterInfo getMasterById(String master_id) throws Exception;

    /**
     * 根据上级主管用户名和密码进行登录验证
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public masterInfo login(String username, String password) throws Exception;
     



}