package DAO.Impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.admin_infoDAO;
import comm.DButil;
import bean.adminInfo;

public class admin_infoDAOImpl implements admin_infoDAO {

	 @Override
	    public boolean insertAdmin(adminInfo adminInfo) throws Exception {
	        // 实现插入管理员信息到数据库的逻辑
	        return false;
	    }

	    @Override
	    public void deleteAdmin(String admin_id) throws Exception {
	        // 实现删除管理员信息的逻辑
	    }

	    @Override
	    public void updateAdmin(adminInfo adminInfo) throws Exception {
	        // 实现更新管理员信息的逻辑
	    }

	    @Override
	    public adminInfo getAdminById(String admin_id) throws Exception {
	        // 实现根据管理员id查找管理员的逻辑
	        return null;
	    }

	    @Override
	    public adminInfo login(String username, String password) throws Exception {
	        DButil db = new DButil();
	        Connection conn = db.getConnection();
	        
	        // SQL 查询语句，根据用户名和密码查找管理员信息
	        String sql = "SELECT * FROM admin_info WHERE admin_id = ? AND admin_password = ?";
	        
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
	            Map<String, String> adminInfoMap = result.get(0); // 假设只查询到一个结果
	            adminInfo adminInfo = new adminInfo();
	            
	            // 设置 adminInfo 对象的属性
	            adminInfo.setAdminId(adminInfoMap.get("admin_id"));
	            adminInfo.setName(adminInfoMap.get("admin_name"));
	            adminInfo.setPassword("admin_password");;
	            // 设置其他属性...
	            
	            return adminInfo;
	        }

	        // 如果查询结果为空，返回 null 或抛出异常，取决于业务需求
	        return null;
	    }

    
}

