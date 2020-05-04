package org.raghuvir.hms.repositories;

import java.util.List;

import org.raghuvir.hms.beans.DoctorBEAN;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DoctorRepository extends PagingAndSortingRepository<DoctorBEAN, String>{
	@Query("select doc.userId from #{#entityName} doc")
	List<String> getAllDoctorIds();
}
