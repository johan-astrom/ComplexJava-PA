package se.astrom.complexjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.astrom.complexjava.entity.LicenseOption;
import se.astrom.complexjava.entity.Microsoft365License;

@Repository
public interface LicenseOptionRepository extends CrudRepository<LicenseOption, Long> {

}
