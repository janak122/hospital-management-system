package org.raghuvir.hms.dtos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.raghuvir.hms.beans.HmsUserBEAN;
import org.raghuvir.hms.beans.StaffBEAN;
import org.raghuvir.hms.utils.Copiable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class StaffInfoDTO implements Copiable<StaffBEAN>{

	private HmsUserBEAN user;
	private String job;
	private int salary;
	private Date dateofjoin, arrivaltime, departtime;

	public StaffInfoDTO(HmsUserBEAN user, String job, int salary, Date dateofjoin, Date arrivaltime, Date departtime) {
		this.user = user;
		this.job = job;
		this.salary = salary;
		this.dateofjoin = dateofjoin;
		this.arrivaltime = arrivaltime;
		this.departtime = departtime;
	}

	public long getAge() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
		String bdate = fmt.format(getUser().getBirthdate());
		long dob = Integer.parseInt(bdate.substring(0, 4));
		long curry = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
		return curry - dob;
	}

	public StaffInfoDTO copy(StaffBEAN entity) {
		this.arrivaltime=entity.getArrivaltime();
		this.dateofjoin=entity.getDateofjoin();
		this.departtime=entity.getDeparttime();
		this.job=entity.getJob();
		this.salary=entity.getSalary();
		this.user=(new HmsUserBEAN()).copy(entity);
		return this;
	}
}
