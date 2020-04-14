
package org.raghuvir.hms.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "staff", catalog = "ram")
@PrimaryKeyJoinColumn(name = "staffid")
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Indexed
public class StaffBEAN extends HmsUserBEAN implements Serializable {
	@Column(name = "salary")
	@Field
	int salary;
	
	@Field
	@DateBridge(resolution = Resolution.DAY)
	@Column(name = "dateofjoin")
	@Temporal(javax.persistence.TemporalType.DATE)
	Date dateofjoin;
	
	@Field
	@DateBridge(resolution = Resolution.SECOND)
	@Column(name = "arrivaltime")
	@Temporal(TemporalType.TIMESTAMP)
	Date arrivaltime;
	
	@Field
	@DateBridge(resolution = Resolution.SECOND)
	@Column(name = "departtime")
	@Temporal(TemporalType.TIMESTAMP)
	Date departtime;
	
	@Field
	@Column(name = "job")
	String job;

	

	public StaffBEAN(int salary, Date dateofjoin, Date arrivaltime, Date departtime, String job, String userId,
			String name, String imgurl, String phoneno, String usertype, String address, String gender,
			String emailaddress, Date birthdate) {
		super(userId, name, imgurl, phoneno, usertype, address, gender, emailaddress, birthdate);
		this.salary = salary;
		this.dateofjoin = dateofjoin;
		this.arrivaltime = arrivaltime;
		this.departtime = departtime;
		this.job = job;
	}

	public void copy(StaffBEAN entity) {
		super.copy(entity);
		this.salary = entity.salary;
		this.job = entity.job;
		this.dateofjoin = entity.dateofjoin;
		this.arrivaltime = entity.arrivaltime;
		this.departtime = entity.departtime;
	}
}
