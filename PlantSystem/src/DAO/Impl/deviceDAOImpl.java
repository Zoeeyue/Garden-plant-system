package DAO.Impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.deviceDAO;
import bean.device;
import service.function;
public class deviceDAOImpl implements deviceDAO{
	//增加监测设备表记录
	@Override
	public boolean insertDevice(device bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_deviceID()!=null)
		{
			list.add(bean.get_deviceID());
			list.add(bean.get_deviceName());
		}else return false;
		String sql = "INSERT INTO device(deviceID,deviceName) VALUES(?,?);";
		return function.operate(list,sql);
	}
	//删除监测设备表记录
	@Override
	public boolean deleteDevice(String deviceID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(deviceID);
		String sql = "DELETE FROM device WHERE deviceID=?;";
		return function.operate(list,sql);
	}
	//修改监测设备表记录
	@Override
	public boolean updateDevice(device bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_deviceID()!=null)
		{
			list.add(bean.get_deviceName());
			list.add(bean.get_deviceID());
		}else return false;
		String sql = "UPDATE device SET deviceName=? WHERE deviceID=?;";
		return function.operate(list,sql);
	}
	//查询监测设备表记录
	@Override
	public List<Map<String, String>> queryDevice(String deviceID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(deviceID);
		String sql ="SELECT * FROM device WHERE deviceID=?";
		return function.search(list,sql);
	}
	//显示监测设备表记录
	@Override
	public List<Map<String, String>> listDevice() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM device";
		return function.search(list,sql);
	}
	//判断是否id重复
		@Override
		public boolean existID(String ID) throws Exception {
			List<Object> list = new ArrayList<Object>();
			list.add(ID);
			String sql ="SELECT * FROM device WHERE deviceID=?";
			List<Map<String, String>> row = function.search(list,sql);
			if(row.isEmpty()) {
				return true;
			}else {
				return false;
			}
		}
}
