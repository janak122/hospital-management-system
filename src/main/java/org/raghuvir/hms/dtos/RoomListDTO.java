
package org.raghuvir.hms.dtos;

import java.util.Date;

import org.raghuvir.hms.utils.EntitiesConstants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RoomListDTO {
	private final String roomno;
	private final String typeofroom;
	private final int occupied;
	
	public int getAvailable() {
		return EntitiesConstants.getTotalBEDS(typeofroom) - occupied;
	}
}
