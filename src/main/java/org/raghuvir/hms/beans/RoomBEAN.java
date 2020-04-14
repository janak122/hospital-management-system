package org.raghuvir.hms.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.raghuvir.hms.utils.EntitiesConstants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "rooms", catalog = "ram")
@Setter
@Getter
@NoArgsConstructor
@ToString
@Indexed
public class RoomBEAN implements Serializable {

	@Id
	@Column(name = "roomno")
	@Field
	String roomno;
	
	@Field
	@Column(name = "typeofroom")
	String typeofroom;
	
	@ContainedIn
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "roomno")
	@ToString.Exclude
	List<RoomEntryBEAN> roomentries;

	public RoomBEAN(String roomno) {
		this.roomno = roomno;
	}

	public RoomBEAN(String roomno, String typeofroom, List<RoomEntryBEAN> roomentries) {
		this.roomno = roomno;
		this.typeofroom = typeofroom;
		this.roomentries = roomentries;
	}

}
