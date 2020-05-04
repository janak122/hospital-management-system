package org.raghuvir.hms.repositories;

import java.util.List;

import org.raghuvir.hms.beans.RoomBEAN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<RoomBEAN, String>  {
	
	@Query("select rm.roomno from RoomBEAN rm "
			+ " where lower(rm.typeofroom) = lower(?1) and size(rm.roomentries) < ?2")
	List<String> findRoomno(String type,int maxsize);
}
