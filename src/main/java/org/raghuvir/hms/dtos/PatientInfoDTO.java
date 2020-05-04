
package org.raghuvir.hms.dtos;

import java.text.SimpleDateFormat;
import java.util.List;
import org.raghuvir.hms.beans.HmsUserBEAN;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PatientInfoDTO {
	private final HmsUserBEAN user;
	private final List<ChiefComplaintDTO> complaint;
	private final List<RoomEntryDTO> entries;
	private final List<String> vipRooms,regularRooms,generalRooms;
	

	public int getAge() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
		String bdate = fmt.format(user.getBirthdate());
		int dob = Integer.parseInt(bdate.substring(0, 4));
		int curry = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
		return curry - dob;
	}

}
