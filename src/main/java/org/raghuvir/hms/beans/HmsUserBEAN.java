package org.raghuvir.hms.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.raghuvir.hms.utils.Copiable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "hmsuser", catalog = "ram")
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@NoArgsConstructor
@ToString
@Indexed
public class HmsUserBEAN implements Serializable, Copiable<HmsUserBEAN> {

	@Id
	@Column(name = "userid")
	@Field
	String userId;	
	
	@Column(name = "uname")
	@Field
	String name;
	
	@Field
	@Column(name = "imageurl")
	String imgurl;
	
	@Field
	@Column(name = "phno")
	String phoneno;
	
	@Field
	@Column(name = "usertype")
	String usertype;
	
	@Field
	@Column(name = "address")
	String address;
	
	@Field
	@Column(name = "gender")
	String gender;
	
	@Field
	@Column(name = "emailid")
	String emailaddress;
	
	@Field(store=Store.NO,analyze=Analyze.NO)
	@DateBridge(resolution = Resolution.DAY)
//	@FieldBridge(impl = org.raghuvir.hms.search.DateSplitBridge.class)
	@Column(name = "birthdate")
	@Temporal(TemporalType.DATE)
	Date birthdate;

	public HmsUserBEAN(String userId, String name, String imgurl, String phoneno, String usertype, String address,
			String gender, String emailaddress, Date birthdate) {
		this.userId = userId;
		this.name = name;
		this.imgurl = imgurl;
		this.phoneno = phoneno;
		this.usertype = usertype;
		this.address = address;
		this.gender = gender;
		this.emailaddress = emailaddress;
		this.birthdate = birthdate;
	}

	public HmsUserBEAN copy(HmsUserBEAN entity) {
		this.address = entity.getAddress();
		this.birthdate = entity.getBirthdate();
		this.gender = entity.getGender();
		this.imgurl = entity.getImgurl();
		this.name = entity.getName();
		this.userId = entity.getUserId();
		this.usertype = entity.getUsertype();
		this.emailaddress = entity.getEmailaddress();
		this.phoneno = entity.getPhoneno();
		return this;
	}
}
