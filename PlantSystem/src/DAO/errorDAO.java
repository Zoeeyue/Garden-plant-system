package DAO;

import java.util.List;
import java.util.Map;

import bean.error;
//该表用触发器实现自动存储
public interface errorDAO {
	//增加异常表记录
	public boolean insertError(error bean) throws Exception;

	//删除异常表记录
	public void deleteError(String errorID) throws Exception;

	//修改异常表记录
	public boolean updateError(String errorID) throws Exception;

	//查询异常表记录
	public List<Map<String, String>> queryError() throws Exception;
}
