package DAO.Impl;

import DAO.GenusDAO;
import bean.Genus;
import bean.Sort;
import comm.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenusDAOImpl implements GenusDAO {

    private static final String GET_GENUS_BY_ID =
            "SELECT * FROM Genus WHERE GenusId = ?";

    private static final String GET_SORTS_BY_GENUS_ID =
            "SELECT * FROM Sort WHERE GenusId = ?";

    private static final String GET_GENERA_BY_PROPERTY =
            "SELECT * FROM Genus WHERE %s = ?";

    private static final String GENERATE_GENUS_ID =
            "SELECT CONCAT('GEN', RIGHT('0000' + CONVERT(VARCHAR, ISNULL(MAX(CAST(SUBSTRING(GenusId, 4, LEN(GenusId) - 3) AS INT)), 0) + 1), 4)) AS newId FROM Genus";

    private static final String ADD_GENUS =
            "INSERT INTO Genus (GenusId, GenusName, FamilyId) VALUES (?, ?, ?)";

    private static final String UPDATE_GENUS =
            "UPDATE Genus SET GenusName = ? WHERE GenusId = ?";

    private static final String DELETE_GENUS =
            "DELETE FROM Genus WHERE GenusId = ?";

    private Connection connection;
    private DButil dbUtil;

    public GenusDAOImpl() throws Exception {
        this.dbUtil = new DButil();
        this.connection = dbUtil.getConnection();
    }

    @Override
    public Genus getGenusById(String genusId) {
        Genus genus = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_GENUS_BY_ID)) {
            preparedStatement.setString(1, genusId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    genus = new Genus();
                    genus.setGenusId(resultSet.getString("GenusId"));
                    genus.setGenusName(resultSet.getString("GenusName"));
                    genus.setFamilyId(resultSet.getString("FamilyId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genus;
    }

    @Override
    public List<Sort> getSortsByGenusId(String genusId) {
        List<Sort> sorts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SORTS_BY_GENUS_ID)) {
            preparedStatement.setString(1, genusId);

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
    public List<Genus> getGeneraByProperty(String propertyName, String propertyValue) {
        List<Genus> genera = new ArrayList<>();
        String query = String.format(GET_GENERA_BY_PROPERTY, propertyName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, propertyValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Genus genus = new Genus();
                    genus.setGenusId(resultSet.getString("GenusId"));
                    genus.setGenusName(resultSet.getString("GenusName"));
                    genus.setFamilyId(resultSet.getString("FamilyId"));
                    genera.add(genus);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genera;
    }

    @Override
    public String generateGenusId() throws SQLException {
        String generatedId = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GENERATE_GENUS_ID)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    generatedId = resultSet.getString(1);
                }
            }
        }

        return generatedId;
    }

    @Override
    public void addGenus(Genus genus) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_GENUS)) {
            preparedStatement.setString(1, generateGenusId());
            preparedStatement.setString(2, genus.getGenusName());
            preparedStatement.setString(3, genus.getFamilyId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGenus(Genus genus) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GENUS)) {
            preparedStatement.setString(1, genus.getGenusName());
            preparedStatement.setString(2, genus.getGenusId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGenus(String genusId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GENUS)) {
            preparedStatement.setString(1, genusId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

