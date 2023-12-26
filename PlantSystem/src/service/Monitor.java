package service;

import DAO.Impl.deviceDAOImpl;
import DAO.Impl.errorDAOImpl;
import DAO.Impl.indexsDAOImpl;
import DAO.Impl.monitorDAOImpl;
import DAO.Impl.staffDAOImpl;
import bean.device;
import bean.indexs;
import bean.monitor;
import bean.staff;

//业务5：监测
public class Monitor {
	//增加监测记录
	public void add(device d,indexs i,monitor m,staff s) throws Exception {
//		deviceDAOImpl device_di = new deviceDAOImpl();
//		staffDAOImpl staff_di = new staffDAOImpl();
//		device_di.insertDevice(d);//监测设备-固定-可单独添加（购进设备）
//		staff_di.insertStaff(s);//监测人员-固定-课单独添加（新雇员工）
		
		indexsDAOImpl indexs_di = new indexsDAOImpl();
		monitorDAOImpl monitor_di = new monitorDAOImpl();
		//【缺主码的唯一性验证】
		monitor_di.insertMonitor(m);//监测-业务
		indexs_di.insertIndexs(i);//监测指标
	}
	//删除监测记录
	public void delete(String indexID) throws Exception {
		indexsDAOImpl indexs_di = new indexsDAOImpl();
		monitorDAOImpl monitor_di = new monitorDAOImpl();
		//【缺主码的存在性验证】
		indexs_di.deleteIndexs(indexID);//监测指标
		monitor_di.deleteMonitor(indexID);//监测
	}
	//修改监测记录
	public void update(String ID) {
		
	}
	//查询监测记录——视图
	public void search(String searchTerm) throws Exception {
		monitorDAOImpl monitor_di = new monitorDAOImpl();
		monitor_di.queryMonitorSystem(searchTerm);
	}
	//查看监测记录——视图
	public void list() throws Exception {
		monitorDAOImpl monitor_di = new monitorDAOImpl();
		monitor_di.listMonitorSystem();
	}
	//指标分析——视图
	public void analysis() throws Exception {
		indexsDAOImpl indexs_di = new indexsDAOImpl();
		indexs_di.IndexsValues();
	}
	//查看异常指标记录——视图
	public void showError() throws Exception {
		errorDAOImpl error_di = new errorDAOImpl();
		error_di.queryError();
	}
	//单独添加监测设备（购进设备）
	public void add_device(device d) throws Exception {
		deviceDAOImpl device_di = new deviceDAOImpl();
		device_di.insertDevice(d);
	}	
	//单独删除监测设备（设备报废）
	public void delete_device(String deviceID) throws Exception {
		deviceDAOImpl device_di = new deviceDAOImpl();
		device_di.deleteDevice(deviceID);
	}
	//单独删除监测人员（员工离职）
	public void delete_staff(String staffID) throws Exception {
		staffDAOImpl staff_di = new staffDAOImpl();
		staff_di.deleteStaff(staffID);
	}
	
	
	
	
	
	
	//测试
	public void test() throws Exception {
		device d = new device("shebei0001","设备2");
		indexs i = new indexs("zhibiao001","空气含氧量","200","MONIT00008");
		monitor m = new monitor("MONIT00008","2023-10-1","bjf1","shebei0001","PLANT00002","211002327_");
		staff s = new staff("211002327_","Kyenlyn","123");
		add(d,i,m,s);
	}
	public static void main(String[] args) throws Exception {
		Monitor d = new Monitor();
		d.test();
	}
}
