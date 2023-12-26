package DAO.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.plant_infoDAO;
import comm.DButil;
import bean.PlantDetailView;
import bean.PlantInfo;

public class plant_infoDAOImpl implements plant_infoDAO {

    @Override
    public List<PlantDetailView> queryAllPf() throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM PlantDetailView;";
//        List<PlantDetailView> result = new ArrayList<>();
     // 将查询结果转换为 PlantInfo 对象列表
 	    List<PlantDetailView> PlantDetailViewList = new ArrayList<>();
        try {
        	 List<Map<String, String>> result = db.excutequery(sql, null, conn);

      
     	    for (Map<String, String> row : result) {
     	    	PlantDetailView plantViewInfo = new PlantDetailView();
     	    	String dateCreateString = row.get("创建时间");
     	    	String dateUpdateString = row.get("更新时间");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date createDate = dateFormat.parse(dateCreateString);
                Date updateDate = dateFormat.parse(dateUpdateString);
                
     	    	plantViewInfo.setPlantId(row.get("植物编号"));
     	    	plantViewInfo.setPlantName(row.get("植物种名"));
     	    	plantViewInfo.setFeatures(row.get("形态特征"));
     	    	plantViewInfo.setCultivation_tech(row.get("栽培技术"));
     	    	plantViewInfo.setValue(row.get("应用价值"));
     	    	plantViewInfo.setCreatedBy(row.get("创建人"));
     	    	plantViewInfo.setCreatedTime(createDate);
     	    	plantViewInfo.setUpdatedTime(updateDate);
     	    	plantViewInfo.setFamilyName(row.get("植物科名"));
     	    	plantViewInfo.setAlias(row.get("植物别名"));
     	    	plantViewInfo.setImageId(row.get("植物配图编号"));
     	    	plantViewInfo.setFilename(row.get("配图文件存储路径"));
     	    	plantViewInfo.setPlace(row.get("拍摄地点"));
     	    	plantViewInfo.setDetail(row.get("配图描述"));
     	    	plantViewInfo.setCreater(row.get("拍摄人"));
     	    	PlantDetailViewList.add(plantViewInfo);
     	    }

     	    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(conn);
        }

