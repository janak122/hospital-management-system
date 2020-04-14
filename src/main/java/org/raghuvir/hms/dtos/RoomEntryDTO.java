package org.raghuvir.hms.dtos;

import java.util.Date;

import org.raghuvir.hms.beans.RoomEntryBEAN;
import org.raghuvir.hms.utils.Copiable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RoomEntryDTO implements Copiable<RoomEntryBEAN> {
	long entryid;
	String patientId;
	Date arrivaldate;
	String roomno;
	public RoomEntryDTO copy(RoomEntryBEAN entity) {
		this.entryid = entity.getEntryId();
		this.patientId = entity.getPatient().getUserId();
		this.arrivaldate = entity.getArrivaldate();
		this.roomno=entity.getRoom().getRoomno();
		return this;
	}

}