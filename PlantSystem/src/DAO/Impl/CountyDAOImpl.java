package DAO.Impl;

import DAO.CountyDAO;
import bean.County;
import bean.Sort;
import comm.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountyDAOImpl implements CountyDAO {

    private static final String SELECT_COUNTY_BY_ID =
            "SELECT * FROM County WHERE CountyId = ?";

    private static final String SELECT_SORTS_BY_COUNTY_ID =
            "SELECT * FROM Sort WHERE CountyId = ?";

    private static final String SEARCH_COUNTIES_BY_PROPERTY =
            "SELECT * FROM County WHERE %s = ?";

    private static final String ADD_COUNTY =
            "INSERT INTO County (CountyId, CountyName, CityId) VALUES (?, ?, ?)";

    private static final String UPDATE_COUNTY =
            "UPDATE County SET CountyName = ? WHERE CountyId = ?";

    private static final String DELETE_COUNTY =
            "DELETE FROM County WHERE CountyId = ?";

    private static final String GENERATE_COUNTY_ID =
            "SELECT CONCAT('COU', RIGHT('0000' + CONVERT(VARCHAR, ISNULL(MAX(CAST(SUBSTRING(CountyId, 4, LEN(CountyId) - 3) AS INT)), 0) + 1), 4)) AS newId FROM County";

    private Connection connection;
    private DButil dbUtil;

    public CountyDAOImpl() throws Exception {
        this.dbUtil = new DButil();
        this.connection = dbUtil.getConnection();
    }

    @Override
    public County getCountyById(String countyId) {
        County county = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTY_BY_ID)) {
            preparedStatement.setString(1, countyId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    county = new County();
                    county.setCountyId(resultSet.getString("CountyId"));
                    county.setCountyName(resultSet.getString("CountyName"));
                    county.setCityId(resultSet.getString("CityId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return county;
    }

    @Override
    public List<Sort> getSortsByCountyId(String countyId) {
        List<Sort> sorts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SORTS_BY_COUNTY_ID)) {
            preparedStatement.setString(1, countyId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Sort sort = new Sort();
                    sort.setGenusId(resultSet.getString("GenusId"));
                    sort.setPlantId(resultSet.getString("PlantId"));
                    sort.setCountyId(resultSet.getString("CountyId"));
                    sort.setGrowEnv(resultSet.getString("GrowEnv"));
                    sort.setAlias(resultSet.getString("Alias"));
                    // Set other Sort attributes
                    sorts.add(sort);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sorts;
    }

    @Override
    public List<County> getCountiesByProperty(String propertyName, String propertyValue) {
        List<County> counties = new ArrayList<>();

        String query = String.format(SEARCH_COUNTIES_BY_PROPERTY, propertyName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, propertyValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    County county = new County();
                    county.setCountyId(resultSet.getString("CountyId"));
                    county.setCountyName(resultSet.getString("CountyName"));
                    county.setCityId(resultSet.getString("CityId"));
                    counties.add(county);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return counties;
    }

    @Override
    public String generateCountyId() throws SQLException {
        String newId = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GENERATE_COUNTY_ID)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    newId = resultSet.getString("newId");
                }
            }
        }

        return newId;
    }

    @Override
    public void addCounty(County county) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_COUNTY)) {
            preparedStatement.setString(1, generateCountyId());
            preparedStatement.setString(2, county.getCountyName());
            preparedStatement.setString(3, county.getCityId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCounty(County county) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNTY)) {
            preparedStatement.setString(1, county.getCountyName());
            preparedStatement.setString(2, county.getCountyId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCounty(String countyId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COUNTY)) {
            preparedStatement.setString(1, countyId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
