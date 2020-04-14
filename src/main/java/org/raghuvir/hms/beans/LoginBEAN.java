package org.raghuvir.hms.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "login", catalog = "ram")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class LoginBEAN implements Serializable {

	@Id
	@OneToOne
	@JoinColumn(name = "userid")
	HmsUserBEAN hmsuser;
	@Column(name = "password")
	String password;

	public LoginBEAN(HmsUserBEAN hmsuser, String password) {
		this.hmsuser = hmsuser;
		this.password = password;
	}

}
