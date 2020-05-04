package org.raghuvir.hms.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.raghuvir.hms.beans.HmsUserBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.controllers.EntityToDTO;
import org.raghuvir.hms.daos.GeneralDAO;
import org.raghuvir.hms.daos.GeneralDAOImpl;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.daos.ManagePatientDAOImpl;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.dtos.PatientInfoDTO;
import org.raghuvir.hms.repositories.DoctorRepository;
import org.raghuvir.hms.repositories.HmsUserRepository;
import org.raghuvir.hms.repositories.PatientRepository;
import org.raghuvir.hms.repositories.RoomRepository;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("patientservice")
public class ManagePatientServiceImpl implements ManagePatientService{
	
	ManagePatientDAO instance=ManagePatientDAOImpl.getInstance();
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	EntityToDTO entityToDTO;
	
	@Override
	public List<PatientBEAN> serchPatient(String query,Pageable pageable) {
		return instance.serchPatient(query,pageable.getPageNumber()*pageable.getPageSize()
				,pageable.getPageSize());
	}

	@Override
	public Page<PatientBEAN> getPatientList(Pageable pageable) {
		return patientRepository.findAll(pageable);
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
	@Transactional
	public PatientInfoDTO getPatientInfo(String pid) {			
		return entityToDTO.convertToPatientInfoDTO(patientRepository.findById(pid).get(),
				roomRepository.findRoomno("vip", EntitiesConstants.VIP_TOTALBEDS)
				, roomRepository.findRoomno("regular", EntitiesConstants.REGULAR_TOTALBEDS)
				, roomRepository.findRoomno("general", EntitiesConstants.GENERAL_TOTALBEDS));
//		return instance.getPatientInfo(pid);
	}

	@Override
	public List<String> getDoctorIds() {
		return doctorRepository.getAllDoctorIds();
//		return instance.getDoctorIds();
	}

	@Override
	public long getTotalPatients() {
		return patientRepository.count();
//		return instance.getTotalPatients();
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
