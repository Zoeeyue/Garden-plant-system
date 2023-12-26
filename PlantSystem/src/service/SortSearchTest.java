package service;

import DAO.FamilyDAO;
import DAO.GenusDAO;
import DAO.Impl.FamilyDAOImpl;
import DAO.Impl.GenusDAOImpl;
import DAO.Impl.SortDAOImpl;
import DAO.SortDAO;
import bean.Family;
import bean.Genus;
import bean.PlantSortView;
import bean.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortSearchTest {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 显示查询菜单
            System.out.println("查找功能选择");
            System.out.println("1----根据植物编号查找");
            System.out.println("2----根据生长环境模糊查找");
            System.out.println("3----查询下属植物信息");
            System.out.println("0----返回上级菜单");

            // 获取用户输入
            System.out.print("请输入选项：");
            int option = scanner.nextInt();

            // 根据用户输入执行相应功能
            switch (option) {
                case 1:
                    // 执行根据植物编号查找分类信息功能
                    System.out.println("根据植物编号查找：");

                    boolean isValidPlantId = false;
                    String plantId = "";

                    while (!isValidPlantId) {
                        //从用户输入中获取植物ID
                        System.out.print("请输入PlantId: ");
                        scanner.nextLine();
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
                    // 显示植物分类信息
                    displaySortInformation(plantId);
                    break;
                case 2:
                    // 执行根据生长环境查询植物功能
                    System.out.println("执行根据生长环境查询植物功能");
                    System.out.print("请输入生长环境信息: ");
                    scanner.nextLine();
                    String Env = scanner.nextLine();
                    SortDAO sortDAO = new SortDAOImpl();
                    List<Sort> sorts = new ArrayList<>();
                    sorts = sortDAO.searchSortsByGrowEnv(Env);
                    System.out.println("植物列表如下：");
                    for (Sort sort : sorts) {
                        PlantSortView psv = new PlantSortView();
                        psv =sortDAO.getPlantSortViewById(sort.getPlantId());
                        psv.displayProperties2();
                    }
                    break;
                case 3:
                    // 执行查询下属植物信息功能
                    System.out.println("请输入需要查询的科：");
                    String familyName = scanner.next();
                    Family family = new Family();
                    FamilyDAO familyDAO = new FamilyDAOImpl();
                    family = familyDAO.getFamiliesByProperty("FamilyName",familyName).get(0);
                    System.out.println(familyName+"科：");

                    // 步骤2：查询该科下的所有属
                    GenusDAO genusDAO = new GenusDAOImpl();
                    List<Genus> genera = genusDAO.getGeneraByProperty("FamilyId", family.getFamilyId());

                    // 步骤3：对每个属，查询其下的所有植物
                    SortDAO sortG = new SortDAOImpl();
                    for (Genus genus : genera) {
                        // 查询该属下的所有植物
                        System.out.println(genus.getGenusName() + "属：");
                        sorts = sortG.getSortsByProperty("GenusId", genus.getGenusId());

                        for (Sort sort : sorts) {
                            PlantSortView psv = new PlantSortView();
                            SortDAO sortS = new SortDAOImpl();
                            psv = sortS.getPlantSortViewById(sort.getPlantId());
                            String plantName = psv.getPlantName();
                            System.out.print(plantName+" ");
                        }
                        System.out.println();
                    }
                    break;
                case 0:
                    // 菜单
                    System.out.println("返回上级菜单");
                    return;
                default:
                    // 无效选项
                    System.out.println("无效选项，请重新输入");
            }

            // 清空输入缓冲区
            scanner.nextLine();
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
