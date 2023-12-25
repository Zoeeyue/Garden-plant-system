import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.plant_infoDAO;
import DAO.image_infoDAO;
import bean.ImageInfo;
import bean.PlantInfo;
import DAO.Impl.plant_infoDAOImpl;
import DAO.Impl.image_infoDAOImpl;
import bean.PlantInfo;

public class plantinfo_service {
    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	PlantInfo plantInfo = getPlantInfoFromUser(scanner);
          
          // 获取配图信息
        ImageInfo imageInfo = getImageInfoFromUser(scanner);
        plant_infoDAO plantDAO =new plant_infoDAOImpl();
        image_infoDAO imageDAO =new image_infoDAOImpl();

		try {
	        // 插入植物信息
	        boolean plantInserted= plantDAO.insertPlant(plantInfo);
			// 插入配图信息
	        boolean imageInserted = imageDAO.insertImage(imageInfo);

	        // 如果其中有一个插入失败，抛出异常
	        if (!plantInserted || !imageInserted) {
	            throw new Exception("插入失败");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}

        


//        PlantInfo pf=new PlantInfo("0004", "测试植物名", "测试", "测试", "测试", "测试", null, "zy", new Date(), new Date());
//        try {
//			dao.queryAllPf();
////			dao.deletePlant("0004");
////			pf.setPlantName("第二次更新测试植物名");
////			dao.updatePlant(pf);
////			boolean flag=dao.insertPlant(pf);
////			if(flag)
////				System.out.println("插入植物数据成功");
////			else 
////				System.out.println("插入植物数据失败");
//			
//			//测试根据植物id查植物
//			pf=dao.getPlantInfoById("0001");
//			System.out.println(pf.getFeatures());
//			//测试查找不同科的植物数量
//			dao.countPlantsByScientificName();
//			//测试属性及属性组合查询
//			// 构建属性及其值的映射
//			Map<String, Object> properties = new HashMap<>();
//			properties.put("plant_name", "野迎春");
//			properties.put("plant_id", "0001");
////			properties.put("distribution_area", "亚洲");
//			 List<PlantInfo> result =dao.queryPlantsByProperties(properties);
////			 String plantId = "your_plant_id"; // 传入植物ID
//			 image_infoDAO imageInfoDAO = new image_infoDAOImpl(); // 请替换成你的DAO实现类
//
//			    // 调用方法获取配图信息列表
//			 List<ImageInfo> imageInfoList = imageInfoDAO.getImageInfoById("0001");
//
//			   // 遍历处理配图信息
//			  for (ImageInfo imageInfo : imageInfoList) {
//			        System.out.println("植物配图路径: " + imageInfo.getFilename());
////			        System.out.println("Plant ID: " + imageInfo.getPlantId());
////			        // 处理其他属性...
//			    }
//			// 处理查询结果
//			    for (PlantInfo plantInfo : result) {
//			        System.out.println("植物编号: " + plantInfo.getPlantId());
//			        System.out.println("植物名称: " + plantInfo.getPlantName());
//			        System.out.println("植物形态特征: " + plantInfo.getFeatures());
//			        // 其他属性...
//			    }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
    }

	private static ImageInfo getImageInfoFromUser(Scanner scanner) {
		System.out.println("请输入配图信息：");

        System.out.print("配图编号: ");
        String imgId = scanner.nextLine();
        System.out.print("配图路径: ");
        String imgFilename = scanner.nextLine();

        // 其他属性的输入，根据你的配图信息表的字段来

        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setImageId(imgId);
        imageInfo.setFilename(imgFilename);
//        imageInfo.setPlantId(plantId);
        // 设置其他属性...

        return imageInfo;
	}

	private static PlantInfo getPlantInfoFromUser(Scanner scanner) {
		   System.out.println("请输入植物信息：");
	        System.out.print("植物编号: ");
	        String plantId = scanner.nextLine();
	        plant_infoDAO plantDAO =new plant_infoDAOImpl();
	        System.out.print("植物种名: ");
	        String plantName = scanner.nextLine();
	        System.out.print("形态特征: ");
	        String features = scanner.nextLine();
	        System.out.print("栽培技术: ");
	        String cultivation_tech = scanner.nextLine();
	        System.out.print("应用价值: ");
	        String value = scanner.nextLine();
	        System.out.print("创建人: ");
	        String creater = scanner.nextLine();

	        // 其他属性的输入，根据你的植物信息表的字段来

	        PlantInfo plantInfo = new PlantInfo();
	        plantInfo.setPlantId(plantId);
	        plantInfo.setPlantName(plantName);
	        plantInfo.setFeatures(features);
	        plantInfo.setCultivationTechniques(cultivation_tech);
	        plantInfo.setValue(value);
	        plantInfo.setCreatedBy(creater);
	        // 设置其他属性...

	        return plantInfo;
	}

}

