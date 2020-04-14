
package org.raghuvir.hms.beans;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patient", catalog = "ram")
@PrimaryKeyJoinColumn(name = "patientid")
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Indexed
public class PatientBEAN extends HmsUserBEAN {
	@ContainedIn
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	Set<ChiefComplaintBEAN> chiefcomplaint;
	
	@ContainedIn
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	@ToString.Exclude
	List<RoomEntryBEAN> roomentries;


	public PatientBEAN(Set<ChiefComplaintBEAN> chiefcomplaint, String userId, String name, String imgurl,
			String phoneno, String usertype, String address, String gender, String emailaddress, Date birthdate) {
		super(userId, name, imgurl, phoneno, usertype, address, gender, emailaddress, birthdate);
		this.chiefcomplaint = chiefcomplaint;
	}

	public PatientBEAN(Set<ChiefComplaintBEAN> chiefcomplaint, List<RoomEntryBEAN> roomentries, String userId,
			String name, String imgurl, String phoneno, String usertype, String address, String gender,
			String emailaddress, Date birthdate) {
		super(userId, name, imgurl, phoneno, usertype, address, gender, emailaddress, birthdate);
		this.chiefcomplaint = chiefcomplaint;
		this.roomentries = roomentries;

	}

}
