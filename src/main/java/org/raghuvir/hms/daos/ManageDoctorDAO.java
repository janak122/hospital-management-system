package org.raghuvir.hms.daos;

import java.util.List;
import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.LoginBEAN;
import org.raghuvir.hms.dtos.DoctorInfoDTO;

public interface ManageDoctorDAO {
    
    List<DoctorBEAN>searchDoctor(String query);
    
    List<DoctorBEAN> getDoctorList(int pageno);
    
    LoginBEAN getIdPassword(String did) ;
    
    DoctorInfoDTO getDoctorInfo(String did);
    
    long getTotalDoctors();
}
