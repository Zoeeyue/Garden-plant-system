package DAO;

import bean.UpkeepStaff;

import java.util.List;

public interface UpkeepStaffDAO {

    void addUpkeepStaff(UpkeepStaff staff) throws Exception;
    void updateUpkeepStaff(UpkeepStaff staff);
    void deleteUpkeepStaff(String staffId) throws Exception;
    UpkeepStaff findUpkeepStaffById(String staffId);
    List<UpkeepStaff> findAllUpkeepStaff() throws Exception;
    List<UpkeepStaff> findUpkeepStaffByProperty(String propertyName, String propertyValue);
    UpkeepStaff login(String username, String password) throws Exception;
}

