package se.astrom.complexjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.astrom.complexjava.entity.ApplicationRole;
import se.astrom.complexjava.entity.ApplicationUser;

import java.util.Optional;

@Repository
public interface ApplicationRoleRepository extends CrudRepository<ApplicationRole, Long> {
    Optional<ApplicationRole> findByRole(String role);
}
