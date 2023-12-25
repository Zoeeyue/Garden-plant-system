package DAO.Impl;

import DAO.UpkeepDAO;
import bean.Upkeep;
import comm.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpkeepDAOImpl implements UpkeepDAO {
    private DButil dbUtil;

    public UpkeepDAOImpl() {
        this.dbUtil = new DButil();
    }

    @Override
    public void addUpkeep(Upkeep upkeep) {
        Connection connection = null;
        try {
            connection = dbUtil.getConnection();

            String query = "INSERT INTO Upkeep (UpkeepId, UpkeepName, UpkeepEtime, UpkeepEplace, UpkeepSiD, UpkeepDes, plant_id, UpkeepProcess) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            List<Object> params = new ArrayList<>();
            params.add(upkeep.getUpkeepId());
            params.add(upkeep.getUpkeepName());
            params.add(new Timestamp(upkeep.getUpkeepEtime().getTime()));
            params.add(upkeep.getUpkeepEplace());
            params.add(upkeep.getUpkeepSiD());
            params.add(upkeep.getUpkeepDes());
            params.add(upkeep.getPlantId());
            params.add(upkeep.getUpkeepProcess());

            dbUtil.excute(query, params, connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    dbUtil.close(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Upkeep getUpkeepById(String upkeepId) {
        Connection connection = null;
        try {
            connection = dbUtil.getConnection();

            String query = "SELECT * FROM Upkeep WHERE UpkeepId = ?";
            List<Object> params = new ArrayList<>();
            params.add(upkeepId);

            List<Map<String, String>> result = dbUtil.excutequery(query, params, connection);

            if (!result.isEmpty()) {
                Map<String, String> resultMap = result.get(0);
                return mapResultToUpkeep(resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    dbUtil.close(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public List<Upkeep> getAllUpkeeps() {
        Connection connection = null;
        List<Upkeep> upkeeps = new ArrayList<>();
        try {
            connection = dbUtil.getConnection();

            String query = "SELECT * FROM Upkeep";

            List<Map<String, String>> result = dbUtil.excutequery(query, null, connection);

            for (Map<String, String> resultMap : result) {
                Upkeep upkeep = mapResultToUpkeep(resultMap);
                upkeeps.add(upkeep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    dbUtil.close(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return upkeeps;
    }

    @Override
    public void updateUpkeep(Upkeep upkeep) {
        Connection connection = null;
        try {
            connection = dbUtil.getConnection();

            String query = "UPDATE Upkeep SET UpkeepName = ?, UpkeepEtime = ?, UpkeepEplace = ?, UpkeepSiD = ?, " +
                    "UpkeepDes = ?, plant_id = ?, UpkeepProcess = ? WHERE UpkeepId = ?";

            List<Object> params = new ArrayList<>();
            params.add(upkeep.getUpkeepName());
            params.add(new Timestamp(upkeep.getUpkeepEtime().getTime()));
            params.add(upkeep.getUpkeepEplace());
            params.add(upkeep.getUpkeepSiD());
            params.add(upkeep.getUpkeepDes());
            params.add(upkeep.getPlantId());
            params.add(upkeep.getUpkeepProcess());
            params.add(upkeep.getUpkeepId());

            dbUtil.excute(query, params, connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    dbUtil.close(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUpkeep(String upkeepId) {
        Connection connection = null;
        try {
            connection = dbUtil.getConnection();

            String query = "DELETE FROM Upkeep WHERE UpkeepId = ?";

            List<Object> params = new ArrayList<>();
            params.add(upkeepId);

            dbUtil.excute(query, params, connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    dbUtil.close(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Upkeep mapResultToUpkeep(Map<String, String> resultMap) {
        Upkeep upkeep = new Upkeep();
        upkeep.setUpkeepId(resultMap.get("UpkeepId"));
        upkeep.setUpkeepName(resultMap.get("UpkeepName"));
        upkeep.setUpkeepEtime(Timestamp.valueOf(resultMap.get("UpkeepEtime")));
        upkeep.setUpkeepEplace(resultMap.get("UpkeepEplace"));
        upkeep.setUpkeepSiD(resultMap.get("UpkeepSiD"));
        upkeep.setUpkeepDes(resultMap.get("UpkeepDes"));
        upkeep.setPlantId(resultMap.get("plant_id"));
        upkeep.setUpkeepProcess(resultMap.get("UpkeepProcess"));
        return upkeep;
    }
}
