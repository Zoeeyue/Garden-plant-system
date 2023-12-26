package DAO.Impl;

import DAO.CityDAO;
import bean.City;
import bean.County;
import comm.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements CityDAO {

    private static final String SELECT_CITY_BY_ID =
            "SELECT * FROM City WHERE CityId = ?";

    private static final String SELECT_COUNTIES_BY_CITY_ID =
            "SELECT * FROM County WHERE CityId = ?";

    private static final String SEARCH_CITIES_BY_PROPERTY =
            "SELECT * FROM City WHERE %s = ?";

    private static final String ADD_CITY =
            "INSERT INTO City (CityId, CityName, ProvinceId) VALUES (?, ?, ?)";

    private static final String UPDATE_CITY =
            "UPDATE City SET CityName = ? WHERE CityId = ?";

    private static final String DELETE_CITY =
            "DELETE FROM City WHERE CityId = ?";

    private static final String GENERATE_CITY_ID =
            "SELECT CONCAT('CIT', RIGHT('0000' + CONVERT(VARCHAR, ISNULL(MAX(CAST(SUBSTRING(CityId, 4, LEN(CityId) - 3) AS INT)), 0) + 1), 4)) AS newId FROM City";

    private Connection connection;
    private DButil dbUtil;

    public CityDAOImpl() throws Exception {
        this.dbUtil = new DButil();
        this.connection = dbUtil.getConnection();
    }

    @Override
    public City getCityById(String cityId) {
        City city = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID)) {
            preparedStatement.setString(1, cityId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    city = new City();
                    city.setCityId(resultSet.getString("CityId"));
                    city.setCityName(resultSet.getString("CityName"));
                    city.setProvinceId(resultSet.getString("ProvinceId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public List<County> getCountiesByCityId(String cityId) {
        List<County> counties = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTIES_BY_CITY_ID)) {
            preparedStatement.setString(1, cityId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    County county = new County();
                    county.setCountyId(resultSet.getString("CountyId"));
                    county.setCountyName(resultSet.getString("CountyName"));
                    // Set other County attributes
                    counties.add(county);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return counties;
    }

    @Override
    public List<City> getCitiesByProperty(String propertyName, String propertyValue) {
        List<City> cities = new ArrayList<>();

        String query = String.format(SEARCH_CITIES_BY_PROPERTY, propertyName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, propertyValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    City city = new City();
                    city.setCityId(resultSet.getString("CityId"));
                    city.setCityName(resultSet.getString("CityName"));
                    city.setProvinceId(resultSet.getString("ProvinceId"));
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public String generateCityId() throws SQLException {
        String newId = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GENERATE_CITY_ID)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    newId = resultSet.getString("newId");
                }
            }
        }

        return newId;
    }

    @Override
    public void addCity(City city) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CITY)) {
            preparedStatement.setString(1, generateCityId());
            preparedStatement.setString(2, city.getCityName());
            preparedStatement.setString(3, city.getProvinceId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCity(City city) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CITY)) {
            preparedStatement.setString(1, city.getCityName());
            preparedStatement.setString(2, city.getCityId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCity(String cityId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY)) {
            preparedStatement.setString(1, cityId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

