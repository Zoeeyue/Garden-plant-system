package DAO.Impl;

import DAO.SortDAO;
import bean.PlantInfo;
import bean.PlantRegionView;
import bean.PlantSortView;
import bean.Sort;
import comm.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortDAOImpl implements SortDAO {

    private static final String SELECT_SORT_BY_ID =
            "SELECT * FROM Sort WHERE SortId = ?";

    private static final String SELECT_PLANTS_BY_SORT_ID =
            "SELECT * FROM plant_info WHERE SortId = ?";

    private static final String SEARCH_SORTS_BY_GROW_ENV =
            "SELECT * FROM Sort WHERE GrowEnv LIKE ?";

    private static final String SELECT_SORTS_BY_COUNTY_ID =
            "SELECT * FROM Sort WHERE CountyId = ?";

    private static final String SELECT_SORTS_BY_PROPERTY =
            "SELECT * FROM Sort WHERE %s = ?";

    private static final String INSERT_SORT =
            "INSERT INTO Sort (SortId, GenusId, CountyId, plant_id, GrowEnv, Alias) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SORT =
            "UPDATE Sort SET GenusId = ?, CountyId = ?, plant_id = ?, GrowEnv = ?, Alias = ? WHERE SortId = ?";

    private static final String DELETE_SORT =
            "DELETE FROM Sort WHERE SortId = ?";

    private static final String GENERATE_SORT_ID =
            "SELECT CONCAT('SOR', RIGHT('0000' + CONVERT(VARCHAR, ISNULL(MAX(CAST(SUBSTRING(SortId, 4, LEN(SortId) - 3) AS INT)), 0) + 1), 4)) AS newId FROM Sort";

    private static final String GET_PLANT_SORT_VIEW_BY_ID =
            "SELECT * FROM PlantSortView WHERE plant_id = ?";

    private static final String GET_PLANT_REGION_VIEW_BY_ID =
            "SELECT * FROM PlantRegionView WHERE plant_id = ?";

    private Connection connection;
    private DButil dbUtil;

    public SortDAOImpl() throws Exception {
        this.dbUtil = new DButil();
        this.connection = dbUtil.getConnection();
    }

    @Override
    public Sort getSortById(String sortId) {
        Sort sort = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SORT_BY_ID)) {
            preparedStatement.setString(1, sortId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    sort = new Sort();
                    sort.setSortId(resultSet.getString("SortId"));
                    sort.setGenusId(resultSet.getString("GenusId"));
                    sort.setCountyId(resultSet.getString("CountyId"));
                    sort.setPlantId(resultSet.getString("PlantId"));
                    sort.setGrowEnv(resultSet.getString("GrowEnv"));
                    sort.setAlias(resultSet.getString("Alias"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sort;
    }

    @Override
    public List<PlantInfo> getPlantsBySortId(String sortId) {
        List<PlantInfo> plantInfos = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLANTS_BY_SORT_ID)) {
            preparedStatement.setString(1, sortId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    PlantInfo plantInfo = new PlantInfo();
                    // 设置植物信息属性
                    plantInfos.add(plantInfo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plantInfos;
    }

    @Override
    public List<Sort> searchSortsByGrowEnv(String growEnvKeyword) {
        List<Sort> sorts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_SORTS_BY_GROW_ENV)) {
            preparedStatement.setString(1, "%" + growEnvKeyword + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Sort sort = new Sort();
                    sort.setSortId(resultSet.getString("SortId"));
                    sort.setGenusId(resultSet.getString("GenusId"));
                    sort.setPlantId(resultSet.getString("plant_id"));
                    sort.setCountyId(resultSet.getString("CountyId"));
                    sort.setGrowEnv(resultSet.getString("GrowEnv"));
                    sort.setAlias(resultSet.getString("Alias"));
                    sorts.add(sort);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sorts;
    }

    @Override
    public List<Sort> getSortsByCountyId(String countyId) {
        List<Sort> sorts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SORTS_BY_COUNTY_ID)) {
            preparedStatement.setString(1, countyId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Sort sort = new Sort();
                    sort.setSortId(resultSet.getString("SortId"));
                    sort.setGenusId(resultSet.getString("GenusId"));
                    sort.setPlantId(resultSet.getString("plant_id"));
                    sort.setCountyId(resultSet.getString("CountyId"));
                    sort.setGrowEnv(resultSet.getString("GrowEnv"));
                    sort.setAlias(resultSet.getString("Alias"));
                    sorts.add(sort);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sorts;
    }

    @Override
    public List<Sort> getSortsByProperty(String propertyName, String propertyValue) {
        List<Sort> sorts = new ArrayList<>();
        String sql = String.format(SELECT_SORTS_BY_PROPERTY, propertyName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, propertyValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Sort sort = new Sort();
                    // 从结果集中获取字段值并设置到 Sort 对象的属性中
                    sort.setSortId(resultSet.getString("SortId"));
                    sort.setGenusId(resultSet.getString("GenusId"));
                    sort.setPlantId(resultSet.getString("plant_id"));
                    sort.setCountyId(resultSet.getString("CountyId"));
                    sort.setGrowEnv(resultSet.getString("GrowEnv"));
                    sort.setAlias(resultSet.getString("Alias"));
                    sorts.add(sort);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sorts;
    }

    @Override
    public String generateSortId() throws SQLException {
        String newSortId = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GENERATE_SORT_ID);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                newSortId = resultSet.getString("newId");
            }
        }

        return newSortId;
    }

    @Override
    public void addSort(Sort sort) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SORT)) {
            // 设置插入 Sort 记录的参数
            preparedStatement.setString(1,generateSortId());
            preparedStatement.setString(2, sort.getGenusId());
            preparedStatement.setString(3, sort.getCountyId());
            preparedStatement.setString(4, sort.getPlantId());
            preparedStatement.setString(5, sort.getGrowEnv());
            preparedStatement.setString(6, sort.getAlias());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSort(Sort sort) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SORT)) {
            // 设置更新 Sort 记录的参数
            preparedStatement.setString(6,sort.getSortId());
            preparedStatement.setString(1, sort.getGenusId());
            preparedStatement.setString(2, sort.getCountyId());
            preparedStatement.setString(3, sort.getPlantId());
            preparedStatement.setString(4, sort.getGrowEnv());
            preparedStatement.setString(5, sort.getAlias());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSort(String sortId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SORT)) {
            preparedStatement.setString(1, sortId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlantSortView getPlantSortViewById(String plantId) throws SQLException {
        PlantSortView plantSortView = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PLANT_SORT_VIEW_BY_ID)) {
            preparedStatement.setString(1, plantId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    plantSortView = new PlantSortView();
                    // 设置 PlantSortView 的属性，从 resultSet 中获取数据
                    plantSortView.setPlantId(resultSet.getString("plant_id"));
                    plantSortView.setPlantName(resultSet.getString("plant_name"));
                    plantSortView.setAlias(resultSet.getString("Alias"));
                    plantSortView.setFamilyName(resultSet.getString("FamilyName"));
                    plantSortView.setGenusName(resultSet.getString("GenusName"));
                    plantSortView.setProvinceName(resultSet.getString("ProvinceName"));
                    plantSortView.setCityName(resultSet.getString("CityName"));
                    plantSortView.setCountyName(resultSet.getString("CountyName"));
                    plantSortView.setGrowEnv(resultSet.getString("GrowEnv"));
                }
            }
        }

        return plantSortView;
    }

    @Override
    public PlantRegionView getPlantRegionById(String plantId) throws SQLException {
        PlantRegionView plantregionView = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PLANT_REGION_VIEW_BY_ID)) {
            preparedStatement.setString(1, plantId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    plantregionView = new PlantRegionView();
                    // 设置 plantregionView 的属性，从 resultSet 中获取数据
                    plantregionView.setPlantId(resultSet.getString("plant_id"));
                    plantregionView.setPlantName(resultSet.getString("plant_name"));
                    plantregionView.setProvinceName(resultSet.getString("ProvinceName"));
                    plantregionView.setCityName(resultSet.getString("CityName"));
                    plantregionView.setCountyName(resultSet.getString("CountyName"));
                }
            }
        }

        return plantregionView;
    }
}
