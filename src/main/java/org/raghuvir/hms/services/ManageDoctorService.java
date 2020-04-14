package org.raghuvir.hms.services;

import java.util.List;
import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.dtos.DoctorInfoDTO;

public interface ManageDoctorService {
    
    List<DoctorBEAN>searchDoctor(String query);
    
    List<DoctorBEAN> getDoctorList(int pageno);
    
    DoctorInfoDTO getDoctorInfo(String did);
    
    long getTotalDoctors();
}
