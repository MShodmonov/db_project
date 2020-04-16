package dreamteam.db_project.service;

import dreamteam.db_project.model.Management;
import dreamteam.db_project.model.Stuffs;
import dreamteam.db_project.repository.StuffRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StuffService {


   private final StuffRepo stuffRepo;

    public StuffService(StuffRepo stuffRepo) {
        this.stuffRepo = stuffRepo;
    }

    public List<Stuffs> findStuffsByName(String fullName)
    {
        return stuffRepo.findAllByFullNameContaining(fullName);
    }
    public Stuffs findStuffsByUsername(String username)
    {
        Optional<Stuffs> optionalStuffs = stuffRepo.findByUsername(username);
        return optionalStuffs.orElseGet(Stuffs::new);
    }
}
