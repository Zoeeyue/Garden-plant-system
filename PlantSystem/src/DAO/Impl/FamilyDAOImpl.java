package DAO.Impl;

import DAO.FamilyDAO;
import bean.Family;
import bean.Genus;
import bean.Sort;
import comm.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamilyDAOImpl implements FamilyDAO {

    private static final String GET_FAMILY_BY_ID =
            "SELECT * FROM Family WHERE FamilyId = ?";

    private static final String GET_GENERA_BY_FAMILY_ID =
            "SELECT * FROM Genus WHERE FamilyId = ?";

    private static final String GET_SORTS_BY_GENUS_ID =
            "SELECT * FROM Sort WHERE GenusId = ?";

    private static final String GET_FAMILIES_BY_PROPERTY =
            "SELECT * FROM Family WHERE %s = ?";

    private static final String GENERATE_FAMILY_ID =
            "SELECT CONCAT('FAM', RIGHT('0000' + CONVERT(VARCHAR, ISNULL(MAX(CAST(SUBSTRING(FamilyId, 4, LEN(FamilyId) - 3) AS INT)), 0) + 1), 4)) AS newId FROM Family";

    private static final String ADD_FAMILY =
            "INSERT INTO Family (FamilyId, FamilyName) VALUES (?, ?)";

    private static final String UPDATE_FAMILY =
            "UPDATE Family SET FamilyName = ? WHERE FamilyId = ?";

    private static final String DELETE_FAMILY =
            "DELETE FROM Family WHERE FamilyId = ?";

    private Connection connection;
    private DButil dbUtil;

    public FamilyDAOImpl() throws Exception {
        this.dbUtil = new DButil();
        this.connection = dbUtil.getConnection();
    }

    @Override
    public Family getFamilyById(String familyId) {
        Family family = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_FAMILY_BY_ID)) {
            preparedStatement.setString(1, familyId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    family = new Family();
                    family.setFamilyId(resultSet.getString("FamilyId"));
                    family.setFamilyName(resultSet.getString("FamilyName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return family;
    }

    @Override
    public List<Genus> getGeneraByFamilyId(String familyId) {
        List<Genus> genera = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_GENERA_BY_FAMILY_ID)) {
            preparedStatement.setString(1, familyId);

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
    public List<Family> getFamiliesByProperty(String propertyName, String propertyValue) {
        List<Family> families = new ArrayList<>();
        String query = String.format(GET_FAMILIES_BY_PROPERTY, propertyName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, propertyValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Family family = new Family();
                    family.setFamilyId(resultSet.getString("FamilyId"));
                    family.setFamilyName(resultSet.getString("FamilyName"));
                    families.add(family);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return families;
    }

    @Override
    public String generateFamilyId() throws SQLException {
        String generatedId = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GENERATE_FAMILY_ID)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    generatedId = resultSet.getString(1);
                }
            }
        }

        return generatedId;
    }

    @Override
    public void addFamily(Family family) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_FAMILY)) {
            preparedStatement.setString(1, generateFamilyId());
            preparedStatement.setString(2, family.getFamilyName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFamily(Family family) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FAMILY)) {
            preparedStatement.setString(1, family.getFamilyName());
            preparedStatement.setString(2, family.getFamilyId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFamily(String familyId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FAMILY)) {
            preparedStatement.setString(1, familyId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
