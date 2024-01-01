import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DAO.admin_infoDAO;
import DAO.Impl.admin_infoDAOImpl;
import bean.adminInfo;

import DAO.plant_infoDAO;
import DAO.image_infoDAO;
import bean.ImageInfo;
import bean.PlantDetailView;
import bean.PlantInfo;
import bean.PlantUnionView;
import DAO.Impl.plant_infoDAOImpl;
import DAO.Impl.image_infoDAOImpl;
import bean.PlantInfo;
import DAO.*;
import DAO.Impl.*;

public class plantinfo_service {
//	login_plantinfo_service(){
//		
//	}
	
//	 public static void main(String[] args) {
//			
//	    	Scanner scanner = new Scanner(System.in);
//	    	
//	    	adminInfo af = getadminInfoFromUser(scanner);
//	     
//	        admin_infoDAO adminDAO =new admin_infoDAOImpl();
//
//			try {
//		     adminInfo isaf=adminDAO.login(af.getAdminId(), af.getPassword());
//		     if(isaf==null) {
//		    	 System.out.print("账号或密码不正确，登录失败！");
//		     }
//		     else
//		     {
//		    	 System.out.println("欢迎！"+isaf.getName());
//		    	 System.out.println("您可以1.管理植物基本信息2.管理植物分类信息");
//		    	  // 获取用户输入
//		         int choice = scanner.nextInt();
//
//		         // 使用 switch-case 处理不同选择
//		         switch (choice) {
//		             case 1:
//		                 System.out.println("1.管理植物基本信息");
//		                 // 调用相关逻辑
//		                 displayPlantInfoMenu();
//		                 break;
//		             case 2:
//		                 System.out.println("2.管理植物分类信息");
//		                 // 调用相关逻辑
//		                 displayPlantSortMenu();
//		                 break;
//		             default:
//		                 System.out.println("无效选择，请重新运行程序并输入有效数字。");
//		                 break;
//		         }
//
//		         // 关闭 Scanner
//		         scanner.close();
//		    	 
//		     }
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
// 
//		 
//	 }

//	public void displayPlantSortMenu() {
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            // 显示植物信息管理菜单
//            System.out.println("植物分类信息管理菜单:");
//            System.out.println("1. 查询所有植物信息");
//            System.out.println("2. 添加植物信息");
//            System.out.println("3. 更新植物信息");
//            System.out.println("4. 删除植物信息");
//            System.out.println("5. 统计平台中每科植物的数量");
//            System.out.println("6. 根据任意属性或属性组合查询所需的园林植物信息");
//            System.out.println("7. 退出");
//
//            // 获取用户输入
//            int choice = scanner.nextInt();
//
//            // 使用 switch-case 处理不同选择
//            switch (choice) {
//                case 1:
//                    System.out.println("查询植物信息");
//                    // 调用查询植物信息的逻辑
//                    plant_infoDAOImpl plantDao=new plant_infoDAOImpl();
//				try {
//					plantDao.queryAllPf();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//                    break;
//                case 2:
//                    System.out.println("添加植物信息");
//                    // 调用添加植物信息的逻辑
//                  
//                 
//                    break;
//                case 3:
//                    System.out.println("更新植物信息");
//                    // 调用更新植物信息的逻辑
//                    break;
//                case 4:
//                    System.out.println("删除植物信息");
//                    // 调用删除植物信息的逻辑
//                    break;
//                case 5:
//                    System.out.println("删除植物信息");
//                    // 调用删除植物信息的逻辑
//                    break;
//                case 6:
//                    System.out.println("退出");
//                    // 退出菜单循环
//                    scanner.close();
//                    System.exit(0);
//                default:
//                    System.out.println("无效选择，请重新输入");
//                    break;
//            }
//        }
//		
//	}

