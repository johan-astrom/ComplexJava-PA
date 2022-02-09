package se.astrom.complexjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.astrom.complexjava.entity.ApplicationRole;
import se.astrom.complexjava.entity.ApplicationUser;

@Repository
public interface ApplicationRoleRepository extends CrudRepository<ApplicationRole, Long> {
    ApplicationRole findByRole(String role);
}
