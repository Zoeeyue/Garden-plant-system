package DAO.Impl;

import DAO.ProvinceDAO;
import bean.City;
import bean.Province;
import comm.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAOImpl implements ProvinceDAO {

    private static final String SELECT_PROVINCE_BY_ID =
            "SELECT * FROM Province WHERE ProvinceId = ?";

    private static final String SELECT_CITIES_BY_PROVINCE_ID =
            "SELECT * FROM City WHERE ProvinceId = ?";

    private static final String SEARCH_PROVINCES_BY_PROPERTY =
            "SELECT * FROM Province WHERE %s = ?";

    private static final String ADD_PROVINCE =
            "INSERT INTO Province (ProvinceId, ProvinceName) VALUES (?, ?)";

    private static final String UPDATE_PROVINCE =
            "UPDATE Province SET ProvinceName = ? WHERE ProvinceId = ?";

    private static final String DELETE_PROVINCE =
            "DELETE FROM Province WHERE ProvinceId = ?";

    private static final String GENERATE_PROVINCE_ID =
            "SELECT CONCAT('PRO', LPAD(IFNULL(SUBSTRING(MAX(ProvinceId), 4) + 1, 1), 4, '0')) AS newId FROM Province";

    private Connection connection;

    public ProvinceDAOImpl() throws Exception {
        this.connection = DButil.getConnection();
    }

    @Override
    public Province getProvinceById(String provinceId) {
        Province province = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE_BY_ID)) {
            preparedStatement.setString(1, provinceId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    province = new Province();
                    province.setProvinceId(resultSet.getString("ProvinceId"));
                    province.setProvinceName(resultSet.getString("ProvinceName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return province;
    }

    @Override
    public List<City> getCitiesByProvinceId(String provinceId) {
        List<City> cities = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITIES_BY_PROVINCE_ID)) {
            preparedStatement.setString(1, provinceId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    City city = new City();
                    city.setCityId(resultSet.getString("CityId"));
                    city.setCityName(resultSet.getString("CityName"));
                    // Set other City attributes
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public List<Province> getProvincesByProperty(String propertyName, String propertyValue) {
        List<Province> provinces = new ArrayList<>();

        String query = String.format(SEARCH_PROVINCES_BY_PROPERTY, propertyName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, propertyValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Province province = new Province();
                    province.setProvinceId(resultSet.getString("ProvinceId"));
                    province.setProvinceName(resultSet.getString("ProvinceName"));
                    provinces.add(province);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return provinces;
    }

    @Override
    public String generateProvinceId() throws SQLException {
        String newId = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GENERATE_PROVINCE_ID)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    newId = resultSet.getString("newId");
                }
            }
        }

        return newId;
    }

    @Override
    public void addProvince(Province province) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PROVINCE)) {
            preparedStatement.setString(1, generateProvinceId());
            preparedStatement.setString(2, province.getProvinceName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProvince(Province province) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROVINCE)) {
            preparedStatement.setString(1, province.getProvinceName());
            preparedStatement.setString(2, province.getProvinceId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProvince(String provinceId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROVINCE)) {
            preparedStatement.setString(1, provinceId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
