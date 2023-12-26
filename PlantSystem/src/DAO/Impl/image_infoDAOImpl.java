package DAO.Impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.image_infoDAO;
import comm.DButil;
import bean.ImageInfo;
import bean.PlantInfo;

public class image_infoDAOImpl implements image_infoDAO {

    @Override
    public boolean insertImage(ImageInfo img) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        if(getImageInfoByImageId(img.getImageId())!=null) {
        	System.out.println("该配图编号已存在，无法插入，请更换！");
        	return false;
        }
        String sql = "INSERT INTO image_info (image_id, image_filename, place, detail, image_taker,plant_id) VALUES (?, ?, ?, ?, ?,?)";
        List<Object> list = new ArrayList<>();
        list.add(img.getImageId());
        list.add(img.getFilename());
        list.add(img.getPlace());
        list.add(img.getDetail());
        list.add(img.getCreatedBy());
        list.add(img.getPlantId());

        boolean result = db.excute(sql, list, conn);
        db.close(conn);
        return result;
    }

    @Override
    public void deleteImg(String img_id) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        String sql = "DELETE FROM image_info WHERE image_id = ?";
        List<Object> list = new ArrayList<>();
        list.add(img_id);

        db.excute(sql, list, conn);
        db.close(conn);
        System.out.println("配图删除成功！");
    }

    @Override
    public void updateImg(ImageInfo img) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        String sql = "UPDATE image_info SET image_filename=?, place=?, detail=?, image_taker=? WHERE image_id=?";
        List<Object> list = new ArrayList<>();
        list.add(img.getFilename());
        list.add(img.getPlace());
        list.add(img.getDetail());
        list.add(img.getCreatedBy());
        list.add(img.getImageId());

        db.excute(sql, list, conn);
        db.close(conn);
        System.out.println("更新成功！");
    }
    
    @Override
    public ImageInfo getImageInfoByImageId(String img_id) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM image_info WHERE image_id = ?";
        // 执行查询
        // 参数列表
	    List<Object> list = new ArrayList<>();
	    list.add(img_id);
	    List<Map<String, String>> result = db.excutequery(sql, list, conn);
        db.close(conn);

        // 如果查询结果非空，将结果封装为 PlantInfo 对象并返回
	    if (!result.isEmpty()) {
	        Map<String, String> imageInfoMap = result.get(0); // 一个植物编号只查询到一个结果
	        ImageInfo imageInfo = new ImageInfo();
	        
	        // 设置 PlantInfo 对象的属性
	        imageInfo.setImageId(imageInfoMap.get("image_id"));
	        imageInfo.setFilename(imageInfoMap.get("image_filename"));
	        imageInfo.setPlantId(imageInfoMap.get("plant_id"));
	        imageInfo.setPlace(imageInfoMap.get("place"));
	        imageInfo.setDetail(imageInfoMap.get("detail"));
	        imageInfo.setCreatedBy(imageInfoMap.get("image_taker"));


	        return imageInfo;
	    }

	    // 如果查询结果为空，返回 null 或抛出异常，取决于业务需求
	    return null;
    }

    @Override
    public List<ImageInfo> getImageInfoByPlantId(String plant_id) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM image_info WHERE plant_id = ?";
        List<Object> list = new ArrayList<>();
        list.add(plant_id);

        List<Map<String, String>> imgList = db.excutequery(sql, list, conn);
        db.close(conn);

        List<ImageInfo> resultList = new ArrayList<>();
        for (Map<String, String> imgInfoMap : imgList) {
            ImageInfo imageInfo = new ImageInfo();
            
            // 设置 ImageInfo 对象的属性
            imageInfo.setImageId(imgInfoMap.get("image_id"));
            imageInfo.setFilename(imgInfoMap.get("image_filename"));
//            imageInfo.setPlantId(imgInfoMap.get("plant_id"));
            // 设置其他属性...

            resultList.add(imageInfo);
        }

        return resultList;
    }

    
}

