package DAO;

import bean.County;
import bean.Sort;

import java.sql.SQLException;
import java.util.List;

public interface CountyDAO {
    County getCountyById(String countyId);

    List<Sort> getSortsByCountyId(String countyId);

    List<County> getCountiesByProperty(String propertyName, String propertyValue);

    String generateCountyId() throws SQLException;

    void addCounty(County county);

    void updateCounty(County county);

    void deleteCounty(String countyId);
}

