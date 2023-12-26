package DAO;

import java.util.List;
import java.util.Map;

import bean.indexs;

public interface indexsDAO {
	//增加监测指标表记录
	public boolean insertIndexs(indexs bean) throws Exception;

	//删除监测指标表记录
	public boolean deleteIndexs(String indexID) throws Exception;

	//修改监测指标表记录
	public boolean updateIndexs(indexs bean) throws Exception;

	//查询监测指标表记录
	public List<Map<String, String>> queryIndexs(String indexID) throws Exception;

	//显示检测指标表记录
	public List<Map<String, String>> listIndexs() throws Exception;

	//指标最大值
	public List<Map<String, String>> IndexsValues() throws Exception;

	//判断主码重复
	boolean existID(String ID) throws Exception;

}
