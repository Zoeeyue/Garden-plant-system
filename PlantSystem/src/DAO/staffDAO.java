package DAO;

import java.util.List;
import java.util.Map;

import bean.staff;

public interface staffDAO {
	//增加监测人员表记录
	public boolean insertStaff(staff bean) throws Exception;

	//删除监测人员表记录
	public boolean deleteStaff(String staffID) throws Exception;

	//修改监测人员表记录
	public boolean updateStaff(staff bean) throws Exception;

	//查询监测人员表记录
	public List<Map<String, String>> queryStaff(String staffID) throws Exception;

	//显示监测人员表记录
	public List<Map<String, String>> listStaff() throws Exception;

	//判断主码重复
	boolean existID(String ID) throws Exception;
}
