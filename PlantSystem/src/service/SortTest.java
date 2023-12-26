package service;

import DAO.*;
import DAO.Impl.*;
import bean.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SortTest {

    public static void main(String[] args) throws Exception {
        /*
        插入植物分类信息
         */

        // 创建植物信息
        Date time1= new Date(2023-1-1);
        PlantInfo plantInfo = new PlantInfo("0001", "meigui", "bing",
                "tezheng", "jishu", "jiazhi",
                "image", "xzh", time1, time1);
        // 设置植物信息的属性...
//        String familyName = "Rosac";  // 假设科名为 Rosaceae
//        String genusName = "Ro";       // 假设属名为 Rosa
//        String provinceName = "浙江"; // 假设省名为 Jiangsu
//        String cityName = "杭州";      // 假设市名为 Nanjing
//        String countyName = "这里";     // 假设县名为 Xuanwu
//        String growEnv = "开心";
//        String alias = "ROOSA";
        Scanner scanner = new Scanner(System.in);

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
        sort.setPlantId(plantInfo.getPlantId());
        sort.setGenusId(genus.getGenusId());
        sort.setCountyId(county.getCountyId());
        sort.setAlias(alias);
        sort.setGrowEnv(growEnv);
        sort.setSortId("暂定");
        // 设置其他分类信息的属性...

        // 添加分类信息
        addSort(sort);
        System.out.println("已成功添加分类信息。");

        /*
        更新植物分类信息
         */

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
            System.out.println("科名不存在，创建成功。");
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

}

