package dreamteam.db_project.service;

import dreamteam.db_project.model.Management;
import dreamteam.db_project.repository.ManagementRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementService {
    final
    ManagementRepo managementRepo;

    public ManagementService(ManagementRepo managementRepo) {
        this.managementRepo = managementRepo;
    }

    public List<Management> findManagersByName(String fullName)
    {
        return managementRepo.findAllByFullNameContaining(fullName);
    }
    public Management findManagerByUsername(String username)
    {
        Optional<Management> optionalManagement = managementRepo.findByUsername(username);
        return optionalManagement.orElseGet(Management::new);
    }
}
