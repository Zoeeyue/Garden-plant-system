package DAO;

import bean.Family;
import bean.Genus;
import bean.Sort;

import java.sql.SQLException;
import java.util.List;

public interface FamilyDAO {
    Family getFamilyById(String familyId);

    List<Genus> getGeneraByFamilyId(String familyId);

    List<Sort> getSortsByGenusId(String genusId);

    List<Family> getFamiliesByProperty(String propertyName, String propertyValue);

    String generateFamilyId() throws SQLException; // 新增的生成编号的方法

    void addFamily(Family family);

    void updateFamily(Family family);

    void deleteFamily(String familyId);
}
