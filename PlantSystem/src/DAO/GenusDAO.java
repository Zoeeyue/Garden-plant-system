package DAO;

import bean.Genus;
import bean.Sort;

import java.sql.SQLException;
import java.util.List;

public interface GenusDAO {
    Genus getGenusById(String genusId);

    List<Sort> getSortsByGenusId(String genusId);

    List<Genus> getGeneraByProperty(String propertyName, String propertyValue);

    String generateGenusId() throws SQLException; // 新增的生成编号的方法

    void addGenus(Genus genus);

    void updateGenus(Genus genus);

    void deleteGenus(String genusId);
}
