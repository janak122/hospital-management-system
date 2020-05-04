package org.raghuvir.hms.services;

import java.util.List;
import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.dtos.DoctorInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManageDoctorService {
    
    List<DoctorBEAN>searchDoctor(String query);
    
    Page<DoctorBEAN> getDoctorList(Pageable pageable);
    
    DoctorInfoDTO getDoctorInfo(String did);
    
    long getTotalDoctors();
    
    LoginBEAN getIdPassword(String did);
}
