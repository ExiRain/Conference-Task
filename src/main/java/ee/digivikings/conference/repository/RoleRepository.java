package ee.digivikings.conference.repository;

import ee.digivikings.conference.domain.Role;
import ee.digivikings.conference.domain.enumeration.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link Role} entity.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
