package org.raghuvir.hms.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "room_entry", catalog = "ram")
@Setter
@Getter
@NoArgsConstructor
@ToString
@Indexed
public class RoomEntryBEAN implements Serializable {

	@Id
	@DocumentId
	@Column(name = "entryid")
	long EntryId;
	
	@IndexedEmbedded(depth = 1)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientid")
	PatientBEAN patient;
	
	@IndexedEmbedded(depth = 1)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roomno")
	RoomBEAN room;
	
	@Field
	@DateBridge(resolution = Resolution.DAY)
	@Column(name = "arrivaldate")
	@Temporal(TemporalType.DATE)
	Date arrivaldate;

	public RoomEntryBEAN(long EntryId) {
		this.EntryId = EntryId;
	}

	public RoomEntryBEAN(long EntryId, PatientBEAN patient, RoomBEAN room, Date arrivaldate) {
		this.EntryId = EntryId;
		this.patient = patient;
		this.room = room;
		this.arrivaldate = arrivaldate;
	}

}
