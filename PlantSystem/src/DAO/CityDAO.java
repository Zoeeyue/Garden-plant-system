package DAO;

import bean.City;
import bean.County;

import java.sql.SQLException;
import java.util.List;

public interface CityDAO {
    City getCityById(String cityId);

    List<County> getCountiesByCityId(String cityId);

    List<City> getCitiesByProperty(String propertyName, String propertyValue);

    String generateCityId() throws SQLException;

    void addCity(City city);

    void updateCity(City city);

    void deleteCity(String cityId);
}

