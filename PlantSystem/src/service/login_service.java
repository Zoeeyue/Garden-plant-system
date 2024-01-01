

import java.util.Map;
import java.util.Scanner;
import bean.*;
import DAO.*;
import DAO.Impl.*;

public class login_service {
	static Disease disease = new Disease();
	static Monitor monitor = new Monitor();
	//选择角色
	public void roleChoose() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("欢迎使用园林植物信息管理系统！");
		while(true) {
			System.out.println("请选择登录角色：1.系统管理员 2.主管部门 3.养护人员 4.监测人员 5.病虫害防治专家 6.退出管理系统");
			int roleID = scanner.nextInt();
            scanner.nextLine();
			switch (roleID) {
				case 1:
					getadminInfoFromUser(scanner);
					break;
				case 2:
					getmasterInfoFromUser(scanner);
					break;
				case 3:
					getupkeepInfoFromUser(scanner);
					break;
				case 4:
					getstaffInfoFromUser(scanner);
					break;
				case 5:
					getexpertInfoFromUser(scanner);
					break;
				case 6:
					System.out.println("感谢使用，再见！");
					System.exit(0);
					break;
				default:
					System.out.println("序号无效，请重新输入！");
					break;
			}
		}
	}

	//账号密码验证
	//管理员
	private static void getadminInfoFromUser(Scanner scanner) {
		System.out.println("您选择以【系统管理员】身份登录，请输入您的账号：");
		String Id = scanner.nextLine();
        System.out.println("请输入您的密码: ");
        String password = scanner.nextLine();
        adminInfo af = new adminInfo(Id,"",password);
        admin_infoDAO adminDAO =new admin_infoDAOImpl();
		try {
	     adminInfo isaf=adminDAO.login(af.getAdminId(), af.getPassword());
	     if(isaf==null) {
	    	 System.out.print("账号或密码不正确，登录失败！");
	     }
	     else
	     {
	    	 System.out.println("欢迎！"+isaf.getName());
	    	 System.out.println("您可以1.管理植物基本信息2.管理植物分类信息");
	    	  // 获取用户输入
	         int choice = scanner.nextInt();
	         // 使用 switch-case 处理不同选择
	         switch (choice) {
	             case 1:
	                 System.out.println("1.管理植物基本信息");
	                 // 调用相关逻辑
	                 displayPlantInfoMenu(scanner);
	                 break;
	             case 2:
	                 System.out.println("2.管理植物分类信息");
	                 // 调用相关逻辑
	                 displayPlantSortMenu(scanner);
	                 break;
	             default:
	                 System.out.println("无效选择，请重新运行程序并输入有效数字。");
	                 break;
	         }
	     }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//养护人员
	private static void getupkeepInfoFromUser(Scanner scanner) {
		System.out.println("您选择以【养护人员】身份登录，请输入您的账号：");
		String Id = scanner.nextLine();
        System.out.println("请输入您的密码: ");
        String password = scanner.nextLine();
        UpkeepStaff staff = new UpkeepStaff(Id,"",password);
        UpkeepStaffDAO staff_ =new UpkeepStaffDAOImpl();
		try {
			UpkeepStaff isstaff = staff_.login(staff.getUpkeepSid(), staff.getUpkeepPwd());
		     	if(isstaff==null) {
		    	 	System.out.println("账号或密码不正确，登录失败！");
		     	}
		     	else
		     	{
		    		System.out.println("欢迎！"+isstaff.getUpkeepSname());
		    		System.out.println("您可以管理以下信息");
		    		displayUpkeepInfoMenu(scanner);
		     	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//监测人员
	private static void getstaffInfoFromUser(Scanner scanner) {
		System.out.println("您选择以【监测人员】身份登录，请输入您的账号：");
		String Id = scanner.nextLine();
        System.out.println("请输入您的密码: ");
        String password = scanner.nextLine();
        staff staff = new staff(Id,"",password);
        staffDAO staff_ =new staffDAOImpl();
		try {
			staff isstaff = staff_.login(staff.get_staffID(), staff.get_staffPwd());
		     if(isstaff==null) {
		    	 System.out.println("账号或密码不正确，登录失败！");
		     }
		     else
		     {
		    	 System.out.println("欢迎！"+isstaff.get_staffName());
		    	 System.out.println("您可以管理监测信息");
		    	 displayMonitorInfoMenu(scanner);
		     }
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	//病虫害防治专家
	private static void getexpertInfoFromUser(Scanner scanner) {
		System.out.println("您选择以【病虫害防治专家】身份登录，请输入您的账号：");
		String Id = scanner.nextLine();
        System.out.println("请输入您的密码: ");
        String password = scanner.nextLine();
        expert expert = new expert(Id,"",password);
        expertDAO expert_ =new expertDAOImpl();
		try {
			expert isexpert = expert_.login(expert.get_expertID(), expert.get_expertPwd());
		     if(isexpert==null) {
		    	 System.out.println("账号或密码不正确，登录失败！");
		     }
		     else
		     {
		    	 System.out.println("欢迎！"+isexpert.get_expertName());
		    	 System.out.println("您可以管理病虫害防治信息");
		    	 displayDiseaseInfoMenu(scanner);
		     }
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	//上级主管部门
	private static void getmasterInfoFromUser(Scanner scanner) {
		System.out.println("您选择以【上级主管部门】身份登录，请输入您的账号：");
		String Id = scanner.nextLine();
	        System.out.println("请输入您的密码: ");
	        String password = scanner.nextLine();
	        masterInfo master = new  masterInfo(Id,"",password);
	        master_infoDAO master_ =new master_infoDAOImpl();
			try {
				masterInfo ismaster = master_.login(master.getMasterId(), master.getMaster_password());
			     	if(ismaster==null) {
			    	 	System.out.println("账号或密码不正确，登录失败！");
			     	}
			     	else
			     	{
			    		System.out.println("欢迎！"+ismaster.getMaster_name());
			    		System.out.println("您可以管理以下信息");
			    		displayMasterInfoMenu(scanner);
			     	}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	

	
	
	//角色功能菜单
	//管理员-管理植物信息
	private static void displayPlantInfoMenu(Scanner scanner) throws Exception {
        while (true) {
        	//实例化业务
        	plantinfo_service plantinfo_service =new plantinfo_service();
		plantSort_service plantsort_service =new plantSort_service();
        	//实例化DAO接口
        	plant_infoDAOImpl plantDAO=new plant_infoDAOImpl();
        	image_infoDAOImpl imageDAO =new image_infoDAOImpl();
            // 显示植物信息管理菜单
            System.out.println("植物基本信息管理菜单:");
	        System.out.println("1. 查询所有植物信息");
	        System.out.println("2. 添加植物信息");
	        System.out.println("3. 更新植物信息");
	        System.out.println("4. 删除植物信息");
	        System.out.println("5. 统计平台中每科植物的数量");
	        System.out.println("6. 根据任意属性或属性组合查询所需的园林植物信息");
	        System.out.println("7. 退出该基本管理信息菜单");
            // 获取用户输入
            int choice = scanner.nextInt();
            scanner.nextLine();
            // 使用 switch-case 处理不同选择
            switch (choice) {
                case 1:
                    System.out.println("查询所有植物信息");
                    // 调用查询植物信息的逻辑
				try {
					plantinfo_service.displayQuery(plantDAO.queryAllPf());
				} catch (Exception e) {
					e.printStackTrace();
				}
                    break;
                case 2:
                    System.out.println("添加植物信息");
                    // 调用添加植物信息的逻辑
                    plantinfo_service.displayInsertPlant(plantDAO,imageDAO);
		    System.out.println("请继续添加植物分类信息");
			// 调用添加植物分类信息逻辑
		    plantsort_service.addSortInfo();
                    break;
                case 3:
                    System.out.println("更新植物信息");
                    // 调用更新植物信息的逻辑
                    
    				try {
					System.out.println("有以下植物可以更新，请选择");
					plantinfo_service.displayQuery(plantDAO.queryAllPf());
                    System.out.println("请输入需要更新的植物编号：");
        	        String plantId = scanner.nextLine();
					if(!plantDAO.isPlantIdExists(plantId))
				    {
				    System.out.println("该植物不存在！");
                    break;
                    }else {
                    System.out.println("以下是你选择更新的植物信息 ：");
                    plantinfo_service.displayQuery(plantDAO.queryFromViewById(plantId));
                    PlantInfo plantinfo=plantDAO.getPlantInfoById(plantId);
                    plantinfo_service.updateSelect(scanner, plantDAO, plantinfo);
                    
                    }
				} catch (Exception e) {
					
					e.printStackTrace();
				}
                    break;
                case 4:
                    System.out.println("删除植物信息");
                    // 调用删除植物信息的逻辑

					 System.out.println("有以下植物可以删除，请选择");
				try {
					plantinfo_service.displayQuery(plantDAO.queryAllPf());
				    System.out.println("请输入需要删除的植物编号：");
        	        String deleteId = scanner.nextLine();
        	        if(!plantDAO.isPlantIdExists(deleteId))
        	        {
        	        	System.out.println("该植物不存在！");
        	        	break;
               }else {
		   System.out.println("请先删除该植物分类信息！");
            	   plantsort_service.deleteSortInfo();
            	   plantinfo_service.displayDeletePlant(plantDAO, imageDAO, deleteId);
               }
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
	                

                    break;
                  case 5:
                  System.out.println("统计平台中每科植物的数量");
                  // 调用统计植物信息的逻辑
				try {
					plantDAO.countPlantsByScientificName();
				} catch (Exception e) {
				
					e.printStackTrace();
				}
                  break;
              case 6:
                  System.out.println("根据任意属性或属性组合查询所需的园林植物信息");
                  // 调用根据任意属性或属性组合查询所需的园林植物信息的逻辑
                  // 提示用户输入查询条件
                  System.out.println("请输入查询条件（属性及值），多个条件之间用逗号分隔，例如：植物种名=野迎春,植物编号=0001");
                  String userInput = scanner.nextLine();

                  // 解析用户输入，构建属性及其值的映射
                  Map<String, Object> properties =  plantinfo_service.parseUserInput(userInput);

                  // 调用查询逻辑
				  try {

					  if(plantDAO.queryFromViewByProperties(properties).size()==0) System.out.println("未查询到符合条件的该植物信息！");
					  else plantinfo_service.displayQuery(plantDAO.queryFromViewByProperties(properties));

				  } catch (Exception e) {
					  e.printStackTrace();
				  }
                  break;
              case 7:
                  System.out.println("退出该基本管理信息菜单");
                  // 退出菜单循环
                  scanner.close();
                  System.exit(0);
                default:
                    System.out.println("无效选择，请重新输入");
                    break;
            }
        }
    }      
    //管理员-管理植物分类信息
	private static void displayPlantSortMenu(Scanner scanner) throws Exception {
		while (true) {
			//实例化业务
			plantSort_service plantsort_service =new plantSort_service();
			//实例化DAO接口

			// 显示植物信息管理菜单
			System.out.println("植物分类信息管理菜单:");
			System.out.println("1. 添加植物分类信息");
			System.out.println("2. 删除植物分类信息");
			System.out.println("3. 修改植物分类信息");
			System.out.println("4. 查询植物分类信息");
			System.out.println("5. 退出系统");

			// 获取用户输入
			//int choice = scanner.nextInt();
			int choice = 0;
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
				// 处理整数
			} else {
				// 处理没有整数可用的情况
				System.out.println("请输入有效的整数。");
			}
			// 使用 switch-case 处理不同选择
			switch (choice) {
				case 1:
					System.out.println("添加植物分类信息");
					// 调用添加植物分类信息逻辑
					plantsort_service.addSortInfo();
					break;
				case 2:
					System.out.println("删除植物分类信息");
					// 调用删除植物分类信息逻辑
					plantsort_service.deleteSortInfo();
					break;
				case 3:
					System.out.println("修改植物分类信息");
					// 调用修改植物分类信息逻辑
					plantsort_service.updateSortInfo();
					break;
				case 4:
					System.out.println("查询植物分类信息");
					//调用查询植物分类信息逻辑
					plantsort_service.searchSortInfo();
					break;
				case 5:
					System.out.println("退出植物分类管理菜单");
					// 退出菜单循环
					scanner.close();
					System.exit(0);
				default:
					System.out.println("无效选择，请重新输入");
					break;
			}
		}
	}
	//监测人员
	private static void displayMonitorInfoMenu(Scanner scanner) throws Exception {
		boolean flag = true;
        while (flag) {
            System.out.println("植物监测信息管理菜单:");
            System.out.println("1. 查看监测记录");//OK
            System.out.println("2. 添加监测记录");//OK
            System.out.println("3. 修改监测记录");//OK
            System.out.println("4. 删除监测记录");//OK
            System.out.println("5. 查询监测记录");//OK
            System.out.println("6. 查看异常指标");//OK
            System.out.println("7. 指标分析");//OK
            System.out.println("8. 退出");//OK
            int choice = scanner.nextInt();
            scanner.nextLine();
            String ID;
            switch (choice) {
                case 1:
                    System.out.println("查看监测记录");
                    monitor.list();
                    break;
                case 2:
                    System.out.println("添加监测记录");
                    monitor.toAdd(scanner);
                    break;
                case 3:
                    System.out.println("修改监测记录");
                    monitor.toUpdate(scanner);
                    break;
                case 4:
                    System.out.println("删除监测记录");
                    System.out.println("请输入监测记录编号：");
                    ID = scanner.nextLine();
                    if(monitor.delete(ID))
                    	System.out.println("删除成功！");
                    break;
                case 5:
                    System.out.println("查询监测记录");
                    System.out.println("请输入已知的属性值：");
                    ID = scanner.nextLine();
                    monitor.search(ID);
                    break;
                case 6:
                    System.out.println("查看异常指标");
                    monitor.showError();
                    break;
                case 7:
                    System.out.println("指标分析");
                    monitor.analysis();
                    break;
                case 8:
                	System.out.println("退出");
                	flag = false;
                	break;
                default:
                    System.out.println("无效选择，请重新输入");
                    break;
            }
        }
	}
	//专家
	private static void displayDiseaseInfoMenu(Scanner scanner) throws Exception {
		boolean flag = true;
        while (flag) {
            System.out.println("植物病虫害防治信息管理菜单:");
            System.out.println("1. 查看病虫害及防治措施信息");//OK
            System.out.println("2. 添加病虫害及防治措施信息");//OK
            System.out.println("3. 修改病虫害及防治措施信息");//OK
            System.out.println("4. 删除病虫害及防治措施信息");//OK
            System.out.println("5. 查询病虫害及防治措施信息");//OK
            System.out.println("6. 查看患病植物养护状态");//OK
            System.out.println("7. 退出");//OK
            int choice = scanner.nextInt();
            scanner.nextLine();
            String ID;
            switch (choice) {
                case 1:
                    System.out.println("查看病虫害及防治措施信息");
                    disease.list();
                    break;
                case 2:
                    System.out.println("添加病虫害及防治措施信息");
                    disease.toAdd(scanner);
                    break;
                case 3:
                    System.out.println("修改病虫害及防治措施信息");
                    disease.toUpdate(scanner);
                    break;
                case 4:
                    System.out.println("删除病虫害及防治措施信息");
                    System.out.println("请输入病虫害编号：");
                    ID = scanner.nextLine();
                    if(disease.delete(ID))
                    	System.out.println("删除成功！");
                    break;
                case 5:
                    System.out.println("查询病虫害及防治措施信息");
                    System.out.println("请输入已知的属性值：");
                    ID = scanner.nextLine();
                    disease.search(ID);
                    break;
                case 6:
                    System.out.println("查看患病植物养护状态");
                    disease.listUpkeep();
                    break;
                case 7:
                    System.out.println("退出");
                	flag = false;
                    break;
                default:
                    System.out.println("无效选择，请重新输入");
                    break;
            }
        }
	}
	//上级主管部门
	private static void displayMasterInfoMenu(Scanner scanner) throws Exception {
		boolean flag = true;
        while (flag) {
            System.out.println("上级主管部门管理菜单:");
            System.out.println("1. 管理养护人员");
            System.out.println("2. 管理养护任务");
            System.out.println("3. 管理监测人员");
            System.out.println("4. 管理监测设备");
            System.out.println("5. 管理病虫害防治专家");
            System.out.println("6. 联合查询（植物、分类、养护信息）");//OK
            System.out.println("7. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("管理养护人员");
                    upkeepStaff_service upkeepstaffservice = new upkeepStaff_service();
		    upkeepstaffservice.manageUStaff();
                    break;
                case 2:
                    System.out.println("管理养护任务");
                    upkeepTask_service upkeeptaskservice = new upkeepTask_service();
                    upkeeptaskservice.upkeeptask_service_menu();
                    break;
                case 3:
                    System.out.println("管理监测人员");
                    monitor.manageStaff(scanner);
                    break;
                case 4:
                    System.out.println("管理监测设备");
                    monitor.manageDevice(scanner);
                    break;
                case 5:
                    System.out.println("管理病虫害防治专家");
                    disease.manageExpert(scanner);
                    break;
                case 6:
                    System.out.println("联合查询（植物、分类、养护信息）");
                       //实例化业务
                	plantinfo_service plantinfo_service =new plantinfo_service();
                	//实例化DAO接口
                	plant_infoDAOImpl plantDAO=new plant_infoDAOImpl();
				    plantinfo_service.displayQueryUnion(plantDAO.queryFromUnionView());
                    break;
                case 7:
                    System.out.println("退出");
                	flag = false;
                    break;
                default:
                    System.out.println("无效选择，请重新输入");
                    break;
            }
        }
	}
	//养护人员
	private static void displayUpkeepInfoMenu(Scanner scanner)throws Exception {
		upkeep_service upkeep_service = new upkeep_service();
		upkeep_service.upkeepMenu();
	}
	//主函数
	public static void main(String[] args) throws Exception {
		login_service l = new login_service();
		l.roleChoose();
	 }
}
