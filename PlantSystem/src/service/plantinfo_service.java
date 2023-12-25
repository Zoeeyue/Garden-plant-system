import java.util.Date;

import DAO.plant_infoDAO;
import bean.PlantInfo;
import DAO.Impl.plant_infoDAOImpl;
import bean.PlantInfo;
public class plantinfo_service {
    public static void main(String[] args) {
        plant_infoDAO dao =new plant_infoDAOImpl();
        PlantInfo pf=new PlantInfo("0004", "测试植物名", "测试", "测试", "测试", "测试", null, "zy", new Date(), new Date());
        try {
			dao.queryAllPf();
//			dao.deletePlant("0004");
			pf.setPlantName("第二次更新测试植物名");
			dao.updatePlant(pf);
//			boolean flag=dao.insertPlant(pf);
//			if(flag)
//				System.out.println("插入植物数据成功");
//			else 
//				System.out.println("插入植物数据失败");
			
			//测试根据植物id查植物
			pf=dao.getPlantInfoById("0001");
			System.out.println(pf.getFeatures());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

}
