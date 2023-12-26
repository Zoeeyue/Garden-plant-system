package DAO;

import java.util.List;
import java.util.Map;

import bean.treatment;

public interface treatmentDAO {
	//增加防治方法表记录
	public boolean insertTreatment(treatment bean) throws Exception;

	//删除防治方法表记录
	public boolean deleteTreatment(String treatmentID) throws Exception;

	//修改防治方法表记录
	public boolean updateTreatment(treatment bean) throws Exception;

	//查询防治方法表记录
	public List<Map<String, String>> queryTreatment(String treatmentID) throws Exception;

	//显示防治方法表记录
	public List<Map<String, String>> listTreatment() throws Exception;

	//判断主码是否重复
	boolean existID(String ID) throws Exception;
}
