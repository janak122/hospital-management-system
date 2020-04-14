package org.raghuvir.hms.services;

import java.util.List;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.dtos.StaffInfoDTO;

public interface ManageStaffService {
    
    List<StaffBEAN> searchStaff(String query);
    
    List<StaffBEAN> getStaffList(int pageno);
    
    StaffInfoDTO getStaffInfo(String sid);
    
    long getTotalStaff() ;
}
