package DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.expertDAO;
import bean.expert;

public class expertDAOImpl implements expertDAO{
	//增加专家表记录
	@Override
	public boolean insertExpert(expert bean) throws Exception{
		List<Object> list = new ArrayList<Object>();
		if(bean.get_expertID()!=null)
		{
			list.add(bean.get_expertID());
			list.add(bean.get_expertName());
			list.add(bean.get_expertPwd());
		}else return false;
		String sql = "INSERT INTO expert(expertID,expertName,expertPwd) VALUES(?,?,?);";
		return function.operate(list,sql);
	}

	//删除专家表记录
	@Override
	public boolean deleteExpert(String expertID){
		List<Object> list = new ArrayList<Object>();
		list.add(expertID);
		String sql = "DELETE FROM expert WHERE expertID=?;";
		try {
			return function.operate(list,sql);
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
	}

	//修改专家表记录
	@Override
	public boolean updateExpert(expert bean) throws Exception{
		List<Object> list = new ArrayList<Object>();
		if(bean.get_expertID()!=null)
		{
			list.add(bean.get_expertName());
			list.add(bean.get_expertPwd());
			list.add(bean.get_expertID());
		}else return false;
		String sql = "UPDATE expert SET expertName=?,expertPwd=? WHERE expertID=?;";
		return function.operate(list,sql);
	}

	//查询专家表记录
	@Override
	public List<Map<String, String>> queryExpert(String expertID) throws Exception{
		List<Object> list = new ArrayList<Object>();
		list.add(expertID);
		String sql ="SELECT * FROM expert WHERE expertID=?";
		return function.search(list,sql);
	}

	//显示专家表记录
	@Override
	public List<Map<String, String>> listExpert() throws Exception{
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM expert";
		return function.search(list,sql);
	}

	//判断主码重复
	@Override
	public boolean existID(String ID) throws Exception{
		List<Object> list = new ArrayList<Object>();
		list.add(ID);
		String sql ="SELECT * FROM expert WHERE expertID=?";
		List<Map<String, String>> row = function.search(list,sql);
		if(row.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public expert login(String username, String password) throws Exception{
        List<Object> list = new ArrayList<>();
        list.add(username);
        list.add(password);
        String sql = "SELECT * FROM expert WHERE expertID = ? AND expertPwd = ?";
        List<Map<String, String>> result = function.search(list,sql);
        if (!result.isEmpty()) {
            Map<String, String> expertInfoMap = result.get(0);
            expert expert = new expert(expertInfoMap.get("expertID"),expertInfoMap.get("expertName"),expertInfoMap.get("expertPwd"));           
            return expert;
        }
        return null;
    }
	
}
