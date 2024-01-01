

import DAO.*;
import DAO.Impl.*;
import bean.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class plantSort_service {

    public void addSortInfo() throws Exception {
        Scanner scanner = new Scanner(System.in);

        boolean isValidPlantId = false;
        String plantId = "";

        while (!isValidPlantId) {
            // 用户输入PlantId
            System.out.print("请输入PlantId: ");
            plantId = scanner.nextLine();
            // 检查是否有重复的PlantId
            if (isDuplicatePlantId(plantId)) {
                System.out.println("分类表中已存在相同的PlantId，请重新输入:(");
            } else {
                isValidPlantId = true;
            }
        }

        // 用户输入正确的PlantId后，继续处理其他逻辑
        System.out.println("PlantId 可用，请继续输入分类信息;)");

        System.out.print("请输入科名: ");
        String familyName = scanner.nextLine();

        System.out.print("请输入属名: ");
        String genusName = scanner.nextLine();

        System.out.print("请输入省名: ");
        String provinceName = scanner.nextLine();

        System.out.print("请输入市名: ");
        String cityName = scanner.nextLine();

        System.out.print("请输入县名: ");
        String countyName = scanner.nextLine();

        System.out.print("请输入生长环境: ");
        String growEnv = scanner.nextLine();

        System.out.print("请输入别名: ");
        String alias = scanner.nextLine();

        // 关闭 Scanner
        //scanner.close();

        // 获取或新建科信息
        Family family = getOrCreateFamily(familyName);

        // 获取或新建属信息
        Genus genus = getOrCreateGenus(genusName, family.getFamilyId());

        // 获取或新建省信息
        Province province = getOrCreateProvince(provinceName);

        // 获取或新建市信息
        City city = getOrCreateCity(cityName, province.getProvinceId());

        // 获取或新建县信息
        County county = getOrCreateCounty(countyName, city.getCityId());

        // 设置分类信息
        Sort sort = new Sort();
        sort.setPlantId(plantId);
        sort.setGenusId(genus.getGenusId());
        sort.setCountyId(county.getCountyId());
        sort.setAlias(alias);
        sort.setGrowEnv(growEnv);
        sort.setSortId("暂定");
        // 设置其他分类信息的属性...

        // 添加分类信息
        addSort(sort);
        System.out.println("已成功添加分类信息。");

    }
    private static Family getOrCreateFamily(String familyName) throws Exception {
        FamilyDAO familyDAO = new FamilyDAOImpl();
        List<Family> families = familyDAO.getFamiliesByProperty("FamilyName", familyName);

        if (!families.isEmpty()) {
            // 如果已存在，返回第一个匹配的科
            System.out.println("科名已存在，连接成功。");
            return families.get(0);
        } else {
            // 如果不存在，新建科并返回
            Family newFamily = new Family();
            newFamily.setFamilyName(familyName);
            // 设置其他科信息的属性...
            familyDAO.addFamily(newFamily);
            System.out.println("科名不存在，创建成功。");
            return familyDAO.getFamiliesByProperty("FamilyName",familyName).get(0);
        }
    }

    private static Genus getOrCreateGenus(String genusName, String familyId) throws Exception {
        GenusDAO genusDAO = new GenusDAOImpl();
        List<Genus> genera = genusDAO.getGeneraByProperty("GenusName", genusName);

        if (!genera.isEmpty()) {
            // 如果已存在，返回第一个匹配的属
            System.out.println("属名已存在，连接成功。");
            return genera.get(0);
        } else {
            // 如果不存在，新建属并返回
            Genus newGenus = new Genus();
            newGenus.setGenusName(genusName);
            newGenus.setFamilyId(familyId);
            // 设置其他属信息的属性...
            genusDAO.addGenus(newGenus);
            System.out.println("属名不存在，创建成功。");
            return genusDAO.getGeneraByProperty("GenusName",genusName).get(0);
        }
    }

    private static Province getOrCreateProvince(String provinceName) throws Exception {
        ProvinceDAO provinceDAO = new ProvinceDAOImpl();
        List<Province> provinces = provinceDAO.getProvincesByProperty("ProvinceName", provinceName);

        if (!provinces.isEmpty()) {
            // 如果已存在，返回第一个匹配的省
            System.out.println("省名已存在，连接成功。");
            return provinces.get(0);
        } else {
            // 如果不存在，新建省并返回
            Province newProvince = new Province();
            newProvince.setProvinceName(provinceName);
            // 设置其他省信息的属性...
            provinceDAO.addProvince(newProvince);
            System.out.println("省名不存在，创建成功。");
            return provinceDAO.getProvincesByProperty("ProvinceName",provinceName).get(0);
        }
    }

    private static City getOrCreateCity(String cityName, String provinceId) throws Exception {
        CityDAO cityDAO = new CityDAOImpl();
        List<City> cities = cityDAO.getCitiesByProperty("CityName", cityName);

        if (!cities.isEmpty()) {
            // 如果已存在，返回第一个匹配的市
            System.out.println("市名已存在，连接成功。");
            return cities.get(0);
        } else {
            // 如果不存在，新建市并返回
            City newCity = new City();
            newCity.setCityName(cityName);
            newCity.setProvinceId(provinceId);  // 设置所属省属性
            // 设置其他市信息的属性...
            cityDAO.addCity(newCity);
            System.out.println("市名不存在，连接成功。");
            return cityDAO.getCitiesByProperty("CityName", cityName).get(0);
        }
    }

    private static County getOrCreateCounty(String countyName, String cityId) throws Exception {
        CountyDAO countyDAO = new CountyDAOImpl();
        List<County> counties = countyDAO.getCountiesByProperty("CountyName", countyName);

        if (!counties.isEmpty()) {
            // 如果已存在，返回第一个匹配的县
            System.out.println("县名已存在，连接成功。");
            return counties.get(0);
        } else {
            // 如果不存在，新建县并返回
            County newCounty = new County();
            newCounty.setCountyName(countyName);
            newCounty.setCityId(cityId);
            // 设置其他县信息的属性...
            countyDAO.addCounty(newCounty);
            System.out.println("县名不存在，创建成功。");
            return countyDAO.getCountiesByProperty("CountyName",countyName).get(0);
        }
    }

    private static void addSort(Sort sort) throws Exception {
        SortDAO sortDAO = new SortDAOImpl();
        sortDAO.addSort(sort);
    }

    private static boolean isDuplicatePlantId(String plantId) throws Exception {
        SortDAO sortDAO = new SortDAOImpl();
        List<Sort> sorts = sortDAO.getSortsByProperty("plant_id", plantId);

        // 如果存在相同的PlantId，则返回 true，表示有重复
        return !sorts.isEmpty();
    }

    public void deleteSortInfo() throws Exception {
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

    public void searchSortInfo() throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 显示查询菜单
            System.out.println("查找功能选择");
            System.out.println("1----根据植物编号查找分类信息");
            System.out.println("2----根据植物编号查找地域信息");
            System.out.println("3----根据生长环境模糊查找");
            System.out.println("4----查询下属植物信息");
            System.out.println("0----返回上级菜单");

            // 获取用户输入
            System.out.print("请输入选项：");
            int option = scanner.nextInt();
            scanner.nextLine();

            // 根据用户输入执行相应功能
            switch (option) {
                case 1:
                    // 执行根据植物编号查找分类信息功能
                    System.out.println("根据植物编号查找分类信息：");

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
                    // 显示植物分类信息
                    displaySortInformation(plantId);
                    break;
                case 2:
                    // 执行根据植物编号查找分类信息功能
                    System.out.println("根据植物编号查找地域信息：");

                    boolean isValidPlantIdr = false;
                    String plantIdr = "";

                    while (!isValidPlantIdr) {
                        //从用户输入中获取植物ID
                        System.out.print("请输入PlantId: ");
                        plantIdr = scanner.nextLine();

                        SortDAO sortDAO = new SortDAOImpl();
                        List<Sort> sorts = sortDAO.getSortsByProperty("plant_id", plantIdr);
                        // 检查是否有PlantId
                        if (sorts.isEmpty()) {
                            System.out.println("不存在这个PlantId，请重新输入:(");
                        } else {
                            isValidPlantIdr = true;
                        }
                    }
                    // 显示植物分类信息
                    displayRegionInformation(plantIdr);
                    break;
                case 3:
                    // 执行根据生长环境查询植物功能
                    System.out.println("执行根据生长环境查询植物功能");
                    System.out.print("请输入生长环境信息: ");
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
                case 4:
                    // 执行查询下属植物信息功能
                    System.out.println("请输入需要查询的科：");
                    String familyName = scanner.nextLine();
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

    private static void displayRegionInformation(String plantId) throws Exception {
        // 显示植物分类信息
        System.out.println("该植物的地域信息如下：");
        SortDAO sortDAO = new SortDAOImpl();
        PlantRegionView plantRegionView = sortDAO.getPlantRegionById(plantId);
        plantRegionView.displayProperties();
    }


    public void updateSortInfo() throws Exception {
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
        // 显示植物分类信息
        displaySortInformation(plantId);

        while (true) {
            // 显示菜单
            System.out.println("修改功能选择");
            System.out.println("1----生长环境");
            System.out.println("2----植物别名");
            System.out.println("3----植物科属信息（请输入科、属）");
            System.out.println("4----植物地区信息（请输入省、市、县）");
            System.out.println("0----退出");

            // 获取用户输入
            System.out.print("请输入选项：");
            int option = scanner.nextInt();
            scanner.nextLine();

            // 根据用户输入执行相应功能
            switch (option) {
                case 1:
                    // 执行生长环境功能
                    Sort sort = new Sort();
                    sort = getSortBefore(plantId);
                    System.out.println("请输入需要修改的生长环境：");
                    String Env = scanner.nextLine();
                    SortDAO sortDAO = new SortDAOImpl();
                    sort.setGrowEnv(Env);
                    sortDAO.updateSort(sort);
                    System.out.println("已修改生长环境，生长环境："+Env);
                    break;
                case 2:
                    // 执行植物别名功能
                    sort = getSortBefore(plantId);
                    System.out.println("请输入需要修改的别名：");
                    String alias = scanner.nextLine();
                    SortDAO sortAlias = new SortDAOImpl();
                    sort.setAlias(alias);
                    sortAlias.updateSort(sort);
                    System.out.println("已修改别名，别名："+alias);
                    break;
                case 3:
                    // 执行植物科属信息功能
                    System.out.println("请输入科：");
                    String familyName = scanner.nextLine();
                    System.out.println("请输入属：");
                    String genusName = scanner.nextLine();
                    // 获取或新建科信息
                    Family family = getOrCreateFamily(familyName);
                    // 获取或新建属信息
                    Genus genus = getOrCreateGenus(genusName, family.getFamilyId());
                    //
                    sort = getSortBefore(plantId);
                    SortDAO sortFG = new SortDAOImpl();
                    sort.setGenusId(genus.getGenusId());
                    sortFG.updateSort(sort);
                    System.out.println("已修改植物科属信息功能，科：" + familyName + "，属：" + genusName);
                    break;
                case 4:
                    // 执行植物地区信息功能
                    System.out.println("请输入省：");
                    String provinceName = scanner.nextLine();
                    System.out.println("请输入市：");
                    String cityName = scanner.nextLine();
                    System.out.println("请输入县：");
                    String countyName = scanner.nextLine();
                    // 获取或新建省信息
                    Province province = getOrCreateProvince(provinceName);
                    // 获取或新建市信息
                    City city = getOrCreateCity(cityName, province.getProvinceId());
                    // 获取或新建县信息
                    County county = getOrCreateCounty(countyName, city.getCityId());
                    // 执行相应操作
                    sort = getSortBefore(plantId);
                    SortDAO sortPCC = new SortDAOImpl();
                    sort.setCountyId(county.getCountyId());
                    sortPCC.updateSort(sort);
                    System.out.println("执行植物地区信息功能，省：" + provinceName + "，市：" + cityName + "，县：" + countyName);
                    break;
                case 0:
                    // 退出程序
                    System.out.println("退出程序");
                    return;
                default:
                    // 无效选项
                    System.out.println("无效选项，请重新输入");
            }

            // 清空输入缓冲区
            scanner.nextLine();
        }
    }

    private static Sort getSortBefore(String plantId) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String Env = scanner.nextLine();
        SortDAO sortDAO = new SortDAOImpl();
        Sort sort = new Sort();
        sort = sortDAO.getSortsByProperty("plant_id",plantId).get(0);
        return sort;
    }


}
