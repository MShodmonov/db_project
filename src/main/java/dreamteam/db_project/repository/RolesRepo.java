package dreamteam.db_project.repository;

import dreamteam.db_project.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RolesRepo extends JpaRepository<Roles, UUID> {
    Optional<Roles> findByRole(String role);
}