        return PlantDetailViewList;
    }

    @Override
    public boolean insertPlant(PlantInfo pf) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();
        try {
        // 查询数据库中是否存在相同的植物编号
        if (isPlantIdExists(pf.getPlantId())) {
            System.out.println("植物编号已存在，插入失败！");
            db.close(conn);
            return false;
        }
        
        
        String sql = "INSERT INTO plant_info (plant_id, plant_name, features, cultivation_techniques, value,  created_by, created_time, updated_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object> list = new ArrayList<>();
        list.add(pf.getPlantId());
        list.add(pf.getPlantName());
        list.add(pf.getFeatures());
        list.add(pf.getCultivationTechniques());
        list.add(pf.getValue());
        list.add(pf.getCreatedBy());
        list.add(pf.getCreatedTime());
        list.add(pf.getUpdatedTime());

        boolean result = db.excute(sql, list, conn);
        db.close(conn);
        return result;
    } catch (SQLException e) {
        // 处理 SQL 异常
        if (e.getMessage().contains("PK_plant_info")) {
            System.out.println("植物编号已存在，插入失败！");
        } else {
            e.printStackTrace();
        }
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        db.close(conn);
    }
    }
    
    @Override
    public boolean isPlantIdExists(String plantId) throws Exception {
    	DButil db = new DButil();
        Connection conn = db.getConnection();
    	String querySql = "SELECT COUNT(*) AS count FROM plant_info WHERE plant_id = ?";
        List<Object> queryList = new ArrayList<>();
        queryList.add(plantId);

        List<Map<String, String>> result = db.excutequery(querySql, queryList, conn);
        int count = Integer.parseInt(result.get(0).get("count"));

        return count > 0;
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
	        String sql = "UPDATE plant_info SET plant_name=?, features=?, cultivation_techniques=?, value=?, created_by=?, updated_time=? WHERE plant_id=?";
	        List<Object> list = new ArrayList<>();
	        list.add(pf.getPlantName());
	        list.add(pf.getFeatures());
	        list.add(pf.getCultivationTechniques());
	        list.add(pf.getValue());
	        list.add(pf.getCreatedBy());
	        list.add(pf.getUpdatedTime());
	        list.add(pf.getPlantId());

	        db.excute(sql, list, conn);
	        db.close(conn);
	        System.out.println("更新成功！");
	}

	@Override
	//to do
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
	        Map<String, String> plantInfoMap = result.get(0); // 一个植物编号只查询到一个结果
	        PlantInfo plantInfo = new PlantInfo();
	        
	        // 设置 PlantInfo 对象的属性
	        plantInfo.setPlantId(plantInfoMap.get("plant_id"));
	        plantInfo.setPlantName(plantInfoMap.get("plant_name"));
	        plantInfo.setFeatures(plantInfoMap.get("features"));
	        plantInfo.setCultivationTechniques(plantInfoMap.get("cultivation_techniques"));
	        plantInfo.setValue(plantInfoMap.get("value"));
	        plantInfo.setCreatedBy(plantInfoMap.get("created_by"));


	        return plantInfo;
	    }

	    // 如果查询结果为空，返回 null 或抛出异常，取决于业务需求
	    return null;
	}

	@Override
	public Map<String, Integer> countPlantsByScientificName() throws Exception {
		 DButil db = new DButil();
		    Connection conn = db.getConnection();
		    
		    // SQL 查询语句，根据科名统计植物数量
		    //查找视图，科名，数量
		    String sql = "SELECT * from PlantCountView";
		    
		    // 参数列表
		    List<Object> list = new ArrayList<>();
//		    list.add(specifiedScientificName);

		    // 执行查询
		    List<Map<String, String>> result = db.excutequery(sql, list, conn);

		    // 关闭连接
		    db.close(conn);

		    // 将结果封装为 Map
		    Map<String, Integer> plantCountMap = new HashMap<>();
		    for (Map<String, String> row : result) {
		        String familyName = row.get("科名");
		        int plantCount = Integer.parseInt(row.get("植物数量"));
		        plantCountMap.put(familyName, plantCount);
		    }
		    System.out.println(plantCountMap);

		    return plantCountMap;
	}

	@Override
	public List<PlantInfo> queryPlantsByProperties(Map<String, Object> properties) throws Exception {
		DButil db = new DButil();
	    Connection conn = db.getConnection();

	    // 构建动态 SQL 查询语句
	    StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM plant_info WHERE ");
	    List<Object> values = new ArrayList<>();

	    // 遍历属性映射，构建查询条件
	    for (Map.Entry<String, Object> entry : properties.entrySet()) {
	        String property = entry.getKey();
	        Object value = entry.getValue();
	        
	        // 处理不同数据类型的查询条件，这里默认都是等值查询
	        if (value instanceof String) {
	            sqlBuilder.append(property).append(" = ? AND ");
	        } else {
	            // 处理其他数据类型...
	        }
	        
	        values.add(value);
	    }

	    // 移除末尾多余的 "AND "
	    sqlBuilder.setLength(sqlBuilder.length() - 5);

	    // 执行查询
	    List<Map<String, String>> result = db.excutequery(sqlBuilder.toString(), values, conn);

	    // 关闭连接
	    db.close(conn);

	    // 将查询结果转换为 PlantInfo 对象列表
	    List<PlantInfo> plantInfoList = new ArrayList<>();
	    for (Map<String, String> row : result) {
	        PlantInfo plantInfo = new PlantInfo();
	        // 设置 PlantInfo 对象的属性...

	        plantInfo.setPlantId(row.get("plant_id"));
	        plantInfo.setPlantName(row.get("plant_name"));
	        plantInfo.setFeatures(row.get("features"));
	        plantInfo.setCultivationTechniques(row.get("cultivation_techniques"));
	        plantInfo.setValue(row.get("value"));
	        plantInfo.setCreatedBy(row.get("created_by"));
	        plantInfoList.add(plantInfo);
	    }

	    return plantInfoList;
	}






}