

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.diseaseDAO;
import DAO.expertDAO;
import DAO.medicineDAO;
import DAO.plant_infoDAO;
import DAO.treatmentDAO;
import DAO.Impl.diseaseDAOImpl;
import DAO.Impl.expertDAOImpl;
import DAO.Impl.medicineDAOImpl;
import DAO.Impl.plant_infoDAOImpl;
import DAO.Impl.treatmentDAOImpl;
import bean.ViewDisease;
import bean.ViewDiseaseUpkeep;
import bean.disease;
import bean.expert;
import bean.medicine;
import bean.treatment;

//业务4：病虫害
public class Disease {
	//增加病虫害及防治措施
	public void add(medicine m,treatment t,disease d) throws Exception {
		diseaseDAO disease_di = new diseaseDAOImpl();
		medicineDAO medicine_di = new medicineDAOImpl();
		treatmentDAO treatment_di = new treatmentDAOImpl();
		//生成id——在封装实体的时候实现
		disease_di.insertDisease(d);//病虫害
		treatment_di.insertTreatment(t);//防治方法
		medicine_di.insertMedicine(m);//药剂
		System.out.println("添加成功！");
	}
	//删除病虫害及防治措施
	public boolean delete(String medicineID) throws Exception {
		diseaseDAO disease_di = new diseaseDAOImpl();
		medicineDAO medicine_di = new medicineDAOImpl();
		treatmentDAO treatment_di = new treatmentDAOImpl();
		List<Map<String, String>> set_treatmentID = treatment_di.getIDbyMedicineID(medicineID);//获取防治方法ID
		List<Map<String, String>> set_diseaseID = disease_di.getIDbyMedicineID(medicineID);//获取病虫害ID
		
		String treatmentID = set_treatmentID.get(0).get("treatmentID");//获取防治方法ID
		int medicineNUM = treatment_di.getMedicineNUMbyID(treatmentID);//该方法使用的药剂数量
		String diseaseID = set_diseaseID.get(0).get("diseaseID");//获取病虫害ID
		int treatmentNUM = disease_di.getTreatmentNUMbyID(diseaseID);//该病虫害使用的防治方法数量
		if(medicine_di.existID(medicineID)) {
			System.out.println("病虫害信息不存在！");
			return false;
		}
		if(!medicine_di.deleteMedicine(medicineID)) {
			System.out.println("删除药剂表失败！");
			return false;
		}
		if(medicineNUM==1) {//该方法使用的药剂只有一种，则删除该方法
			if(!treatment_di.deleteTreatment(treatmentID)) {
				System.out.println("删除防治方法表失败！");
				return false;
			}
			if(treatmentNUM==1) {//该病虫害使用的防治方法只有一种，则删除该病虫害
				if(!disease_di.deleteDisease(diseaseID)) {
					System.out.println("删除病虫害表失败！");
					return false;
				}
			}
		}
//		System.out.println("删除成功！");
		return true;
	}
	//修改病虫害及防治措施
	public void update(Object entity) throws Exception {
		if (entity instanceof disease) {
			disease d = (disease) entity;
			diseaseDAO disease_di = new diseaseDAOImpl();
			disease_di.updateDisease(d);
	    } else if (entity instanceof treatment) {
	    	treatment t = (treatment) entity;
	    	treatmentDAO treatment_di = new treatmentDAOImpl();
	    	treatment_di.updateTreatment(t);
	    }else if (entity instanceof medicine) {
	    	medicine m = (medicine) entity;
	    	medicineDAOImpl medicine_di = new medicineDAOImpl();
	    	medicine_di.updateMedicine(m);
	    }
		System.out.println("修改成功！");
	}
	//查询病虫害及防治措施——视图
	public void search(String searchTerm) throws Exception {
		diseaseDAO disease_di = new diseaseDAOImpl();
		List<Map<String, String>> result = disease_di.queryDiseaseSystem(searchTerm);
		for(Map<String, String> map : result) {
			ViewDisease m = new ViewDisease(map.get("id"),map.get("plant_name"),map.get("diseaseName"),map.get("treatmentName"),map.get("medicineName"),map.get("medicineDosage"),map.get("medicineDuration"));
			m.toPrint();
		}
	}
	//查看病虫害及防治措施——视图
	public void list() throws Exception {
		diseaseDAO disease_di = new diseaseDAOImpl();
		List<Map<String, String>> result = disease_di.listDiseaseSystem();
		for(Map<String, String> map : result) {
			ViewDisease m = new ViewDisease(map.get("id"),map.get("plant_name"),map.get("diseaseName"),map.get("treatmentName"),map.get("medicineName"),map.get("medicineDosage"),map.get("medicineDuration"));
			m.toPrint();
		}
	}
	//查看病虫害养护状态——视图
	public void listUpkeep() throws Exception {
		diseaseDAO disease_di = new diseaseDAOImpl();
		List<Map<String, String>> result = disease_di.ShowUpkeepStats();
		for(Map<String, String> map : result) {
			ViewDiseaseUpkeep m = new ViewDiseaseUpkeep(map.get("UTaskId"),map.get("plant_name"),map.get("diseaseName"),map.get("treatmentName"),map.get("medicineName"),map.get("UTaskStatus"));
			m.toPrint();
		}
	}
	//接收用户输入的数据进行封装，再连接接口【添加】
	public void toAdd(Scanner scanner) throws Exception {
		//业务验证
        System.out.println("请输入植物编号：");
        String plantID = scanner.nextLine();
        plant_infoDAO plant_di = new plant_infoDAOImpl();
		if(!plant_di.isPlantIdExists(plantID)) {
			System.out.println("该植物不存在！");
			return;
		}
        //用户输入
        System.out.println("请输入病虫害名：");
        String diseaseName = scanner.nextLine();
        System.out.println("请输入防治方法：");
        String treatmentName = scanner.nextLine();
        System.out.println("请输入方法内容：");
        String treatmentCont = scanner.nextLine();
        System.out.println("请输入药剂名称");
        String medicineName = scanner.nextLine();
        System.out.println("请输入药剂用量：");
        String medicineDosage = scanner.nextLine();
        System.out.println("请输入作用期限：");
        String medicineDuration = scanner.nextLine();
        
        //生成随机编号
		diseaseDAO disease_di = new diseaseDAOImpl();
		String diseaseID = disease_di.getNewID();//病虫害编号编号
		treatmentDAO treatment_di = new treatmentDAOImpl();
		String treatmentID = treatment_di.getNewID();//防治方法编号
		medicineDAO medicine_di = new medicineDAOImpl();
		String medicineID = medicine_di.getNewID();//药剂编号
		
        //封装数据
		medicine m = new medicine(medicineID,medicineName,medicineDosage,medicineDuration,treatmentID);
		treatment t = new treatment(treatmentID,treatmentName,treatmentCont,diseaseID);
		disease d = new disease(diseaseID,diseaseName,plantID);
        //调用接口
		add(m,t,d);
	}

