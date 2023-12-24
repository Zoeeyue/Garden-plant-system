package DAO;

import java.util.List;

import bean.PlantInfo;

public interface PlantInfoDAO {

    /**
     * 增加植物基本信息到数据库
     * @param pf
     * @return
     */
    boolean insertPlant(PlantInfo pf) throws Exception;

    /**
     * 根据帖子id查找回复
     * @param zt_id
     * @return
     * @throws Exception
     */
    public List queryHf(String zt_id) throws Exception;

    /**
     * 删除植物数据
     * @param hf_id
     * @throws Exception
     */
    public void deleteHf(String hf_id) throws Exception;

    /**
     * 根据用户id查找回复
     * @param user_id
     * @return
     */
    public List getPlantInfoById(String plant_id) throws Exception;

    /**
     * 更新植帖评论数
     * @param string
     * @param flag
     * @throws Exception
     */
    public boolean changeHf(String zt_id, boolean flag) throws Exception;

    /**
     * 查询所有回复
     * @return
     * @throws Exception
     */
    public List queryAllHf() throws Exception;

    /**
     * 条件查询回复内容
     * @param condition1
     * @param condition2
     * @return
     */
    public List queryHf(String condition1, String condition2) throws Exception;

}