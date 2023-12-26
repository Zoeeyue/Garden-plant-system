package DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.treatmentDAO;
import bean.treatment;
import service.function;

public class treatmentDAOImpl implements treatmentDAO{
	//增加防治方法表记录
	@Override
	public boolean insertTreatment(treatment bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_treatmentID()!=null && bean.get_diseaseID()!=null)
		{
			list.add(bean.get_treatmentID());
			list.add(bean.get_treatmentName());
			list.add(bean.get_treatmentCont());
			list.add(bean.get_diseaseID());
		}else return false;
		String sql = "INSERT INTO treatment(treatmentID,treatmentName,treatmentCont,diseaseID) VALUES(?,?,?,?);";
		return function.operate(list,sql);
	}
	//删除防治方法表记录
	@Override
	public boolean deleteTreatment(String medicineID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(medicineID);
		String sql = "DELETE FROM treatment WHERE treatmentID IN(SELECT treatmentID FROM medicine WHERE medicineID=?);";
		return function.operate(list,sql);
	}
	//修改防治方法表记录
	@Override
	public boolean updateTreatment(treatment bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_treatmentID()!=null && bean.get_diseaseID()!=null)
		{
			list.add(bean.get_treatmentName());
			list.add(bean.get_treatmentCont());
			list.add(bean.get_diseaseID());
			list.add(bean.get_treatmentID());
		}else return false;
		String sql = "UPDATE treatment SET treatmentName=?,treatmentCont=?,diseaseID=? WHERE treatmentID=?;";
		return function.operate(list,sql);
	}
	//查询防治方法表记录
	@Override
	public List<Map<String, String>> queryTreatment(String treatmentID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(treatmentID);
		String sql ="SELECT * FROM treatment WHERE treatmentID=?";
		return function.search(list,sql);
	}
	//显示防治方法表记录
	@Override
	public List<Map<String, String>> listTreatment() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM treatment";
		return function.search(list,sql);
	}
	//判断是否id重复
		@Override
		public boolean existID(String ID) throws Exception {
			List<Object> list = new ArrayList<Object>();
			list.add(ID);
			String sql ="SELECT * FROM treatment WHERE treatmentID=?";
			List<Map<String, String>> row = function.search(list,sql);
			if(row.isEmpty()) {
				return true;
			}else {
				return false;
			}
		}
}
