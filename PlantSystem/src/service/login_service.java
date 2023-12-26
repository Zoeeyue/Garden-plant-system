package service;

import java.util.Scanner;
import DAO.admin_infoDAO;
import DAO.Impl.admin_infoDAOImpl;
import bean.adminInfo;
import bean.staff;
import DAO.staffDAO;
import DAO.Impl.plant_infoDAOImpl;
import DAO.Impl.staffDAOImpl;

public class login_service {
	//选择角色
	public void roleChoose(Scanner scanner) {
		System.out.print("请选择登录角色：1.系统管理员 2.主管部门 3.养护人员 4.监测人员");
		int roleID = scanner.nextInt();
		switch (roleID) {
			case 1:
				adminLogin(getadminInfoFromUser(scanner),scanner);
				break;
			case 2:
				break;
			case 3:
//				upkeepLogin(getupkeepInfoFromUser(scanner),scanner);
				break;
			case 4:
				monitorLogin(getstaffInfoFromUser(scanner),scanner);
				break;
			default:
				System.out.print("请输入正确序号！");
				break;
		}
	}

	//封装用户输入的账号密码
	private static adminInfo getadminInfoFromUser(Scanner scanner) {
		System.out.println("您选择以【系统管理员】身份登录，请输入您的账号：");
		String Id = scanner.nextLine();
        System.out.print("请输入您的密码: ");
        String password = scanner.nextLine();
        adminInfo admin = new adminInfo(Id,"",password);
        return admin;
	}
	private static staff getstaffInfoFromUser(Scanner scanner) {
		System.out.println("您选择以【监测人员】身份登录，请输入您的账号：");
		String Id = scanner.nextLine();
        System.out.print("请输入您的密码: ");
        String password = scanner.nextLine();
        staff staff = new staff(Id,"",password);
        return staff;
	}
//	private static UpkeepStaff getupkeepInfoFromUser(Scanner scanner) {
//		System.out.println("您选择以【养护人员】身份登录，请输入您的账号：");
//		String Id = scanner.nextLine();
//        System.out.print("请输入您的密码: ");
//        String password = scanner.nextLine();
//        UpkeepStaff staff = new UpkeepStaff(Id,"",password);
//        return staff;
//	}
	
	//账号密码验证
	private void monitorLogin(staff role,Scanner scanner) {
		 staffDAO staff =new staffDAOImpl();
		 try {
		     staff isstaff = staff.login(role.get_staffID(), role.get_staffPwd());
		     if(isstaff==null) {
		    	 System.out.print("账号或密码不正确，登录失败！");
		     }
		     else
		     {
		    	 System.out.println("欢迎！"+isstaff.get_staffName());
		    	 System.out.println("您可以管理监测信息");
		    	 displayMonitorInfoMenu();
		     }
			} catch (Exception e) {
				e.printStackTrace();
			}
 
	}
	private void adminLogin(adminInfo af,Scanner scanner) {
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
	                 displayPlantInfoMenu();
	                 break;
	             case 2:
	                 System.out.println("2.管理植物分类信息");
	                 // 调用相关逻辑
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
//	private void upkeepLogin(UpkeepStaff role,Scanner scanner) {
//		
//	}
	
	//角色功能菜单
	private static void displayPlantInfoMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 显示植物信息管理菜单
            System.out.println("植物基本信息管理菜单:");
            System.out.println("1. 查询植物信息");
            System.out.println("2. 添加植物信息");
            System.out.println("3. 更新植物信息");
            System.out.println("4. 删除植物信息");
            System.out.println("5. 统计平台中每科植物的数量");
            System.out.println("6. 根据任意属性或属性组合查询所需的园林植物");
            System.out.println("7. 退出");

            // 获取用户输入
            int choice = scanner.nextInt();

            // 使用 switch-case 处理不同选择
            switch (choice) {
                case 1:
                    System.out.println("查询植物信息");
                    // 调用查询植物信息的逻辑
                    plant_infoDAOImpl plantDao=new plant_infoDAOImpl();
				try {
					plantDao.queryAllPf();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    break;
                case 2:
                    System.out.println("添加植物信息");
                    // 调用添加植物信息的逻辑
                    break;
                case 3:
                    System.out.println("更新植物信息");
                    // 调用更新植物信息的逻辑
                    break;
                case 4:
                    System.out.println("删除植物信息");
                    // 调用删除植物信息的逻辑
                    break;
                case 5:
                    System.out.println("删除植物信息");
                    // 调用删除植物信息的逻辑
                    break;
                case 6:
                    System.out.println("退出");
                    // 退出菜单循环
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("无效选择，请重新输入");
                    break;
            }
        }
    }
	
	private static void displayMonitorInfoMenu() {
		Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("植物监测信息管理菜单:");
            System.out.println("1. 查看监测记录");
            System.out.println("2. 添加监测记录");
            System.out.println("3. 修改监测记录");
            System.out.println("4. 删除监测记录");
            System.out.println("5. 查询监测记录");
            System.out.println("6. 查看异常指标");
            System.out.println("7. 指标分析");
            System.out.println("8. 退出");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("查看监测记录");
                    break;
                case 2:
                    System.out.println("添加监测记录");
                    break;
                case 3:
                    System.out.println("修改监测记录");
                    break;
                case 4:
                    System.out.println("删除监测记录");
                    break;
                case 5:
                    System.out.println("查询监测记录");
                    break;
                case 6:
                    System.out.println("查看异常指标");
                case 7:
                    System.out.println("指标分析");
                    break;
                case 8:
                    System.out.println("退出");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效选择，请重新输入");
                    break;
            }
        }
	}
	
	
	//主函数
	public static void main(String[] args) {
		login_service l = new login_service();
		while(true) {
			Scanner scanner = new Scanner(System.in);
			l.roleChoose(scanner);
			scanner.close();
		}
	 }
}
