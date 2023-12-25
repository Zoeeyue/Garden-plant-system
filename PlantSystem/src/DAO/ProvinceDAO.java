package DAO;

import bean.City;
import bean.Province;

import java.sql.SQLException;
import java.util.List;

public interface ProvinceDAO {
    Province getProvinceById(String provinceId);

    List<City> getCitiesByProvinceId(String provinceId);

    List<Province> getProvincesByProperty(String propertyName, String propertyValue);

    String generateProvinceId() throws SQLException;

    void addProvince(Province province);

    void updateProvince(Province province);

    void deleteProvince(String provinceId);
}

