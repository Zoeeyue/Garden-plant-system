package DAO.Impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.plant_infoDAO;
import comm.DButil;

import bean.PlantInfo;

public class plant_infoDAOImpl implements plant_infoDAO {

    @Override
    public List queryAllPf() throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM plant_info;";
        List rslist = new ArrayList();
        List list = new ArrayList();
        System.out.println(sql);
        rslist = db.excutequery(sql, list,conn);
        db.close(conn);
        System.out.println(rslist);
        return rslist;
    }

    @Override
    public boolean insertPlant(PlantInfo pf) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        String sql = "INSERT INTO plant_info (plant_id, plant_name, diseaseID, features, cultivation_techniques, value, image_id, created_by, created_time, updated_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object> list = new ArrayList<>();
        list.add(pf.getPlantId());
        list.add(pf.getPlantName());
        list.add(pf.getDiseaseID());
        list.add(pf.getFeatures());
        list.add(pf.getCultivationTechniques());
        list.add(pf.getValue());
        list.add(pf.getImageId());
        list.add(pf.getCreatedBy());
        list.add(pf.getCreatedTime());
        list.add(pf.getUpdatedTime());

        boolean result = db.excute(sql, list, conn);
        db.close(conn);
        return result;
    }

	@Override
	public void deletePlant(String plant_id) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        String sql = "DELETE FROM plant_info WHERE plant_id = ?";
        List<Object> list = new ArrayList<>();
        list.add(plant_id);

        db.excute(sql, list, conn);
        db.close(conn);
        System.out.println("删除成功！");
		
	}

	@Override
	public void updatePlant(PlantInfo pf) throws Exception {
		//每次更新改更新时间，创建时间不变
		// TODO Auto-generated method stub
		 DButil db = new DButil();
	        Connection conn = db.getConnection();
	        String sql = "UPDATE plant_info SET plant_name=?, diseaseID=?, features=?, cultivation_techniques=?, value=?, image_id=?, created_by=?, updated_time=? WHERE plant_id=?";
	        List<Object> list = new ArrayList<>();
	        list.add(pf.getPlantName());
	        list.add(pf.getDiseaseID());
	        list.add(pf.getFeatures());
	        list.add(pf.getCultivationTechniques());
	        list.add(pf.getValue());
	        list.add(pf.getImageId());
	        list.add(pf.getCreatedBy());
	        list.add(pf.getUpdatedTime());
	        list.add(pf.getPlantId());

	        db.excute(sql, list, conn);
	        db.close(conn);
	        System.out.println("更新成功！");
	}

	@Override

	public PlantInfo getPlantInfoById(String plant_id) throws Exception {
		DButil db = new DButil();
	    Connection conn = db.getConnection();
	    
	    // SQL 查询语句
	    String sql = "SELECT * FROM plant_info WHERE plant_id = ?";
	    
	    // 参数列表
	    List<Object> list = new ArrayList<>();
	    list.add(plant_id);

	    // 执行查询
	    List<Map<String, String>> result = db.excutequery(sql, list, conn);

	    // 关闭连接
	    db.close(conn);

	    // 如果查询结果非空，将结果封装为 PlantInfo 对象并返回
	    if (!result.isEmpty()) {
	        Map<String, String> plantInfoMap = result.get(0); // 假设只查询到一个结果
	        PlantInfo plantInfo = new PlantInfo();
	        
	        // 设置 PlantInfo 对象的属性
	        plantInfo.setPlantId(plantInfoMap.get("plant_id"));
	        plantInfo.setPlantName(plantInfoMap.get("plant_name"));
	        plantInfo.setDiseaseID(plantInfoMap.get("diseaseID"));
	        plantInfo.setFeatures(plantInfoMap.get("features"));
	        plantInfo.setCultivationTechniques(plantInfoMap.get("cultivation_techniques"));
	        plantInfo.setValue(plantInfoMap.get("value"));
	        plantInfo.setImageId(plantInfoMap.get("image_id"));
	        plantInfo.setCreatedBy(plantInfoMap.get("created_by"));
	        // 设置其他属性...
//	        plantInfo.setCreatedTime(/* convert to Date */);
//	        plantInfo.setUpdatedTime(/* convert to Date */);

	        return plantInfo;
	    }

	    // 如果查询结果为空，返回 null 或抛出异常，取决于业务需求
	    return null;
	}

	@Override
	public Map<String, Integer> countPlantsByScientificName(String specifiedScientificName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
