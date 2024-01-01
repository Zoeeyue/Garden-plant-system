package DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.staffDAO;
import bean.staff;

public class staffDAOImpl implements staffDAO{
	//增加监测人员表记录
	@Override
	public boolean insertStaff(staff bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_staffID()!=null)
		{
			list.add(bean.get_staffID());
			list.add(bean.get_staffName());
			list.add(bean.get_staffPwd());
		}else return false;
		String sql = "INSERT INTO staff(staffID,staffName,staffPwd) VALUES(?,?,?);";
		return function.operate(list,sql);
	}
	//删除监测人员表记录
	@Override
	public boolean deleteStaff(String staffID){
		List<Object> list = new ArrayList<Object>();
		list.add(staffID);
		String sql = "DELETE FROM staff WHERE staffID=?;";
		try {
			return function.operate(list,sql);
		} catch (Exception e) {
			return false;
		}
	}
	//修改监测人员表记录
	@Override
	public boolean updateStaff(staff bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_staffID()!=null)
		{
			list.add(bean.get_staffName());
			list.add(bean.get_staffPwd());
			list.add(bean.get_staffID());
		}else return false;
		String sql = "UPDATE staff SET staffName=?,staffPwd=? WHERE staffID=?;";
		return function.operate(list,sql);
	}
	//查询监测人员表记录
	@Override
	public List<Map<String, String>> queryStaff(String staffID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(staffID);
		String sql ="SELECT * FROM staff WHERE staffID=?";
		return function.search(list,sql);
	}
	//显示监测人员表记录
	@Override
	public List<Map<String, String>> listStaff() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM staff";
		return function.search(list,sql);
	}
	//判断是否id重复
	@Override
	public boolean existID(String ID) throws Exception {
		List<Map<String, String>> row = queryStaff(ID);
		if(row.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	//登录
    @Override
    public staff login(String username, String password) throws Exception {
        List<Object> list = new ArrayList<>();
        list.add(username);
        list.add(password);
        String sql = "SELECT * FROM staff WHERE staffID = ? AND staffPwd = ?";
        List<Map<String, String>> result = function.search(list,sql);
        if (!result.isEmpty()) {
            Map<String, String> staffInfoMap = result.get(0);
            staff staff = new staff(staffInfoMap.get("staffID"),staffInfoMap.get("staffName"),staffInfoMap.get("staffPwd"));           
            return staff;
        }
        return null;
    }
}
