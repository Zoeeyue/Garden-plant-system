package DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.monitorDAO;
import bean.monitor;
import service.function;

public class monitorDAOImpl implements monitorDAO{
	//增加监测表记录
	@Override
	public boolean insertMonitor(monitor bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_monitorID()!=null&&bean.get_deviceID()!=null&&bean.get_plantID()!=null&&bean.get_staffID()!=null)
		{
			list.add(bean.get_monitorID());
			list.add(bean.get_monitorTime());
			list.add(bean.get_monitorAddr());
			list.add(bean.get_deviceID());
			list.add(bean.get_plantID());
			list.add(bean.get_staffID());
		}else return false;
		String sql = "INSERT INTO monitor(monitorID,monitorTime,monitorAddr,deviceID,plantID,staffID) VALUES(?,?,?,?,?,?);";
		return function.operate(list,sql);
	}
	//删除监测表记录
	@Override
	public boolean deleteMonitor(String monitorID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(monitorID);
		String sql = "DELETE FROM monitor WHERE monitorID=?;";
		return function.operate(list,sql);
	}
	//修改监测表记录
	@Override
	public boolean updateMonitor(monitor bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_monitorID()!=null&&bean.get_deviceID()!=null&&bean.get_plantID()!=null&&bean.get_staffID()!=null)
		{
			list.add(bean.get_monitorTime());
			list.add(bean.get_monitorAddr());
			list.add(bean.get_deviceID());
			list.add(bean.get_plantID());
			list.add(bean.get_staffID());
			list.add(bean.get_monitorID());
		}else return false;
		String sql = "UPDATE monitor SET monitorTime=?,monitorAddr=?,deviceID=?,plantID=?,staffID=? WHERE monitorID=?;";
		return function.operate(list,sql);
	}
	//查询监测表记录
	@Override
	public List<Map<String, String>> queryMonitor(String monitorID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(monitorID);
		String sql ="SELECT * FROM monitor WHERE monitorID=?";
		return function.search(list,sql);
	}
	
	//查询监测表记录
	@Override
	public List<Map<String, String>> queryMonitor1(String indexID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(indexID);
		String sql ="SELECT * FROM monitor WHERE monitorID IN(SELECT monitorID FROM indexs WHERE indexID=?);";
		return function.search(list,sql);
	}
	
	
	//显示监测表记录
	@Override
	public List<Map<String, String>> listMonitor() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM monitor;";
		return function.search(list,sql);
	}

	//视图查询
	@Override
	public List<Map<String, String>> queryMonitorSystem(String searchTerm) throws Exception {
	    List<Object> list = new ArrayList<>();
	    StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM MonitorYewu WHERE ");
	    sqlBuilder.append("monitorID=? OR staffName=? OR monitorAddr=? OR plant_name=? OR monitorTime=? OR deviceName=? OR indexName=?");
	    for (int i = 0; i < 7; i++) {
	        list.add(searchTerm);
	    }
	    String sql = sqlBuilder.toString();
	    return function.search(list, sql);
	}
	
	//视图显示1
	@Override
	public List<Map<String, String>> listMonitorSystem() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM MonitorYewu";
		return function.search(list,sql);
	}	
	//视图显示2
	@Override
	public List<Map<String, String>> listMonitorShow() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM MonitorToShow";
		return function.search(list,sql);
	}
	//判断是否id存在
	@Override
	public boolean existID(String ID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(ID);
		String sql ="SELECT * FROM monitor WHERE monitorID=?";
		List<Map<String, String>> row = function.search(list,sql);
		if(row.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean existID2(String ID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(ID);
		String sql ="SELECT * FROM MonitorToShow WHERE id=?";
		List<Map<String, String>> row = function.search(list,sql);
		if(row.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	//获取最大编号+1并返回新编号
	@Override
	public String getNewID() throws Exception {
		String sql = "SELECT MAX(monitorID) AS max_id FROM monitor";
		return function.getNewID(sql,"MON");
	}
//	@Override
//	public List<Map<String, String>> queryMonitorSystem(String monitorID) throws Exception {
//		List<Object> list = new ArrayList<Object>();
//		list.add(monitorID);
//		String sql ="SELECT * "+
//				"FROM monitor "+
//				"JOIN staff ON monitor.staffID=staff.staffID "+
//				"JOIN plant_info ON monitor.plant_id=plant_info.plant_id "+
//				"JOIN device ON monitor.deviceID=device.deviceID "+
//				"JOIN indexs ON monitor.monitorID=indexs.monitorID "+
//				"WHERE monitorID=?;";
//		return function.search(list,sql);
//	}
//	@Override
//	public List<Map<String, String>> queryMonitorSystem(String monitorID) throws Exception {
//		List<Object> list = new ArrayList<Object>();
//		list.add(monitorID);
//		String sql ="SELECT * FROM MonitorYewu WHERE monitorID=?;";
//		return function.search(list,sql);
//	}
	
//	@Override
//	public List<Map<String, String>> listMonitorSystem() throws Exception {
//		List<Object> list = new ArrayList<Object>();
//		String sql ="SELECT * "+
//				"FROM monitor "+
//				"JOIN staff ON monitor.staffID=staff.staffID "+
//				"JOIN plant_info ON monitor.plant_id=plant_info.plant_id "+
//				"JOIN device ON monitor.deviceID=device.deviceID "+
//				"JOIN indexs ON monitor.monitorID=indexs.monitorID;";
//		return function.search(list,sql);
//	}
}
