package DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.medicineDAO;
import bean.medicine;

public class medicineDAOImpl implements medicineDAO{
	//根据植物编号获取药剂编号
	@Override
	public List<Map<String, String>> getIDbyPlantID(String plantID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(plantID);
		String sql = "SELECT * FROM medicine WHERE treatmentID IN (SELECT treatmentID FROM treatment WHERE diseaseID IN(SELECT diseaseID FROM disease WHERE plantID=?));";
		List<Map<String, String>> IDset= function.search(list,sql);
		return IDset;
	}
	
	//增加药剂表记录
	@Override
	public boolean insertMedicine(medicine bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_medicineID()!=null)
		{
			list.add(bean.get_medicineID());
			list.add(bean.get_medicineName());
			list.add(bean.get_medicineDosage());
			list.add(bean.get_medicineDuration());
			list.add(bean.get_treatmentID());
		}else return false;
		String sql = "INSERT INTO medicine(medicineID,medicineName,medicineDosage,medicineDuration,treatmentID) VALUES(?,?,?,?,?);";
		return function.operate(list,sql);
	}
	//删除药剂表记录-根据药剂编号
	@Override
	public boolean deleteMedicine(String medicineID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(medicineID);
		String sql = "DELETE FROM medicine WHERE medicineID=?;";
		return function.operate(list,sql);
	}
	//修改药剂表记录
	@Override
	public boolean updateMedicine(medicine bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_medicineID()!=null)
		{
			list.add(bean.get_medicineName());
			list.add(bean.get_medicineDosage());
			list.add(bean.get_medicineDuration());
			list.add(bean.get_treatmentID());
			list.add(bean.get_medicineID());
		}else return false;
		String sql = "UPDATE medicine SET medicineName=?,medicineDosage=?,medicineDuration=?,treatmentID=? WHERE medicineID=?;";
		return function.operate(list,sql);
	}
	//【NO】查询药剂表记录
	@Override
	public List<Map<String, String>> queryMedicine(String medicineID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(medicineID);
		String sql ="SELECT * FROM medicine WHERE medicineID=?";
		return function.search(list,sql);
	}
	//【NO】显示药剂表记录
	@Override
	public List<Map<String, String>> listMedicine() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM medicine";
		return function.search(list,sql);
	}
	//【NO】显示所有药剂编号
	@Override
	public void listID() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT medicineID FROM medicine";
		List<Map<String, String>> result = function.search(list,sql);
		for(Map<String,String> map:result) {
			System.out.print(map.get("medicineID")+"\t");
		}
		System.out.println();
	}
	
	//判断是否id存在
	@Override
	public boolean existID(String ID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(ID);
		String sql ="SELECT * FROM medicine WHERE medicineID=?";
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
			String sql = "SELECT MAX(medicineID) AS max_id FROM medicine";
			return function.getNewID(sql,"MED");
		}
}
