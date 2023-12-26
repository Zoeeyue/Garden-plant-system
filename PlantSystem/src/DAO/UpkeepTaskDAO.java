package DAO;

import bean.UpkeepTask;

import java.util.List;

public interface UpkeepTaskDAO {

    void addTask(UpkeepTask upkeepTask) throws Exception;
    void updateTask(UpkeepTask upkeepTask) throws Exception;
    void deleteTask(String UTaskId) throws Exception;
    UpkeepTask findTaskById(String UTaskId);
    List<UpkeepTask> findAll() throws Exception;
    List<UpkeepTask> findTaskByProperty(String propertyName, String propertyValue) throws Exception;
}

