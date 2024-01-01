package DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.errorDAO;
import bean.error;
import service.function;

public class errorDAOImpl implements errorDAO{
	@Override
	public boolean insertError(error bean) throws Exception {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean deleteError(String errorID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(errorID);
		String sql = "DELETE FROM error WHERE indexID=?;";
		return function.operate(list,sql);
	}

	@Override
	public boolean updateError(String errorID) throws Exception {
		// TODO 自动生成的方法存根
		return false;
	}
	
	//根据指标编号判断该指标是否存在于异常表中
	@Override
	public boolean isinError(String indexID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(indexID);
		String sql = "SELECT * FROM error WHERE indexID=?;";		
		List<Map<String, String>> row = function.search(list,sql);
		if(row.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public List<Map<String, String>> queryError() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM ErrorIndexs";
		return function.search(list,sql);
	}

}
