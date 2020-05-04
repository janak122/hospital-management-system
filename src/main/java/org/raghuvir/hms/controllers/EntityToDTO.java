package org.raghuvir.hms.controllers;

import java.util.List;

import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.dtos.DoctorInfoDTO;
import org.raghuvir.hms.dtos.PatientInfoDTO;
import org.raghuvir.hms.dtos.StaffInfoDTO;

public interface EntityToDTO {
	PatientInfoDTO convertToPatientInfoDTO(PatientBEAN patient,List<String> vips,
			List<String> regulars,List<String> generals);
	
	DoctorInfoDTO convertToDoctorInfoDTO(DoctorBEAN doc);
	
	StaffInfoDTO convertToStaffInfoDTO(StaffBEAN staff);
}
