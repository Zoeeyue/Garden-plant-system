package service;

import java.util.List;
import java.util.Map;

import DAO.Impl.diseaseDAOImpl;
import DAO.Impl.medicineDAOImpl;
import DAO.Impl.treatmentDAOImpl;
import bean.disease;
import bean.medicine;
import bean.treatment;

//业务4病虫害
public class Disease {
	//增加病虫害及防治措施记录
	public void add(medicine m,treatment t,disease d) throws Exception {
		diseaseDAOImpl disease_di = new diseaseDAOImpl();
		medicineDAOImpl medicine_di = new medicineDAOImpl();
		treatmentDAOImpl treatment_di = new treatmentDAOImpl();

		//【缺主码的唯一性验证】
		disease_di.insertDisease(d);//病虫害
		treatment_di.insertTreatment(t);//防治方法
		medicine_di.insertMedicine(m);//药剂
	}
	//删除病虫害及防治措施记录
	public void delete(String medicineID) throws Exception {
		diseaseDAOImpl disease_di = new diseaseDAOImpl();
		medicineDAOImpl medicine_di = new medicineDAOImpl();
		treatmentDAOImpl treatment_di = new treatmentDAOImpl();

		//【缺主码的存在性验证】
		medicine_di.deleteMedicine(medicineID);//药剂
		treatment_di.deleteTreatment(medicineID);//防治方法
		disease_di.deleteDisease(medicineID);//病虫害
	}
	//修改病虫害及防治措施记录
	public void update() {
		
	}
	//查询病虫害及防治措施记录——视图
	public void search(String searchTerm) throws Exception {
		diseaseDAOImpl disease_di = new diseaseDAOImpl();
		disease_di.queryDiseaseSystem(searchTerm);
	}	
	//查看病虫害及防治措施记录——视图
	public void list() throws Exception {
		diseaseDAOImpl disease_di = new diseaseDAOImpl();
		disease_di.listDiseaseSystem();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void testDisease() {
		//插入药剂记录
		diseaseDAOImpl mdi = new diseaseDAOImpl();
		try {
//			List<String> valueList = new ArrayList<String>();
			List<Map<String, String>> result = mdi.queryDisease("DISEA00001");
			System.out.println("查找药品的结果: " + result);
			disease m;
			if (!result.isEmpty()) {
			    Map<String, String> update = result.get(0);
			    m = new disease(update.get("diseaseID"),update.get("diseaseName"),update.get("plantID"));
				boolean result_ = mdi.updateDisease(m);
				System.out.println("结果: " + result_);
				m.set_diseaseName("毛毛虫");
//			    for (Map.Entry<String, String> entity : update.entrySet()) {  
//			        String key = entity.getKey();
//			        String value = entity.getValue();
//			        valueList.add(value);
//			        System.out.println("Key: " + key + ", Value: " + value);
//			    }
			} else {  
			    System.out.println("Result is empty.");
			}
			//1 3 0 2 4
//			disease m = new disease(valueList.get(1),valueList.get(2),valueList.get(0),valueList.get(3));
//	        m.set_medicineID();
//			m.set_medicineDosage(valueList.get(2));
//			m.set_medicineDuration(valueList.get(0));
//			m.set_medicineName(valueList.get(3));
			
//			m.set_medicineDuration("2weeks");
//			indexs m = new indexs("INDEX00005","土壤湿度","400");
//			boolean result_ = mdi.insertIndexs(m);
//			System.out.println("插入的结果: " + result_);
//			List<Map<String, String>> result = mdi.queryMedicine("MEDIC00008");
//            System.out.println("查找药品的结果: " + result);
//			List<Map<String, String>> result = mdi.queryMonitorSystem("MONIT00001");
//			System.out.println("结果: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public static void main(String[] args) {
		Disease d = new Disease();
		d.testDisease();
	}
}
