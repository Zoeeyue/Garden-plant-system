package service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.deviceDAO;
import DAO.errorDAO;
import DAO.indexsDAO;
import DAO.monitorDAO;
import DAO.plant_infoDAO;
import DAO.staffDAO;
import DAO.Impl.deviceDAOImpl;
import DAO.Impl.errorDAOImpl;
import DAO.Impl.indexsDAOImpl;
import DAO.Impl.monitorDAOImpl;
import DAO.Impl.plant_infoDAOImpl;
import DAO.Impl.staffDAOImpl;
import bean.ViewError;
import bean.ViewIndexs;
import bean.ViewMonitor;
import bean.device;
import bean.indexs;
import bean.monitor;
import bean.staff;

//业务5：监测
public class Monitor {
	//增加监测记录
	public void add(indexs i,monitor m) throws Exception {
		indexsDAO indexs_di = new indexsDAOImpl();
		monitorDAO monitor_di = new monitorDAOImpl();
		monitor_di.insertMonitor(m);//监测-业务
		indexs_di.insertIndexs(i);//监测指标
		System.out.println("添加成功！");
	}
	//删除监测记录的某个指标
	public void delete_index(String indexID) throws Exception {
		indexsDAOImpl indexs_di = new indexsDAOImpl();
		if(indexs_di.existID(indexID)) {
			System.out.print("指标信息不存在！");
			return;
		}
		indexs_di.deleteIndexsByID(indexID);//监测指标
		System.out.println("删除成功！");
	}
	//删除监测记录
	public void delete(String monitorID) throws Exception {
		indexsDAOImpl indexs_di = new indexsDAOImpl();
		monitorDAOImpl monitor_di = new monitorDAOImpl();
		if(monitor_di.existID(monitorID)) {
			System.out.print("监测记录不存在！");
			return;
		}
		indexs_di.deleteIndexsByMonitor(monitorID);//监测指标
		monitor_di.deleteMonitor(monitorID);//监测
		System.out.println("删除成功！");
	}
	//修改监测记录
	public void update(Object entity) throws Exception {
		if (entity instanceof monitor) {
			monitor m = (monitor) entity;
			monitorDAOImpl monitor_di = new monitorDAOImpl();
			monitor_di.updateMonitor(m);
	    } else if (entity instanceof indexs) {
	    	indexs i = (indexs) entity;
			indexsDAOImpl indexs_di = new indexsDAOImpl();
			indexs_di.updateIndexs(i);
	    }
		System.out.println("修改成功！");
	}
	//查询监测记录——视图
	public void search(String searchTerm) throws Exception {
		monitorDAO monitor_di = new monitorDAOImpl();
		List<Map<String, String>> result = monitor_di.queryMonitorSystem(searchTerm);
		for(Map<String, String> map : result) {
			ViewMonitor m = new ViewMonitor(map.get("monitorID"),map.get("plant_name"),map.get("staffName"),map.get("monitorTime"),map.get("monitorAddr"),map.get("deviceName"),map.get("indexName"),map.get("indexValue"));
			m.toPrint();
		}
	}
	//查看监测记录——视图
	public void list() throws Exception {
		monitorDAO monitor_di = new monitorDAOImpl();
		List<Map<String, String>> result = monitor_di.listMonitorSystem();
		for(Map<String, String> map : result) {
			ViewMonitor m = new ViewMonitor(map.get("monitorID"),map.get("plant_name"),map.get("staffName"),map.get("monitorTime"),map.get("monitorAddr"),map.get("deviceName"),map.get("indexName"),map.get("indexValue"));
			m.toPrint();
		}
	}
	//指标分析——视图
	public void analysis() throws Exception {
		indexsDAO indexs_di = new indexsDAOImpl();
		List<Map<String, String>> result = indexs_di.IndexsValues();
		for(Map<String, String> map : result) {
			ViewIndexs m = new ViewIndexs(map.get("indexName"),map.get("maxValue"),map.get("avgValue"));
			m.toPrint();
		}
	}
	//查看异常指标记录——视图
	public void showError() throws Exception {
		errorDAO error_di = new errorDAOImpl();
		List<Map<String, String>> result = error_di.queryError();
		for(Map<String, String> map : result) {
			ViewError m = new ViewError(map.get("plant_name"),map.get("indexName"),map.get("indexValue"),map.get("monitorTime"));
			m.toPrint();
		}
	}
	//单独添加监测设备（购进设备）
	public void add_device(device d) throws Exception {
		deviceDAO device_di = new deviceDAOImpl();
		device_di.insertDevice(d);
	}
	//单独删除监测设备（设备报废）
	public void delete_device(String deviceID) throws Exception {
		deviceDAO device_di = new deviceDAOImpl();
		device_di.deleteDevice(deviceID);
	}
	//单独删除监测人员（员工离职）
	public void delete_staff(String staffID) throws Exception {
		staffDAO staff_di = new staffDAOImpl();
		staff_di.deleteStaff(staffID);
	}
	//单独添加监测人员（员工入职）
	public void add_staff(staff s) throws Exception {
		staffDAO staff_di = new staffDAOImpl();
		staff_di.insertStaff(s);
	}
		
	
	//接收用户输入的数据进行封装，再连接接口【添加】
	public void toAdd(Scanner scanner) throws Exception {
		//业务验证
        System.out.println("请输入监测对象编号：");
        String plantID = scanner.nextLine();
        plant_infoDAO plant_di = new plant_infoDAOImpl();
		if(!plant_di.isPlantIdExists(plantID)) {
			System.out.println("该植物不存在！");
			return;
		}
        System.out.println("请输入监测人员工号：");
        String staffID = scanner.nextLine();
        staffDAO staff_di = new staffDAOImpl();
        if(staff_di.existID(staffID)) {
			System.out.println("监测人员不存在！");
			return;
		}
        System.out.println("请输入监测设备编号：");
        String deviceID = scanner.nextLine();
        deviceDAO device_di = new deviceDAOImpl();
		if(device_di.existID(deviceID)) {
			System.out.println("监测设备不存在！");
			return;
		}
		
        //用户输入
        System.out.println("请输入监测时间：");
        String monitorTime = scanner.nextLine();
        System.out.println("请输入监测地点：");
        String monitorAddr = scanner.nextLine();
        System.out.println("请输入监测指标：");
        String monitorIndex = scanner.nextLine();
        System.out.println("请输入监测值");
        float indexValue = scanner.nextFloat();
        scanner.nextLine();
        
        //生成随机编号
		indexsDAO indexs_di = new indexsDAOImpl();
		String indexID = indexs_di.getNewID();//指标编号
		monitorDAO monitor_di = new monitorDAOImpl();
		String monitorID = monitor_di.getNewID();//监测编号
		
        //封装数据
		monitor m = new monitor(monitorID,monitorTime,monitorAddr,deviceID,plantID,staffID);
		indexs i = new indexs(indexID,monitorIndex,indexValue,monitorID);
        //调用接口
		add(i,m);
	}
	
