package DAO;

import java.util.List;
import java.util.Map;

import bean.expert;

public interface expertDAO {
	//增加专家表记录
	public boolean insertExpert(expert bean) throws Exception;

	//删除专家表记录
	public boolean deleteExpert(String expertID) throws Exception;

	//修改专家表记录
	public boolean updateExpert(expert bean) throws Exception;

	//查询专家表记录
	public List<Map<String, String>> queryExpert(String expertID) throws Exception;

	//显示专家表记录
	public List<Map<String, String>> listExpert() throws Exception;

	//判断主码重复
	public boolean existID(String ID) throws Exception;

	public expert login(String username, String password) throws Exception;
}
