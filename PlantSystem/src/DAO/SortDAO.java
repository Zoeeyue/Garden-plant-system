package DAO;

import bean.PlantInfo;
import bean.PlantSortView;
import bean.Sort;

import java.util.List;

import java.sql.SQLException;
import java.util.List;

public interface SortDAO {
    Sort getSortById(String sortId);

    List<PlantInfo> getPlantsBySortId(String sortId);

    List<Sort> searchSortsByGrowEnv(String growEnvKeyword);

    List<Sort> getSortsByCountyId(String countyId);

    List<Sort> getSortsByProperty(String propertyName, String propertyValue);

    String generateSortId() throws SQLException;

    void addSort(Sort sort);

    void updateSort(Sort sort);

    void deleteSort(String sortId);

    PlantSortView getPlantSortViewById(String plantId) throws SQLException;//通过植物id查找
}


