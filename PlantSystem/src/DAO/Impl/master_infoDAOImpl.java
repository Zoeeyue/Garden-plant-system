package DAO.Impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.master_infoDAO;
import comm.DButil;
import bean.masterInfo;

public class master_infoDAOImpl implements master_infoDAO {

	 	@Override
	    public boolean insertMaster(masterInfo m) throws Exception {
	     
	        return false;
	    }
	 
		@Override
		public void deleteMaster(String master_id) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateMaster(masterInfo a) throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		public masterInfo getMasterById(String master_id) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

	    @Override
	    public masterInfo login(String username, String password) throws Exception {
	        DButil db = new DButil();
	        Connection conn = db.getConnection();
	        
	        // SQL 查询语句，根据用户名和密码查找管理员信息
	        String sql = "SELECT * FROM master_info WHERE master_id = ? AND master_password = ?";
	        
	        // 参数列表
	        List<Object> list = new ArrayList<>();
	        list.add(username);
	        list.add(password);

	        // 执行查询
	        List<Map<String, String>> result = db.excutequery(sql, list, conn);

	        // 关闭连接
	        db.close(conn);

	        // 如果查询结果非空，将结果封装为 adminInfo 对象并返回
	        if (!result.isEmpty()) {
	            Map<String, String>  masterInfoMap = result.get(0); // 假设只查询到一个结果
	            masterInfo masterInfo = new masterInfo();
	            
	            // 设置 masterInfo 对象的属性
	            masterInfo.setMasterId(masterInfoMap.get("master_id"));
	            masterInfo.setMaster_name(masterInfoMap.get("master_name"));
	            masterInfo.setMaster_password(masterInfoMap.get("master_password"));
	            
	            return masterInfo;
	        }

	        // 如果查询结果为空，返回 null 或抛出异常，取决于业务需求
	        return null;
	    }



    
}

