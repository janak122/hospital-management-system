package org.raghuvir.hms.repositories;
import org.raghuvir.hms.beans.HmsUserBEAN;
import org.springframework.data.repository.CrudRepository;

public interface HmsUserRepository  extends CrudRepository<HmsUserBEAN, String>  {

}
