package org.raghuvir.hms.beans;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctor", catalog = "ram")
@PrimaryKeyJoinColumn(name = "doctorid")
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Indexed
public class DoctorBEAN extends StaffBEAN {
	@Field
	@Column(name = "degrees")
	String degrees;
	@Field
	@Column(name = "typeofdoctor")
	String typeofdoctor;
	@ContainedIn
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	Set<ChiefComplaintBEAN> chiefcomplaint;



	public DoctorBEAN(String degrees, String typeofdoctor, Set<ChiefComplaintBEAN> chiefcomplaint, int salary,
			Date dateofjoin, Date arrivaltime, Date departtime, String job, String userId, String name, String imgurl,
			String phoneno, String usertype, String address, String gender, String emailaddress, Date birthdate) {
		super(salary, dateofjoin, arrivaltime, departtime, job, userId, name, imgurl, phoneno, usertype, address,
				gender, emailaddress, birthdate);
		this.degrees = degrees;
		this.typeofdoctor = typeofdoctor;
		this.chiefcomplaint = chiefcomplaint;
	}

	public void copy(DoctorBEAN entity) {
		super.copy(entity);
		this.degrees = entity.degrees;
		this.typeofdoctor = entity.typeofdoctor;
	}

}
