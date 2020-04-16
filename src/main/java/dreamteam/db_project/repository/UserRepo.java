package dreamteam.db_project.repository;

import dreamteam.db_project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {
    Optional<Users>findByUsername(String username);
}
