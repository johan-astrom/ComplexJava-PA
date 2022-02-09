package se.astrom.complexjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.astrom.complexjava.entity.Microsoft365License;

@Repository
public interface Microsoft365LicenseRepository extends CrudRepository<Microsoft365License, Long> {
}