	//接收用户输入的植物编号，确认记录存在，返回数据封装实体，接收用户输入数据修改实体，再连接接口【修改】
	public void toUpdate(Scanner scanner) throws Exception {
		//显示检测记录供用户选择
		
		monitorDAO monitor_di = new monitorDAOImpl();
		System.out.println("请选择监测记录编号：");
		String ID = scanner.nextLine();
		//业务验证
		if(monitor_di.existID(ID)) {
			System.out.println("监测记录不存在！");
			return;
		}
		//用户输入,返回数据，封装实体
		System.out.println("请选择信息进行修改：");
		System.out.println("1.监测基本信息");
		System.out.println("2.监测指标与监测值");
		int id = scanner.nextInt();
		scanner.nextLine();
		while(id!=2&&id!=1) {
			System.out.println("序号无效！请重新选择：");
			id = scanner.nextInt();
			scanner.nextLine();
		}
		List<Map<String, String>> result;
		Map<String,String> map;
		switch(id) {
			case 1:
				result = monitor_di.queryMonitor(ID);
				map = result.get(0);
				monitor m = new monitor(map.get("monitorID"),map.get("monitorTime"),map.get("monitorAddr"),map.get("deviceID"),map.get("plantID"),map.get("staffID"));
				
				System.out.println("请选择监测信息进行修改：");
				System.out.println("1.监测地点");
				System.out.println("2.监测时间");
				
				id = scanner.nextInt();
				scanner.nextLine();
				
				while(id!=1&&id!=2) {
					System.out.println("序号无效！请重新选择：");
					id = scanner.nextInt();
					scanner.nextLine();
				}
				switch(id) {
					case 1:
				        System.out.println("请输入监测地点：");
				        String monitorAddr = scanner.nextLine();
				        m.set_monitorAddr(monitorAddr);
						break;
					case 2:
				        System.out.println("请输入监测时间：");
				        String monitorTime = scanner.nextLine();
				        m.set_monitorTime(monitorTime);
						break;
				}
				//调用接口
				update(m);
				break;
			case 2:
				indexsDAO index_di = new indexsDAOImpl();
				result = index_di.queryIndexs(ID);
				map = result.get(0);
				indexs i = new indexs(map.get("indexID"),map.get("indexName"),Float.parseFloat(map.get("indexValue")),map.get("monitorID"));
				
		        System.out.println("请输入监测指标：");
		        String indexName = scanner.nextLine();
		        i.set_indexName(indexName);
		        System.out.println("请输入监测值");
		        float indexValue = scanner.nextFloat();
		        scanner.nextLine();
		        i.set_indexValue(indexValue);
				//调用接口
		        update(i);
				break;
		}
	}
}
