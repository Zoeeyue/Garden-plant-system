package DAO;

import java.util.List;
import java.util.Map;

import bean.disease;

public interface diseaseDAO {
	//增加病虫害表记录
	public boolean insertDisease(disease bean) throws Exception;

	//删除病虫害表记录
	public boolean deleteDisease(String diseaseID) throws Exception;

	//修改病虫害表记录
	public boolean updateDisease(disease bean) throws Exception;

	//查询病虫害表记录
	public List<Map<String, String>> queryDisease(String diseaseID) throws Exception;

	//显示病虫害表记录
	List<Map<String, String>> listDisease() throws Exception;

	//多表查询
	List<Map<String, String>> queryDiseaseSystem(String monitorID) throws Exception;

	//多表查询all
	List<Map<String, String>> listDiseaseSystem() throws Exception;

	//判断主码重复
	boolean existID(String ID) throws Exception;
}