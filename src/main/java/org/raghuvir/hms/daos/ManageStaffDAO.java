package org.raghuvir.hms.daos;

import java.util.List;

import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.dtos.StaffInfoDTO;

public interface ManageStaffDAO {
    
    List<StaffBEAN> searchStaff(String query);
    
    StaffInfoDTO getStaffInfo(String sid);
    
    long getTotalStaff() ;
    
    LoginBEAN getIdPassword(String sid);
}
