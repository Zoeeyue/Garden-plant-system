package DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import comm.DButil;

public class function {
	public static boolean operate(List<Object> list, String sql) throws Exception {
		boolean flag = false;
		DButil db = new DButil();
		Connection conn = db.getConnection();
		if(db.excute(sql,list, conn)){
			flag = true;
		}
		db.close(conn);
		return flag;
	}
	
	public static List<Map<String, String>> search(List<Object> list, String sql) throws Exception {
		DButil db = new DButil();
		Connection conn = db.getConnection();
		List<Map<String, String>> result = db.excutequery(sql,list,conn);
		db.close(conn);
		return result;
	}
	
	//
	//获取最大编号+1并返回新编号
	public static String getNewID(String sql,String first) throws Exception {
		DButil db = new DButil();
		Connection conn = db.getConnection();
		String newID;
        // 查询最大ID
		String maxIdQuery = sql;
		PreparedStatement preparedStatement = conn.prepareStatement(maxIdQuery);
		ResultSet resultSet = preparedStatement.executeQuery();
        String maxId = null;
        // 获取查询结果
        if (resultSet.next()) {
            maxId = resultSet.getString("max_id");
        }
        // 计算新ID
        if (maxId != null && maxId.matches("[A-Z]+\\d+")) {
            String prefix = maxId.replaceAll("\\d", "");
            int number = Integer.parseInt(maxId.replaceAll("\\D", ""));
            number++;
            newID = prefix + String.format("%07d", number);
        } else {
        	newID = first+"0000001";
        }
        return newID;
	}
}
