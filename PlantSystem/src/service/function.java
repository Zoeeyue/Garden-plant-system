package service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import link.DButil;

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
}
