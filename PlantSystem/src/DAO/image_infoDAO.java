package DAO;

import java.util.List;
import java.util.Map;
import bean.ImageInfo;
import bean.PlantInfo;


public interface image_infoDAO {

    /**
     * 增加配图基本信息到数据库
     * @param 
     * @return
     */
    public boolean insertImage(ImageInfo img) throws Exception;


    /**
     * 根据配图id删除
     * @param 
     * @throws Exception
     */
    public void deleteImg(String img_id) throws Exception;

    /**
     * 更新
     * @param 
     * @return
     * @throws Exception
     */
    public void updateImg(ImageInfo img) throws Exception;
    
    /**
     * 根据植物id查找对应配图信息
     * @param 
     * @return
     */
    
    public  List<ImageInfo> getImageInfoByPlantId(String plant_id) throws Exception;

    /**
     * 根据配图id查找对应配图信息
     * @param 
     * @return
     */
	public ImageInfo getImageInfoByImageId(String img_id) throws Exception;
    


}
