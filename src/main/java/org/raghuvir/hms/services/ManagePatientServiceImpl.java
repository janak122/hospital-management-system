package org.raghuvir.hms.services;

import java.util.List;

import java.util.Set;

import org.raghuvir.hms.beans.HmsUserBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.daos.GeneralDAO;
import org.raghuvir.hms.daos.GeneralDAOImpl;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.daos.ManagePatientDAOImpl;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.dtos.PatientInfoDTO;
import org.springframework.stereotype.Service;

@Service("patientservice")
public class ManagePatientServiceImpl implements ManagePatientService{

	ManagePatientDAO instance=ManagePatientDAOImpl.getInstance();
	
	@Override
	public List<PatientBEAN> serchPatient(String query) {
		return instance.serchPatient(query);
	}

	@Override
	public List<PatientBEAN> getPatientList(int pageno) {
		return instance.getPatientList(pageno);
	}

	@Override
	public Set<HmsUserBEAN> getPatientListForDoctor(String did) {
		return instance.getPatientListForDoctor(did);
	}

	@Override
	public void addPrescription(long caseid, String prescription) {
		instance.addPrescription(caseid, prescription);
	}

	@Override
	public PatientInfoDTO getPatientInfo(String pid) {
		return instance.getPatientInfo(pid);
	}

	@Override
	public Set<String> getDoctorIds() {
		return instance.getDoctorIds();
	}

	@Override
	public long getTotalPatients() {
		return instance.getTotalPatients();
	}

	@Override
	public void addChiefComplaint(ChiefComplaintDTO chiefcomplaint) {
		instance.addChiefComplaint(chiefcomplaint);
	}

	@Override
	public void addRoomEntry(String pid, String roomno) {
		instance.addRoomEntry(pid, roomno);
	}

}
