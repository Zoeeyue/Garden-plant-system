package DAO;

import java.util.List;
import java.util.Map;

import bean.device;

public interface deviceDAO {
	//增加监测设备表记录
	public boolean insertDevice(device bean) throws Exception;

	//删除监测设备表记录
	public boolean deleteDevice(String deviceID) throws Exception;

	//修改监测设备表记录
	public boolean updateDevice(device bean) throws Exception;

	//查询监测设备表记录
	public List<Map<String, String>> queryDevice(String deviceID) throws Exception;

	//显示监测设备表记录
	public List<Map<String, String>> listDevice() throws Exception;

	//判断主码重复
	boolean existID(String ID) throws Exception;
}
