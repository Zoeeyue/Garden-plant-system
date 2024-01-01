package DAO.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAO.indexsDAO;
import bean.indexs;

public class indexsDAOImpl implements indexsDAO{
	//根据植物编号获取指标编号
	@Override
	public List<Map<String, String>> getIDbyPlantID(String plantID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(plantID);
		String sql = "SELECT * FROM indexs WHERE monitorID IN (SELECT monitorID FROM monitor WHERE plantID=?);";
		List<Map<String, String>> IDset= function.search(list,sql);
		return IDset;
	}
	
	//增加监测指标表记录
	@Override
	public boolean insertIndexs(indexs bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_indexID()!=null)
		{
			list.add(bean.get_indexID());
			list.add(bean.get_indexName());
			list.add(bean.get_indexValue());
			list.add(bean.get_monitorID());
		}else return false;
		String sql = "INSERT INTO indexs(indexID,indexName,indexValue,monitorID) VALUES(?,?,?,?);";
		return function.operate(list,sql);
	}
	
	//删除监测指标表记录
	@Override
	public boolean deleteIndexsByID(String indexID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(indexID);
		String sql = "DELETE FROM indexs WHERE indexID=?;";
		return function.operate(list,sql);
	}
	
	//删除监测指标表记录
	@Override
	public boolean deleteIndexsByMonitor(String monitorID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(monitorID);
		String sql = "DELETE FROM indexs WHERE monitorID=?;";
		return function.operate(list,sql);
	}
	
	//修改监测指标表记录
	@Override
	public boolean updateIndexs(indexs bean) throws Exception {
		List<Object> list = new ArrayList<Object>();
		if(bean.get_indexID()!=null)
		{
			list.add(bean.get_indexName());
			list.add(bean.get_indexValue());
			list.add(bean.get_monitorID());
			list.add(bean.get_indexID());
		}else return false;
		String sql = "UPDATE indexs SET indexName=?,indexValue=?,monitorID=? WHERE indexID=?;";
		return function.operate(list,sql);
	}
	
	//根据指标编号查询监测指标表记录
	@Override
	public List<Map<String, String>> queryIndexs(String indexID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(indexID);
		String sql ="SELECT * FROM indexs WHERE indexID=?";
		return function.search(list,sql);
	}
	
	//根据监测编号查询监测指标表记录
	@Override
	public List<Map<String, String>> queryIndexs1(String monitorID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(monitorID);
		String sql ="SELECT * FROM indexs WHERE monitorID=?";
		return function.search(list,sql);
	}

	//显示检测指标表所有记录
	@Override
	public List<Map<String, String>> listIndexs() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM indexs";
		return function.search(list,sql);
	}
	
	//显示检测指标表所有编号
	@Override
	public void listID() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT indexID FROM indexs";
		List<Map<String, String>> result = function.search(list,sql);
		for(Map<String,String> map:result) {
			System.out.print(map.get("indexID")+"\t");
		}
		System.out.println();
	}
	
	//计算指标最大值、均值——已实现视图（可替换）
	@Override
	public List<Map<String, String>> IndexsValues() throws Exception {
		List<Object> list = new ArrayList<Object>();
		String sql ="SELECT * FROM IndexAnalysis";
//		String sql ="SELECT indexName,max(indexValue) as maxValue,avg(indexValue) as avgValue FROM indexs GROUP BY indexName";
		return function.search(list,sql);
	}
	//判断主码重复
	@Override
	public boolean existID(String ID) throws Exception {
		List<Object> list = new ArrayList<Object>();
		list.add(ID);
		String sql ="SELECT * FROM indexs WHERE indexID=?";
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
		String sql = "SELECT MAX(indexID) AS max_id FROM indexs";
		return function.getNewID(sql,"IND");
	}
}