	public void toUpdate(Scanner scanner) throws Exception {
		//显示检测记录供用户选择
		medicineDAO medicine_di = new medicineDAOImpl();
		diseaseDAO disease_di = new diseaseDAOImpl();
		disease_di.listDiseaseShow();
		System.out.println("请选择病虫害防治记录编号：");
		String ID = scanner.nextLine();
		//业务验证
		if(disease_di.existID2(ID)) {
			System.out.println("病虫害防治记录不存在！");
			return;
		}
		//用户输入,返回数据，封装实体
		System.out.println("请选择信息进行修改：");
		System.out.println("1.病虫害基本信息");
		System.out.println("2.防治方法基本信息");
		System.out.println("3.药剂基本信息");
		int id = scanner.nextInt();
		scanner.nextLine();
		while(id!=2&&id!=1&&id!=3) {
			System.out.println("序号无效！请重新选择：");
			id = scanner.nextInt();
			scanner.nextLine();
		}
		List<Map<String, String>> result;
		Map<String,String> map;
		switch(id) {
			case 1:
				result = disease_di.queryDisease2(ID);
				map = result.get(0);
				disease d = new disease(map.get("diseaseID"),map.get("diseaseName"),map.get("plantID"));
			
				System.out.println("请输入病虫害名称：");
		        String diseaseName = scanner.nextLine();
		        d.set_diseaseName(diseaseName);
				//调用接口
				update(d);
				break;
			case 2:
				treatmentDAO treatment_di = new treatmentDAOImpl();
				result = treatment_di.queryTreatment2(ID);
				map = result.get(0);
				treatment t = new treatment(map.get("treatmentID"),map.get("treatmentName"),map.get("treatmentCont"),map.get("diseaseID"));
				
		        System.out.println("请输入防治方法名称：");
		        String treatmentName = scanner.nextLine();
		        t.set_treatmentName(treatmentName);
		        System.out.println("请输入防治方法内容：");
		        String treatmentCont = scanner.nextLine();
		        t.set_treatmentCont(treatmentCont);
				//调用接口
		        update(t);
				break;
			case 3:
				result = medicine_di.queryMedicine(ID);
				map = result.get(0);
				medicine m = new medicine(map.get("medicineID"),map.get("medicineName"),map.get("medicineDosage"),map.get("medicineDuration"),map.get("treatmentID"));
				
		        System.out.println("请输入药剂名称：");
		        String medicineName = scanner.nextLine();
		        m.set_medicineName(medicineName);
		        System.out.println("请输入药剂用量：");
		        String medicineDosage = scanner.nextLine();
		        m.set_medicineDosage(medicineDosage);
		        System.out.println("请输入作用期限：");
		        String medicineDuration = scanner.nextLine();
		        m.set_medicineDuration(medicineDuration);
				//调用接口
		        update(m);
				break;
		}	
	}
	
