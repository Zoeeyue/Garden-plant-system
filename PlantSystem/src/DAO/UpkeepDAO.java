package DAO;

import bean.Upkeep;

import java.util.List;

public interface UpkeepDAO {
    void addUpkeep(Upkeep upkeep);
    Upkeep getUpkeepById(String upkeepId);
    List<Upkeep> getAllUpkeeps();
    void updateUpkeep(Upkeep upkeep);
    void deleteUpkeep(String upkeepId);
}

