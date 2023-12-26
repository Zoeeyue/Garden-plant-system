package DAO.Impl;

import DAO.UpkeepStaffDAO;
import bean.UpkeepStaff;
import comm.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpkeepStaffDAOImpl implements UpkeepStaffDAO {

    private DButil dbUtil;

    private static final String INSERT_SQL = "INSERT INTO UpkeepStaff (UpkeepSid, UpkeepSname, UpkeepPwd) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE UpkeepStaff SET UpkeepSname = ?, UpkeepPwd = ? WHERE UpkeepSid = ?";
    private static final String DELETE_SQL = "DELETE FROM UpkeepStaff WHERE UpkeepSid = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM UpkeepStaff WHERE UpkeepSid = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM UpkeepStaff";
    private static final String FIND_BY_PROPERTY_SQL = "SELECT * FROM UpkeepStaff WHERE %s = ?";

    public UpkeepStaffDAOImpl() {
        this.dbUtil = new DButil();
    }

    @Override
    public void addUpkeepStaff(UpkeepStaff staff) throws Exception {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            setParameters(preparedStatement, staff);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    @Override
    public void updateUpkeepStaff(UpkeepStaff staff) {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            setParameters(preparedStatement, staff);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUpkeepStaff(String staffId) throws Exception {
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setString(1, staffId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
    }

    @Override
    public UpkeepStaff findUpkeepStaffById(String staffId) {
        UpkeepStaff staff = null;
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setString(1, staffId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    staff = mapResultSetToUpkeepStaff(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public List<UpkeepStaff> findAllUpkeepStaff() throws Exception {
        List<UpkeepStaff> staffList = new ArrayList<>();
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                UpkeepStaff staff = mapResultSetToUpkeepStaff(resultSet);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }
        return staffList;
    }

    @Override
    public List<UpkeepStaff> findUpkeepStaffByProperty(String propertyName, String propertyValue) {
        List<UpkeepStaff> staffList = new ArrayList<>();
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(FIND_BY_PROPERTY_SQL, propertyName))) {
            preparedStatement.setString(1, propertyValue);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    UpkeepStaff staff = mapResultSetToUpkeepStaff(resultSet);
                    staffList.add(staff);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffList;
    }

    @Override
    public UpkeepStaff login(String username, String password) throws Exception {
        DButil db = new DButil();
        Connection conn = db.getConnection();

        // SQL 查询语句，根据用户名和密码查找管理员信息
        String sql = "SELECT * FROM admin_info WHERE admin_id = ? AND admin_password = ?";

        // 参数列表
        List<Object> list = new ArrayList<>();
        list.add(username);
        list.add(password);

        // 执行查询
        List<Map<String, String>> result = db.excutequery(sql, list, conn);

        // 关闭连接
        db.close(conn);

        // 如果查询结果非空，将结果封装为 adminInfo 对象并返回
        if (!result.isEmpty()) {
            Map<String, String> adminInfoMap = result.get(0); // 假设只查询到一个结果
            UpkeepStaff upkeepStaff = new UpkeepStaff();

            // 设置 adminInfo 对象的属性
            upkeepStaff.setUpkeepSid(adminInfoMap.get("admin_id"));
            upkeepStaff.setUpkeepSname(adminInfoMap.get("admin_name"));
            upkeepStaff.setUpkeepPwd("admin_password");;
            // 设置其他属性...

            return upkeepStaff;
        }

        // 如果查询结果为空，返回 null 或抛出异常，取决于业务需求
        return null;
    }


    private UpkeepStaff mapResultSetToUpkeepStaff(ResultSet resultSet) throws SQLException {
        // 从 ResultSet 中提取数据并创建 UpkeepStaff 对象
        // 请根据实际的表结构和字段类型进行适当的调整
        return new UpkeepStaff(
                resultSet.getString("UpkeepSid"),
                resultSet.getString("UpkeepSname"),
                resultSet.getString("UpkeepPwd")
        );
    }

    private void setParameters(PreparedStatement preparedStatement, UpkeepStaff staff) throws SQLException {
        preparedStatement.setString(1, staff.getUpkeepSid());
        preparedStatement.setString(2, staff.getUpkeepSname());
        preparedStatement.setString(3, staff.getUpkeepPwd());
    }
}
