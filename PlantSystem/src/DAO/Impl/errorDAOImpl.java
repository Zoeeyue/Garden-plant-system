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
	public void deleteError(String errorID) throws Exception {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public boolean updateError(String errorID) throws Exception {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public List<Map<String, String>> queryError() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM ErrorIndexs";
		return function.search(list,sql);
	}

}
