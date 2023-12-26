package DAO;

import bean.Upkeep;

import java.util.List;

public interface UpkeepDAO {

    void addUpkeep(Upkeep upkeep) throws Exception;
    void updateUpkeep(Upkeep upkeep) throws Exception;
    void deleteUpkeep(String UpkeepId) throws Exception;
    Upkeep findUpkeepById(String UpkeepId) throws Exception;
    List<Upkeep> findAllUpkeep() throws Exception;
    List<Upkeep> findUpkeepByProperty(String propertyName, String propertyValue) throws Exception;
}

