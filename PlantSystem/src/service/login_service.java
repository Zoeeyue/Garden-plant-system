import java.util.Scanner;
import DAO.admin_infoDAO;
import DAO.Impl.admin_infoDAOImpl;
import bean.adminInfo;

import DAO.plant_infoDAO;
import DAO.image_infoDAO;
import bean.ImageInfo;
import bean.PlantInfo;
import DAO.Impl.plant_infoDAOImpl;
import DAO.Impl.image_infoDAOImpl;
import bean.PlantInfo;

public class login_service {
	 public static void main(String[] args) {
			
	    	Scanner scanner = new Scanner(System.in);
	    	
	    	adminInfo af = getadminInfoFromUser(scanner);
	     
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

		         // 关闭 Scanner
		         scanner.close();
		    	 
		     }
			} catch (Exception e) {
				e.printStackTrace();
			}
 
		 
	 }

	private static adminInfo getadminInfoFromUser(Scanner scanner) {
		System.out.println("您选择以系统管理员身份登录，请输入您的账号：");
		String adminId = scanner.nextLine();
        System.out.print("请输入您的密码: ");
        String admin_password = scanner.nextLine();

        adminInfo af = new adminInfo();
        af.setAdminId(adminId);
        af.setPassword(admin_password);

        return af;
	}
	
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
}
