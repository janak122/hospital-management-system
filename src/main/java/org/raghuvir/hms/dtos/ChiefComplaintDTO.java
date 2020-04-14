
package org.raghuvir.hms.dtos;

import java.util.Date;

import org.raghuvir.hms.beans.ChiefComplaintBEAN;
import org.raghuvir.hms.beans.PatientBEAN;
import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.utils.Copiable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ChiefComplaintDTO implements Copiable<ChiefComplaintBEAN> {
	private long caseid;
	private Date creationdate;
	private String chief_complaint;
	private String doctorid;
	private String prescription;
	private String patientid;
	private String status;
	private String description;

	public ChiefComplaintDTO(long caseid, String patientid, String doctorid, Date creationdate, String chief_complaint,
			String description, String prescription, String status) {
		this.caseid = caseid;
		this.patientid = patientid;
		this.doctorid = doctorid;
		this.creationdate = creationdate;
		this.chief_complaint = chief_complaint;
		this.description = description;
		this.prescription = prescription;
		this.status = status;
	}

	public ChiefComplaintDTO copy(ChiefComplaintBEAN entity) {
		this.caseid=entity.getCaseid();
		this.chief_complaint=entity.getChief_complaint();
		this.creationdate=entity.getCreationdate();
		this.description=entity.getDescription();
		this.doctorid=entity.getDoctor().getUserId();
		this.patientid=entity.getPatient().getUserId();
		this.prescription=entity.getPrescription();
		this.status=entity.getStatus();
		return this;
	}

}
