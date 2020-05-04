package org.raghuvir.hms.services;

import java.util.List;
import java.util.Set;
import org.raghuvir.hms.beans.ChiefComplaintBEAN;
import org.raghuvir.hms.beans.HmsUserBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.dtos.PatientInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagePatientService {

    List<PatientBEAN> serchPatient(String query,Pageable pageable);
    
    Page<PatientBEAN> getPatientList(Pageable pageable);

    Set<HmsUserBEAN> getPatientListForDoctor(String did);

    void addPrescription(long caseid, String prescription);
    
    PatientInfoDTO getPatientInfo(String pid);
    
    List<String> getDoctorIds();
    
    long getTotalPatients();

    void addChiefComplaint(ChiefComplaintDTO chiefcomplaint);
    
    void addRoomEntry(String pid,String roomno);
}
