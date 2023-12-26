package service;

import DAO.SortDAO;
import DAO.Impl.SortDAOImpl;
import bean.PlantSortView;
import bean.Sort;

import java.util.List;
import java.util.Scanner;

public class SortDeleteTest {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        boolean isValidPlantId = false;
        String plantId = "";

        while (!isValidPlantId) {
            //从用户输入中获取植物ID
            System.out.print("请输入PlantId: ");
            plantId = scanner.nextLine();

            SortDAO sortDAO = new SortDAOImpl();
            List<Sort> sorts = sortDAO.getSortsByProperty("plant_id", plantId);
            // 检查是否有PlantId
            if (sorts.isEmpty()) {
                System.out.println("不存在这个PlantId，请重新输入:(");
            } else {
                isValidPlantId = true;
            }
        }


        // 步骤3：显示植物分类信息
        displaySortInformation(plantId);

        // 步骤4：确认删除
        System.out.print("确认要删除该植物分类信息吗？删除后无法恢复。");
        System.out.print("确认删除请输入 确认，返回请输入 返回");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("确认")) {
            //删除植物分类信息
            SortDAO sortDAO = new SortDAOImpl();

            List<Sort> sorts = sortDAO.getSortsByProperty("plant_id", plantId);
            String sortid = sorts.get(0).getSortId();
            sortDAO.deleteSort(sortid);
            System.out.println("植物分类信息已删除。");

        } else if (confirmation.equalsIgnoreCase("返回")) {
            System.out.println("返回植物分类菜单。");
            return;//返回菜单
        } else {
            System.out.println("无效的操作。");
        }
    }

    private static void displaySortInformation(String plantId) throws Exception {
        // 显示植物分类信息
        System.out.println("该植物的分类信息如下：");
        SortDAO sortDAO = new SortDAOImpl();
        PlantSortView psv = sortDAO.getPlantSortViewById(plantId);
        psv.displayProperties();
    }

}

