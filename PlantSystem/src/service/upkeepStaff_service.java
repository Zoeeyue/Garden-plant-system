package service;

import DAO.Impl.UpkeepStaffDAOImpl;
import DAO.UpkeepStaffDAO;
import bean.UpkeepStaff;

import java.util.List;
import java.util.Scanner;

public class upkeepStaff_service {

    public void manageUStaff() throws Exception {
        Scanner scanner = new Scanner(System.in);
        UpkeepStaffDAO staff_di = new UpkeepStaffDAOImpl() ;
        System.out.println("请选择操作：");
        System.out.println("1.查看养护人员");
        System.out.println("2.增加养护人员");
        System.out.println("3.删除养护人员");
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
                System.out.println("--查看养护人员");
                List<UpkeepStaff> result = staff_di.listStaff();
                for(UpkeepStaff staff : result) {
                    UpkeepStaff m = new UpkeepStaff(staff.getUpkeepSid(),staff.getUpkeepSname(),staff.getUpkeepPwd());
                    System.out.println("养护人员编号: " + m.getUpkeepSid());
                    System.out.println("养护人员名称: " + m.getUpkeepSname());
                    System.out.println("养护人员密码: " + m.getUpkeepPwd());
                    System.out.println("——————————————————————————————————————");
                }
                break;
            case 2:
                System.out.println("--增加监测人员");
                System.out.println("----请输入人员编号：");
                ID = scanner.nextLine();
                if(!staff_di.existID(ID)) {
                    System.out.println("该监测人员已存在！");
                    return;
                }
                System.out.println("----请输入人员名称：");
                String name = scanner.nextLine();
                System.out.println("----请输入人员密码：");
                String pwd = scanner.nextLine();
                UpkeepStaff s = new UpkeepStaff(ID,name,pwd);
                staff_di.addUpkeepStaff(s);
                break;
            case 3:
                System.out.println("--删除监测人员");
                System.out.println("----请输入监测人员编号：");
                ID = scanner.nextLine();
                if(staff_di.existID(ID)) {
                    System.out.println("该监测人员不存在！");
                    return;
                }
                staff_di.deleteUpkeepStaff(ID);
                break;
        }
    }

}
