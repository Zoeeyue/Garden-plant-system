package DAO.Impl;

import DAO.UpkeepDAO;
import bean.Upkeep;
import comm.DButil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UpkeepDAOImpl implements UpkeepDAO {

    private DButil dbUtil;

    private static final String INSERT_SQL =
            "INSERT INTO Upkeep (UpkeepId, UTaskId, plant_id, UpkeepName, UpkeepDes, UpkeepTime, UpkeepPlace, UpkeepSiD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE Upkeep SET UTaskId = ?, plant_id = ?, UpkeepName = ?, UpkeepDes = ?, UpkeepTime = ?, UpkeepPlace = ?, UpkeepSiD = ? WHERE UpkeepId = ?";

    private static final String DELETE_SQL =
            "DELETE FROM Upkeep WHERE UpkeepId = ?";

    private static final String FIND_BY_ID_SQL =
            "SELECT * FROM Upkeep WHERE UpkeepId = ?";

    private static final String FIND_ALL_SQL =
            "SELECT * FROM Upkeep";

    private static final String FIND_BY_PROPERTY_SQL =
            "SELECT * FROM Upkeep WHERE %s = ?";

    public UpkeepDAOImpl() {
        this.dbUtil = new DButil();
    }

    @Override
    public void addUpkeep(Upkeep upkeep) throws Exception {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            // 生成养护编号
            String newUpkeepId = generateUpkeepId(connection);

            // 设置 Upkeep 表的属性
            statement.setString(1, newUpkeepId);
            statement.setString(2, upkeep.getUTaskId());
            statement.setString(3, upkeep.getPlant_id());
            statement.setString(4, upkeep.getUpkeepName());
            statement.setString(5, upkeep.getUpkeepDes());
            // 将字符串时间转换为 java.sql.Timestamp 类型
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(upkeep.getUpkeepTime());
            java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            statement.setTimestamp(6, timestamp);

            statement.setString(7, upkeep.getUpkeepPlace());
            statement.setString(8, upkeep.getUpkeepSiD());
            // 执行插入操作
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("养护信息添加成功。");
            } else {
                System.out.println("养护信息添加失败。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUpkeep(Upkeep upkeep) throws Exception {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, upkeep.getUTaskId());
            preparedStatement.setString(2, upkeep.getPlant_id());
            preparedStatement.setString(3, upkeep.getUpkeepName());
            preparedStatement.setString(4, upkeep.getUpkeepDes());
// 将字符串时间转换为 java.sql.Timestamp 类型
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(upkeep.getUpkeepTime());
            java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            preparedStatement.setTimestamp(5, timestamp);
            preparedStatement.setString(6, upkeep.getUpkeepPlace());
            preparedStatement.setString(7, upkeep.getUpkeepSiD());
            preparedStatement.setString(8, upkeep.getUpkeepId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
    }


    @Override
    public void deleteUpkeep(String UpkeepId) throws Exception {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setString(1, UpkeepId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    @Override
    public Upkeep findUpkeepById(String UpkeepId) throws Exception {
        Upkeep upkeep = null;
        DButil db = new DButil();
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setString(1, UpkeepId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    upkeep = mapResultSetToUpkeep(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return upkeep;
    }

    @Override
    public List<Upkeep> findAllUpkeep() {
        List<Upkeep> upkeepList = new ArrayList<>();
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Upkeep upkeep = mapResultSetToUpkeep(resultSet);
                upkeepList.add(upkeep);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        } catch (Exception e) {
            e.printStackTrace();
        }
        return upkeepList;
    }



    @Override
    public List<Upkeep> findUpkeepByProperty(String propertyName, String propertyValue) throws Exception {
        List<Upkeep> upkeepList = new ArrayList<>();
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(FIND_BY_PROPERTY_SQL, propertyName))) {
            preparedStatement.setString(1, propertyValue);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Upkeep upkeep = mapResultSetToUpkeep(resultSet);
                    upkeepList.add(upkeep);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return upkeepList;
    }

    private Upkeep mapResultSetToUpkeep(ResultSet resultSet) throws SQLException {
        // 从 ResultSet 中提取数据并创建 Upkeep 对象
        // 请根据实际的表结构和字段类型进行适当的调整

        // 获取字符串形式的时间
        String stringTime = resultSet.getString("UpkeepTime");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = " ";
        try {
            // 解析字符串时间
            Date date = dateFormat.parse(stringTime);
            // 将日期格式化为不包含毫秒的字符串
            formattedTime = dateFormat.format(date);
            System.out.println(formattedTime);
        } catch (ParseException e) {
            e.printStackTrace();
            // 处理日期格式异常
        }
        return new Upkeep(
                resultSet.getString("UpkeepId"),
                resultSet.getString("UTaskId"),
                resultSet.getString("plant_id"),
                resultSet.getString("UpkeepName"),
                resultSet.getString("UpkeepDes"),
                formattedTime,  // 直接使用字符串形式的时间
                resultSet.getString("UpkeepPlace"),
                resultSet.getString("UpkeepSiD")
        );
    }


    private void setParameters(PreparedStatement preparedStatement, Upkeep upkeep) throws SQLException {
        // 注意：这里假设 UpkeepTime 属性在 Upkeep 实体类中的类型是 String

        preparedStatement.setString(1, upkeep.getUpkeepId());
        preparedStatement.setString(2, upkeep.getUTaskId());
        preparedStatement.setString(3, upkeep.getPlant_id());
        preparedStatement.setString(4, upkeep.getUpkeepName());
        preparedStatement.setString(5, upkeep.getUpkeepDes());
        preparedStatement.setString(6, upkeep.getUpkeepTime());  // 直接使用字符串形式的时间
        preparedStatement.setString(7, upkeep.getUpkeepPlace());
        preparedStatement.setString(8, upkeep.getUpkeepSiD());
    }


    private String generateUpkeepId(Connection connection) throws SQLException {
        String queryMaxId = "SELECT CONCAT('UP', RIGHT('0000' + CONVERT(VARCHAR, ISNULL(MAX(CAST(SUBSTRING(UpkeepId, 3, LEN(UpkeepId) - 2) AS INT)), 0) + 1), 4)) AS newId FROM Upkeep";
        try (PreparedStatement statement = connection.prepareStatement(queryMaxId);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("newId");
            }
        }
        return null;
    }
}
