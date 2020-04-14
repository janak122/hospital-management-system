package org.raghuvir.hms.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.raghuvir.hms.dtos.ChiefComplaintDTO;
import org.raghuvir.hms.utils.Copiable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "chiefcomplaint", catalog = "ram")
@Setter
@Getter
@NoArgsConstructor
@ToString
@Indexed
public class ChiefComplaintBEAN implements Serializable, Copiable<ChiefComplaintDTO> {

	@Id
	@Column(name = "caseid")
	long caseid;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patientid")
	@ToString.Exclude
	@IndexedEmbedded(depth = 1)
	PatientBEAN patient;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorid")
	@ToString.Exclude
	@IndexedEmbedded(depth = 1)
	DoctorBEAN doctor;

	@Column(name = "creationdate")
	@Temporal(javax.persistence.TemporalType.DATE)
	@Field
	@DateBridge(resolution = Resolution.DAY)
	Date creationdate;
	
	@Field
	@Column(name = "chief_complaint")
	String chief_complaint;
	@Field
	@Column(name = "description")
	String description;
	@Field
	@Column(name = "prescription")
	String prescription;
	@Field
	@Column(name = "status")
	String status;

	public ChiefComplaintBEAN(long caseid, PatientBEAN patient, DoctorBEAN doctor, Date creationdate,
			String chief_complaint, String description, String prescription, String status) {
		this.caseid = caseid;
		this.patient = patient;
		this.doctor = doctor;
		this.creationdate = creationdate;
		this.chief_complaint = chief_complaint;
		this.description = description;
		this.prescription = prescription;
		this.status = status;
	}

	public ChiefComplaintBEAN copy(ChiefComplaintDTO entity) {
		this.caseid = entity.getCaseid();
		this.chief_complaint = entity.getChief_complaint();
		this.creationdate = entity.getCreationdate();
		this.description = entity.getDescription();
		this.prescription = entity.getPrescription();
		this.status = entity.getStatus();
		return this;
	}

}
