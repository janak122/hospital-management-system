package org.raghuvir.hms.repositories;

import java.util.List;

import org.raghuvir.hms.beans.PatientBEAN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends JpaRepository<PatientBEAN, String>{

}
