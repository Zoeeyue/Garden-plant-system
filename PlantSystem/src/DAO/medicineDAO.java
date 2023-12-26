package DAO;

import java.util.List;
import java.util.Map;

import bean.medicine;

public interface medicineDAO {
	//增加药剂表记录
	public boolean insertMedicine(medicine bean) throws Exception;

	//删除药剂表记录
	public boolean deleteMedicine(String medicineID) throws Exception;

	//修改药剂表记录
	public boolean updateMedicine(medicine bean) throws Exception;

	//查询药剂表记录
	public List<Map<String, String>> queryMedicine(String medicineID) throws Exception;

	//显示药剂表记录
	public List<Map<String, String>> listMedicine() throws Exception;
	
	//判断主码重复
	boolean existID(String ID) throws Exception;

	String getNewID() throws Exception;

	public void listID() throws Exception;
}
