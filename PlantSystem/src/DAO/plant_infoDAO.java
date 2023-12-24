package DAO;

import java.util.List;
import java.util.Map;

import bean.PlantInfo;

public interface plant_infoDAO {

    /**
     * 增加植物基本信息到数据库
     * @param pf
     * @return
     */
    public boolean insertPlant(PlantInfo pf) throws Exception;


    /**
     * 根据植物id删除植物数据
     * @param plant_id
     * @throws Exception
     */
    public void deletePlant(String plant_id) throws Exception;

    /**
     * 更新植物数据
     * @param plant_id
     * @return
     * @throws Exception
     */
    public void updatePlant(PlantInfo pf) throws Exception;

    /**
     * 根据植物id查找
     * @param plant_id
     * @return
     */
    public PlantInfo getPlantInfoById(String plant_id) throws Exception;


    /**
     * 查询所有植物
     * @return
     * @throws Exception
     */
    public List queryAllPf() throws Exception;

    /**
     * 根据用户需要统计平台中每科植物的数量，支持不同科名植物的视图。通过视图来查
     * @param specifiedScientificName，用户指定科名
     * @return
     */
    public Map<String, Integer> countPlantsByScientificName(String specifiedScientificName) throws Exception;

}