	//管理专家（主管）
	public void manageExpert(Scanner scanner) throws Exception {
		expertDAO expert_di = new expertDAOImpl();
		System.out.println("管理专家信息：");
		System.out.println("1.查看专家");
		System.out.println("2.增加专家");
		System.out.println("3.删除专家");
		int choice = scanner.nextInt();
		scanner.nextLine();
        while(choice!=2&&choice!=1&&choice!=3) {
			System.out.println("序号无效！请重新选择：");
			choice = scanner.nextInt();
			scanner.nextLine();
		}
		String ID;//用户输入
		
		switch(choice) {
		case 1:
			System.out.println("查看专家");
			expert_di.listExpert();
			List<Map<String, String>> result = expert_di.listExpert();
			for(Map<String, String> map : result) {
				expert m = new expert(map.get("expertID"),map.get("expertName"),map.get("expertPwd"));
		        System.out.println("专家编号: " + m.get_expertID());
		        System.out.println("专家名称: " + m.get_expertName());
		        System.out.println("专家密码: " + m.get_expertPwd());
		        System.out.println("——————————————————————————————————————");
			}
			break;
		case 2:
			System.out.println("增加专家");
            System.out.println("请输入专家编号：");
            ID = scanner.nextLine();
    		if(!expert_di.existID(ID)) {
    			System.out.println("该专家已存在！");
    			return;
    		}
            System.out.println("请输入专家名称：");
            String name = scanner.nextLine();
            System.out.println("请输入专家密码：");
            String pwd = scanner.nextLine();
            expert e = new expert(ID,name,pwd);
            add_expert(e);
			break;
		case 3:
			System.out.println("删除专家");
            System.out.println("请输入专家编号：");
            ID = scanner.nextLine();
    		if(expert_di.existID(ID)) {
    			System.out.println("该专家不存在！");
    			return;
    		}
    		delete_expert(ID);
			break;
		}
	}
	//单独删除专家（离职）
	public void delete_expert(String expertID) throws Exception {
		expertDAO expert_di = new expertDAOImpl();
		if(expert_di.deleteExpert(expertID)){
			System.out.println("删除成功！");
		}else {
			System.out.println("该专家与相关记录关联，暂不能删除！");
		}
		
	}
	//单独添加专家（入职）
	public void add_expert(expert e) throws Exception {
		expertDAO expert_di = new expertDAOImpl();
		expert_di.insertExpert(e);
		System.out.println("添加成功！");
	}
}
