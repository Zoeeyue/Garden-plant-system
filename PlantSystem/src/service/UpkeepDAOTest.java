package service;

import DAO.UpkeepDAO;
import DAO.Impl.UpkeepDAOImpl;
import bean.Upkeep;

import java.util.Date;
import java.util.List;

public class UpkeepDAOTest {
    public static void main(String[] args) {
        UpkeepDAO dao = new UpkeepDAOImpl();
        Upkeep upkeep = new Upkeep("1", "测试任务", new Date(), "测试地点", "123", "测试描述", "0001", "测试进程");

        try {
            // 查询所有养护信息
            List<Upkeep> allUpkeeps = dao.getAllUpkeeps();
            for (Upkeep u : allUpkeeps) {
                System.out.println(u.getUpkeepName());
            }

            // 更新养护信息
            dao.updateUpkeep(upkeep);

            // 根据任务编号查询养护信息
            Upkeep retrievedUpkeep = dao.getUpkeepById("1");
            if (retrievedUpkeep != null) {
                System.out.println("Retrieved Upkeep: " + retrievedUpkeep.getUpkeepName());
            } else {
                System.out.println("No Upkeep found with ID 1");
            }

            // 插入新的养护信息
            Upkeep newUpkeep = new Upkeep("2", "新测试任务", new Date(), "新测试地点", "0001", "新测试描述", "0001", "新测试进程");
            dao.addUpkeep(newUpkeep);
            System.out.println("插入养护信息成功");

            // 再次查询所有养护信息
            List<Upkeep> updatedUpkeeps = dao.getAllUpkeeps();
            for (Upkeep u : updatedUpkeeps) {
                System.out.println(u.getUpkeepName());
            }

            // 删除养护信息
            dao.deleteUpkeep("2");

            // 查询删除后的所有养护信息
            List<Upkeep> afterDeleteUpkeeps = dao.getAllUpkeeps();
            System.out.println("After deletion:");
            for (Upkeep u : afterDeleteUpkeeps) {
                System.out.println(u.getUpkeepName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}