	public void displayInsertPlant(plant_infoDAO plantDAO, image_infoDAO imageDAO) {
		Scanner scanner = new Scanner(System.in);
		PlantInfo plantInfo = getPlantInfoFromUser(scanner);
		try {
	        // 插入植物信息
	        boolean plantInserted= plantDAO.insertPlant(plantInfo);
	        if(!plantInserted) {
	        	System.out.println("该植物基本信息插入失败");
	        }else {
	        	System.out.println("请继续插入配图信息");
	        	// 获取配图信息
		        ImageInfo imageInfo = getImageInfoFromUser(scanner);
		        //关联上外键
		        imageInfo.setPlantId(plantInfo.getPlantId());
				// 插入配图信息
		        boolean imageInserted = imageDAO.insertImage(imageInfo);
		        
		        // 如果其中有一个插入失败，抛出异常
		       
		        if (!imageInserted) {
		        	 System.out.println("该植物配图插入失败，基本信息已插入");
		        }
	        }
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static ImageInfo getImageInfoFromUser(Scanner scanner) {
		System.out.println("请输入配图信息：");

        System.out.print("配图编号: ");
        String imgId = scanner.nextLine();
        System.out.print("配图路径: ");
        System.out.print("请注意输入格式，双斜杠，类似:(D:\\idea-project\\image_filepath\\野迎春.jpg) ");
        String imgFilename = scanner.nextLine();
        System.out.print("配图拍摄地点: ");
        String place = scanner.nextLine();
        System.out.print("配图拍摄者: ");
        String taker = scanner.nextLine();
        System.out.print("配图描述: ");
        String detail = scanner.nextLine();

        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setImageId(imgId);
        imageInfo.setFilename(imgFilename);
        imageInfo.setPlace(place);
        imageInfo.setCreatedBy(taker);
        imageInfo.setDetail(detail);
       

        return imageInfo;
	}

	
	private static PlantInfo getUpdateInfoFromUser(Scanner scanner) {
		    System.out.println("请输入需要更新的植物编号：");
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
	private static PlantInfo getPlantInfoFromUser(Scanner scanner) {
			   System.out.println("请输入植物基本信息：");
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

	private static adminInfo getadminInfoFromUser(Scanner scanner) {
		System.out.println("您选择以系统管理员身份登录，请输入您的账号：");
		String adminId = scanner.nextLine();
        System.out.println("请输入您的密码: ");
        String admin_password = scanner.nextLine();

        adminInfo af = new adminInfo();
        af.setAdminId(adminId);
        af.setPassword(admin_password);

        return af;
	}
	
//	private static void displayPlantInfoMenu() {
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            // 显示植物信息管理菜单
//        	plant_infoDAO plantDAO =new plant_infoDAOImpl();
//            image_infoDAO imageDAO =new image_infoDAOImpl();
//            System.out.println("植物基本信息管理菜单:");
//            System.out.println("1. 查询所有植物信息");
//            System.out.println("2. 添加植物信息");
//            System.out.println("3. 更新植物信息");
//            System.out.println("4. 删除植物信息");
//            System.out.println("5. 统计平台中每科植物的数量");
//            System.out.println("6. 根据任意属性或属性组合查询所需的园林植物信息");
//            System.out.println("7. 退出该基本管理信息菜单");
//            plant_infoDAOImpl plantDao=new plant_infoDAOImpl();
//            // 获取用户输入
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            // 使用 switch-case 处理不同选择
//            switch (choice) {
//                case 1:
//                    System.out.println("查询所有植物信息如下：");
//                    // 调用查询植物信息的逻辑
//				try {
//					displayQuery(plantDao.queryAllPf());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				    
//                    break;
//                case 2:
//                    System.out.println("添加植物信息");
//                    // 调用添加植物信息的逻辑
//                    displayInsertPlant(plantDAO,imageDAO);
//                    break;
//                case 3:
//                    System.out.println("更新植物信息");
//                    // 调用更新植物信息的逻辑
//                    
//				try {
//					System.out.println("有以下植物可以更新，请选择");
//                    displayQuery(plantDao.queryAllPf());
//                    System.out.println("请输入需要更新的植物编号：");
//        	        String plantId = scanner.nextLine();
//					if(!plantDao.isPlantIdExists(plantId))
//				    {
//				    System.out.println("该植物不存在！");
//                    break;
//                    }else {
//                    System.out.println("以下是你选择更新的植物信息 ：");
//                    displayQuery(plantDao.queryFromViewById(plantId));
//                    PlantInfo plantinfo=plantDAO.getPlantInfoById(plantId);
//                    updateSelect(scanner, plantDAO, plantinfo);
//                    
//                    }
//				} catch (Exception e) {
//					
//					e.printStackTrace();
//				}
//				break;
//                case 4:
//                    System.out.println("删除植物信息");
//                    // 调用删除植物信息的逻辑
//                   
//				try {
//					 System.out.println("有以下植物可以删除，请选择");
//	                    displayQuery(plantDao.queryAllPf());
//	                    System.out.println("请输入需要删除的植物编号：");
//	        	        String deleteId = scanner.nextLine();
//					if(!plantDao.isPlantIdExists(deleteId))
//				    {
//				    System.out.println("该植物不存在！");
//                    break;
//                    }else {
//                     displayDeletePlant(plantDAO, imageDAO, deleteId);
//                    }
//				} catch (Exception e) {
//					
//					e.printStackTrace();
//				}
//                    break;
//                case 5:
//                    System.out.println("统计平台中每科植物的数量");
//                    // 调用统计植物信息的逻辑
//				try {
//					plantDAO.countPlantsByScientificName();
//				} catch (Exception e) {
//				
//					e.printStackTrace();
//				}
//                    break;
//                case 6:
//                    System.out.println("根据任意属性或属性组合查询所需的园林植物信息");
//                    // 调用根据任意属性或属性组合查询所需的园林植物信息的逻辑
//                    // 提示用户输入查询条件
//                    System.out.println("请输入查询条件（属性及值），多个条件之间用逗号分隔，例如：植物种名=野迎春,植物编号=0001");
//                    String userInput = scanner.nextLine();
//
//                    // 解析用户输入，构建属性及其值的映射
//                    Map<String, Object> properties = parseUserInput(userInput);
//
//                    // 调用查询逻辑
//                    try {
//                    	 List<PlantDetailView> plantDetailViewList1= plantDAO.queryFromViewByProperties(properties);
//                    	 displayQuery(plantDetailViewList1);
//                       
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case 7:
//                    System.out.println("退出该基本管理信息菜单");
//                    // 退出菜单循环
//                    scanner.close();
//                    System.exit(0);
//                default:
//                    System.out.println("无效选择，请重新输入");
//                    break;
//            }
//        }
//    }

	public  Map<String, Object> parseUserInput(String userInput) {
        Map<String, Object> properties = new HashMap<>();
        
        // 分割用户输入的属性及值
        String[] conditions = userInput.split(",");
        for (String condition : conditions) {
            // 分割属性及值
            String[] keyValue = condition.split("=");
            
            // 将属性及值放入映射
            if (keyValue.length == 2) {
                properties.put(keyValue[0], keyValue[1]);
            } else {
                System.out.println("输入格式不正确，请按照属性=值,属性=值的格式输入。");
            }
        }

        return properties;
    }

	public void displayDeletePlant(plant_infoDAO plantDAO, image_infoDAO imageDAO, String deleteId)
			throws Exception {
		                    List<ImageInfo> imageinfo=imageDAO.getImageInfoByPlantId(deleteId);
		//                   
		                    // 删除关联的所有图片信息
		                    for (ImageInfo image : imageinfo) {
		                        imageDAO.deleteImg(image.getImageId());;
		                    }
		
		                    // 删除植物信息
		                    plantDAO.deletePlant(deleteId);
		
		                    System.out.println("植物信息删除成功！");
	}

	public void updateSelect(Scanner scanner, plant_infoDAO plantDAO, PlantInfo plantinfo) throws Exception {
		System.out.println("请输入你要更新的信息:");
		System.out.println("1. 更新形态特征");
		System.out.println("2. 更新栽培技术");
		System.out.println("3. 更新应用价值");
		System.out.println("4. 更新创建人");
		System.out.println("5. 退出更新");
             // 获取用户输入
		int choice2 = scanner.nextInt();
		scanner.nextLine();
		

		// 使用 switch-case 处理不同选择
		switch (choice2) {
		    case 1:
		        System.out.println("请输入新的形态特征：");
		        // 调用查询植物信息的逻辑
		        String new_features = scanner.nextLine();
		        plantinfo.setFeatures(new_features);
		        plantDAO.updatePlant(plantinfo);
			  
		        break;
		    case 2:
		        System.out.println("请输入新的栽培技术：");
		        // 调用查询植物信息的逻辑
		        String new_tech= scanner.nextLine();
		        plantinfo.setCultivationTechniques(new_tech);
		        plantDAO.updatePlant(plantinfo);
			  
		        break;
		    case 3:
		        System.out.println("请输入新的应用价值：");
		        // 调用查询植物信息的逻辑
		        String new_value= scanner.nextLine();
		        plantinfo.setValue(new_value);
		        plantDAO.updatePlant(plantinfo);
			  
		        break;
		        
		    case 4:
		        System.out.println("请输入新的创建人：");
		        // 调用查询植物信息的逻辑
		        String new_person= scanner.nextLine();
		        plantinfo.setCreatedBy(new_person);
		        plantDAO.updatePlant(plantinfo);
			  
		        break;
		    case 5:
		        System.out.println("退出更新");
		        // 退出菜单循环
		        scanner.close();
		        System.exit(0);
		    default:
		        System.out.println("无效选择，请重新输入");
		        break;
		}
	}

	public void displayQuery(List<PlantDetailView> plantDetailViewList) {
		try {
			
              
		       // 遍历并打印每个 PlantDetailView 的信息
		       for (PlantDetailView plantDetailView : plantDetailViewList) {
		           System.out.println("植物编号: " + plantDetailView.getPlantId());
		           System.out.println("植物种名: " + plantDetailView.getPlantName());
		           if(plantDetailView.getFamilyName()==null&&plantDetailView.getAlias()==null) {
		        	   System.out.println("科名: " + "未知");
			           System.out.println("别名: " + "未知");
		           }else {
		           System.out.println("科名: " + plantDetailView.getFamilyName());
		           System.out.println("别名: " + plantDetailView.getAlias());
		           }
		           System.out.println("形态特征: " + plantDetailView.getFeatures());
		           System.out.println("栽培技术: " + plantDetailView.getCultivation_tech());
		           System.out.println("应用价值: " + plantDetailView.getValue());
		           System.out.println("创建者: " + plantDetailView.getCreatedBy());
		           System.out.println("配图编号: " + plantDetailView.getImageId());
		           System.out.println("配图文件路径: " + plantDetailView.getFilename());
		           System.out.println("拍摄地点: " + plantDetailView.getPlace());
		           System.out.println("配图描述: " + plantDetailView.getDetail());
		           System.out.println("拍摄人: " + plantDetailView.getCreater());

		           // 日期的打印
		           System.out.println("创建时间: " + plantDetailView.getCreatedTime());
		           System.out.println("更新时间: " + plantDetailView.getUpdatedTime());
		           
		           displayImage(plantDetailView.getFilename(),plantDetailView.getPlantName());

		           System.out.println(); // 为了格式化输出，可根据实际需要调整
		       }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
    
	public void displayQueryUnion(List<PlantUnionView> plantUnionViewList) {
		try {
			
              
		       // 遍历并打印每个 PlantDetailView 的信息
		       for (PlantUnionView plantDetailView : plantUnionViewList) {
		    	   System.out.println("植物基本信息如下: ");
		           System.out.println("植物编号: " + plantDetailView.getPlantId());
		           System.out.println("植物种名: " + plantDetailView.getPlantName());
		           System.out.println("配图文件路径: " + plantDetailView.getFilename());
		           System.out.println("形态特征: " + plantDetailView.getFeatures());
		           System.out.println("栽培技术: " + plantDetailView.getCultivationTechniques());
		           System.out.println("应用价值: " + plantDetailView.getValue());
		           System.out.println("创建者: " + plantDetailView.getCreatedBy());
		           System.out.println("该植物分类信息如下: ");
		           if(plantDetailView.getFamilyName()==null&&plantDetailView.getGenusName()==null&&plantDetailView.getAlias()==null) {
		        	   System.out.println("科名: " + "未知");
		        	   System.out.println("属名: " + "未知");
			           System.out.println("别名: " + "未知");
		           }else {
		           System.out.println("科名: " + plantDetailView.getFamilyName());
		           System.out.println("属名: " + plantDetailView.getGenusName());
		           System.out.println("别名: " + plantDetailView.getAlias());
		           System.out.println("生长区域: " + plantDetailView.getGrowEnv());
		           
		           }
		           System.out.println("该植物养护信息如下: ");
		           System.out.println("养护任务编号: " + plantDetailView.getuTaskId());
		           System.out.println("养护名称: " + plantDetailView.getUpkeepName());
		           System.out.println("养护描述: " + plantDetailView.getUpkeepDes());
		           System.out.println("养护地点: " + plantDetailView.getUpkeepPlace());
		           System.out.println("养护人员: " + plantDetailView.getUpkeepSname());


		           
		           displayImage(plantDetailView.getFilename(),plantDetailView.getPlantName());

		           System.out.println(); // 为了格式化输出，可根据实际需要调整
		       }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	private static void displayUpdatePlant(plant_infoDAO plantDAO, image_infoDAO imageDAO) {
		
		Scanner scanner = new Scanner(System.in);
		PlantInfo plantInfo = getPlantInfoFromUser(scanner);
		try {
	        // 插入植物信息
	        boolean plantInserted= plantDAO.insertPlant(plantInfo);
	        if(!plantInserted) {
	        	System.out.println("该植物基本信息插入失败");
	        }else {
	        	System.out.println("请继续插入配图信息");
	        	// 获取配图信息
		        ImageInfo imageInfo = getImageInfoFromUser(scanner);
		        //关联上外键
		        imageInfo.setPlantId(plantInfo.getPlantId());
				// 插入配图信息
		        boolean imageInserted = imageDAO.insertImage(imageInfo);
		        
		        // 如果其中有一个插入失败，抛出异常
		       
		        if (!imageInserted) {
		        	 System.out.println("该植物配图插入失败，基本信息已插入");
		        }
	        }
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void displayImage(String path,String name) {
		 // 创建 ImageIcon 实例
	    ImageIcon icon = new ImageIcon(path);

	    // 创建 JLabel 实例，并将 ImageIcon 设置为其图标
	    JLabel label = new JLabel(icon);

	    // 设置 JFrame 大小
	    JFrame frame = new JFrame(name);
	    frame.getContentPane().add(label);
	    frame.setSize(500, 400);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口时不退出整个应用
	    frame.setVisible(true);
	}
}
