
package org.raghuvir.hms.dtos;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.raghuvir.hms.beans.RoomBEAN;
import org.raghuvir.hms.beans.RoomEntryBEAN;
import org.raghuvir.hms.utils.Copiable;
import org.raghuvir.hms.utils.EntitiesConstants;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RoomInfoDTO implements Copiable<RoomBEAN> {
	

	private String roomno;
	private String typeofroom;
	private List<RoomEntryDTO> entries= new LinkedList<>();

	public RoomInfoDTO copy(RoomBEAN entity) {
		this.roomno = entity.getRoomno();
		this.typeofroom = entity.getTypeofroom();
		for (RoomEntryBEAN entry : entity.getRoomentries()) {
			RoomEntryDTO roomentry = new RoomEntryDTO();
			roomentry.copy(entry);
			this.entries.add(roomentry);
		}
		return this;
	}

	public int getAvailable() {
		return EntitiesConstants.getTotalBEDS(roomno) - entries.size();
	}

	public int getPricePerBed() {
		return EntitiesConstants.getPricePerBed(typeofroom);
	}
}
