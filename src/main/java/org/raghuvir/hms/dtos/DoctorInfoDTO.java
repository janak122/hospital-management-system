package org.raghuvir.hms.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.raghuvir.hms.beans.DoctorBEAN;
import org.raghuvir.hms.beans.HmsUserBEAN;
import org.raghuvir.hms.utils.Copiable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class DoctorInfoDTO extends StaffInfoDTO {

	private String degrees;
	private String typeofdoctor;

	public DoctorInfoDTO(String degrees, String typeofdoctor, HmsUserBEAN user, String job, int salary, Date dateofjoin,
			Date arrivaltime, Date departtime) {
		super(user, job, salary, dateofjoin, arrivaltime, departtime);
		this.degrees = degrees;
		this.typeofdoctor = typeofdoctor;
	}

	public DoctorInfoDTO copy(DoctorBEAN entity) {
		this.degrees=entity.getDegrees();
		this.typeofdoctor=entity.getTypeofdoctor();
		super.copy(entity);
		return this;
	}
}
