package org.raghuvir.hms.services;

import java.util.List;

import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.dtos.StaffInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManageStaffService {
    
    List<StaffBEAN> searchStaff(String query);
    
    Page<StaffBEAN> getStaffList(Pageable pageable);
    
    StaffInfoDTO getStaffInfo(String sid);
    
    long getTotalStaff() ;
    LoginBEAN getIdPassword(String sid) ;
}
