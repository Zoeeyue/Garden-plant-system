package DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.diseaseDAO;
import bean.disease;
import service.function;

public class diseaseDAOImpl implements diseaseDAO {
	//根据药剂编号获取病虫害编号
	@Override
	public List<Map<String, String>> getIDbyMedicineID(String medicineID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(medicineID);
		String sql = "SELECT * FROM treatment WHERE treatmentID IN(SELECT treatmentID FROM medicine WHERE medicineID=?);";
		List<Map<String, String>> IDset= function.search(list,sql);
		return IDset;
	}
	//根据病虫害编号获取对付该病虫害的防治方法数量
	@Override
	public int getTreatmentNUMbyID(String diseaseID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(diseaseID);
		String sql = "SELECT * FROM treatment WHERE diseaseID=?;";
		List<Map<String, String>> IDset= function.search(list,sql);
		int num = IDset.size();
		return num;
	}
	
	//增加病虫害表记录
	@Override
	public boolean insertDisease(disease bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_diseaseID()!=null&&bean.get_plantID()!=null)
		{
			list.add(bean.get_diseaseID());
			list.add(bean.get_diseaseName());
			list.add(bean.get_plantID());
		}else return false;
		String sql = "INSERT INTO disease(diseaseID,diseaseName,plantID) VALUES(?,?,?);";
		return function.operate(list,sql);
	}
	//删除病虫害表记录
	@Override
	public boolean deleteDisease(String ID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(ID);
		String sql = "DELETE FROM disease WHERE diseaseID = ?;";
		return function.operate(list,sql);
	}
	//修改病虫害表记录
	@Override
	public boolean updateDisease(disease bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_diseaseID()!=null&&bean.get_plantID()!=null)
		{
			list.add(bean.get_diseaseName());
			list.add(bean.get_plantID());
			list.add(bean.get_diseaseID());
		}else return false;
		String sql = "UPDATE disease SET diseaseName=?,plantID=? WHERE diseaseID=?;";
		return function.operate(list,sql);
	}
	//【NO】查询病虫害表记录-根据病虫害编号
	@Override
	public List<Map<String, String>> queryDisease(String diseaseID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(diseaseID);
		String sql ="SELECT * FROM disease WHERE diseaseID=?";
		return function.search(list,sql);
	}
	//【NO】查询病虫害表记录-根据药剂编号
	@Override
	public List<Map<String, String>> queryDisease2(String medicineID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(medicineID);
		String sql ="SELECT * FROM disease WHERE diseaseID in(SELECT diseaseID FROM treatment WHERE treatmentID IN(SELECT treatmentID FROM medicine WHERE medicineID=?))";
		return function.search(list,sql);
	}
	//【NO】显示病虫害表记录
	@Override
	public List<Map<String, String>> listDisease() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM disease";
		return function.search(list,sql);
	}
	
	//视图查询
	@Override
	public List<Map<String, String>> queryDiseaseSystem(String searchTerm) throws Exception {
		List<Object> list = new ArrayList<Object>();
	    StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM DiseaseYewu WHERE ");
	    sqlBuilder.append("id=? OR plant_name=? OR diseaseName=? OR treatmentName=? OR medicineName=? OR medicineDosage=? OR medicineDuration=?");
	    for (int i = 0; i < 7; i++) {
	        list.add(searchTerm);
	    }
	    String sql = sqlBuilder.toString();
	    return function.search(list, sql);
	}
	//视图显示-病虫害养护状态
	@Override
	public List<Map<String, String>> ShowUpkeepStats() throws Exception {
	    List<Object> list = new ArrayList<>();
	    String sql = "SELECT * FROM DiseaseUpkeep";
	    return function.search(list, sql);
	}
	//视图显示-病虫害防治措施详细版
	@Override
	public List<Map<String, String>> listDiseaseSystem() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM DiseaseYewu;";
		return function.search(list,sql);
	}
	//视图显示-病虫害防治措施简略版
	@Override
	public List<Map<String, String>> listDiseaseShow() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM DiseaseToShow;";
		return function.search(list,sql);
	}
	//判断是否id存在-根据表查找病虫害ID是否存在
	@Override
	public boolean existID(String ID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(ID);
		String sql ="SELECT * FROM disease WHERE diseaseID=?";
		List<Map<String, String>> row = function.search(list,sql);
		if(row.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	//判断是否id存在-根据视图查找药剂ID是否存在
	@Override
	public boolean existID2(String ID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(ID);
		String sql ="SELECT * FROM DiseaseToShow WHERE id=?";
		List<Map<String, String>> row = function.search(list,sql);
		if(row.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	//获取最大编号+1并返回新编号
	@Override
	public String getNewID() throws Exception {
		String sql = "SELECT MAX(diseaseID) AS max_id FROM disease";
		return function.getNewID(sql,"DIS");
	}
}
