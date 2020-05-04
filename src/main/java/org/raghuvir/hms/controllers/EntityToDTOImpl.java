package org.raghuvir.hms.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.raghuvir.hms.beans.ChiefComplaintBEAN;
import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.beans.RoomEntryBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.dtos.DoctorInfoDTO;
import org.raghuvir.hms.dtos.PatientInfoDTO;
import org.raghuvir.hms.dtos.RoomEntryDTO;
import org.raghuvir.hms.dtos.StaffInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityToDTOImpl implements EntityToDTO {

	@Override
	public PatientInfoDTO convertToPatientInfoDTO(PatientBEAN patient, List<String> vips, List<String> regulars,
			List<String> generals) {
		Set<ChiefComplaintBEAN> complaints = patient.getChiefcomplaint();
		List<ChiefComplaintDTO> complaints1 = new LinkedList<>();
		List<RoomEntryBEAN> entries = patient.getRoomentries();
		List<RoomEntryDTO> entries1 = new LinkedList<>();
		
		complaints.forEach((complaint) -> complaints1.add((new ChiefComplaintDTO()).copy(complaint)));
		entries.forEach((entry) -> entries1.add((new RoomEntryDTO()).copy(entry)));
		patient.setChiefcomplaint(null);
		patient.setRoomentries(null);
		return new PatientInfoDTO(patient, complaints1, entries1, vips, regulars, generals);
	}

	@Override
	public DoctorInfoDTO convertToDoctorInfoDTO(DoctorBEAN doc) {		
		return new DoctorInfoDTO().copy(doc);
	}

	@Override
	public StaffInfoDTO convertToStaffInfoDTO(StaffBEAN staff) {
		return new StaffInfoDTO().copy(staff);
	}

}
