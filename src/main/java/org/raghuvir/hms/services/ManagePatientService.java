package org.raghuvir.hms.services;

import java.util.List;
import java.util.Set;
import org.raghuvir.hms.beans.ChiefComplaintBEAN;
import org.raghuvir.hms.beans.HmsUserBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.dtos.PatientInfoDTO;

public interface ManagePatientService {

    List<PatientBEAN> serchPatient(String query);
    
    List<PatientBEAN> getPatientList(int pageno);

    Set<HmsUserBEAN> getPatientListForDoctor(String did);

    void addPrescription(long caseid, String prescription);
    
    PatientInfoDTO getPatientInfo(String pid);
    
    Set<String> getDoctorIds();
    
    long getTotalPatients();

    void addChiefComplaint(ChiefComplaintDTO chiefcomplaint);
    
    void addRoomEntry(String pid,String roomno);
}
