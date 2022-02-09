package se.astrom.complexjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.astrom.complexjava.entity.AzureGroup;
import se.astrom.complexjava.entity.AzureUser;

@Repository
public interface AzureUserRepository extends CrudRepository<AzureUser, String> {
}
