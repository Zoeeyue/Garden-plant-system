package DAO;

import java.util.List;
import java.util.Map;

import bean.indexs;

public interface indexsDAO {
	//增加监测指标表记录
	public boolean insertIndexs(indexs bean) throws Exception;

	//按照指标编号删除监测指标表记录
	public boolean deleteIndexsByID(String indexID) throws Exception;
	
	//按照监测编号删除监测指标表记录
	boolean deleteIndexsByMonitor(String indexID) throws Exception;

	//修改监测指标表记录
	public boolean updateIndexs(indexs bean) throws Exception;

	//查询监测指标表记录
	public List<Map<String, String>> queryIndexs(String indexID) throws Exception;
	
	//按照监测编号查询监测指标表记录
	List<Map<String, String>> queryIndexs1(String monitorID) throws Exception;

	//显示检测指标表记录
	public List<Map<String, String>> listIndexs() throws Exception;

	//指标最大值
	public List<Map<String, String>> IndexsValues() throws Exception;

	//判断主码重复
	boolean existID(String ID) throws Exception;

	String getNewID() throws Exception;

	void listID() throws Exception;


}
