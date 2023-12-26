package DAO.Impl;

import DAO.UpkeepTaskDAO;
import bean.UpkeepTask;
import comm.DButil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpkeepTaskDAOImpl implements UpkeepTaskDAO {

    private DButil dbUtil;

    private static final String INSERT_SQL =
            "INSERT INTO UpkeepTask (UTaskId, Plant_id, UTaskSid, UTaskDes, UTaskStatus) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE UpkeepTask SET Plant_id = ?, UTaskSid = ?, UTaskDes = ?, UTaskStatus = ? WHERE UTaskId = ?";

    private static final String DELETE_SQL =
            "DELETE FROM UpkeepTask WHERE UTaskId = ?";

    private static final String FIND_BY_ID_SQL =
            "SELECT * FROM UpkeepTask WHERE UTaskId = ?";

    private static final String FIND_ALL_SQL =
            "SELECT * FROM UpkeepTask";

    private static final String FIND_BY_PROPERTY_SQL =
            "SELECT * FROM UpkeepTask WHERE %s = ?";
    public static final String STATUS_PENDING = "未完成";
    public static final String STATUS_COMPLETED = "已完成";

    public UpkeepTaskDAOImpl() {
        this.dbUtil = new DButil();
    }

    @Override
    public void addTask(UpkeepTask upkeepTask) throws Exception {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, upkeepTask.getUTaskId());
            preparedStatement.setString(2, upkeepTask.getPlantId());
            preparedStatement.setString(3, upkeepTask.getUTaskSid());
            preparedStatement.setString(4, upkeepTask.getUTaskDes());
            preparedStatement.setString(5, upkeepTask.getUTaskStatus());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateTask(UpkeepTask upkeepTask) throws Exception {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, upkeepTask.getPlantId());
            preparedStatement.setString(2, upkeepTask.getUTaskSid());
            preparedStatement.setString(3, upkeepTask.getUTaskDes());
            preparedStatement.setString(4, upkeepTask.getUTaskStatus());
            preparedStatement.setString(5, upkeepTask.getUTaskId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteTask(String UTaskId) throws Exception {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setString(1, UTaskId);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public UpkeepTask findTaskById(String UTaskId) {
        UpkeepTask upkeepTask = null;

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setString(1, UTaskId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    upkeepTask = createUpkeepTaskFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return upkeepTask;
    }

    @Override
    public List<UpkeepTask> findAll() throws Exception {
        List<UpkeepTask> upkeepTasks = new ArrayList<>();

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                UpkeepTask upkeepTask = createUpkeepTaskFromResultSet(resultSet);
                upkeepTasks.add(upkeepTask);
            }
        }

        return upkeepTasks;
    }

    @Override
    public List<UpkeepTask> findTaskByProperty(String propertyName, String propertyValue) throws Exception {
        List<UpkeepTask> upkeepTasks = new ArrayList<>();

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     String.format(FIND_BY_PROPERTY_SQL, propertyName))) {
            preparedStatement.setString(1, propertyValue);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    UpkeepTask upkeepTask = createUpkeepTaskFromResultSet(resultSet);
                    upkeepTasks.add(upkeepTask);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return upkeepTasks;
    }

    private UpkeepTask createUpkeepTaskFromResultSet(ResultSet resultSet) throws SQLException {
        UpkeepTask upkeepTask = new UpkeepTask();
        upkeepTask.setUTaskId(resultSet.getString("UTaskId"));
        upkeepTask.setPlantId(resultSet.getString("Plant_id"));
        upkeepTask.setUTaskSid(resultSet.getString("UTaskSid"));
        upkeepTask.setUTaskDes(resultSet.getString("UTaskDes"));
        upkeepTask.setUTaskStatus(resultSet.getString("UTaskStatus"));
        return upkeepTask;
    }
}
