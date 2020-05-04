package org.raghuvir.hms.repositories;

import org.raghuvir.hms.beans.StaffBEAN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<StaffBEAN, String> {
	Page<StaffBEAN> findByJobNotIgnoreCase(String job,Pageable pageable);
}
