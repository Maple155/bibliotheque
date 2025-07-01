package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.StatusProlongement;
import repo.repositories.StatusProlongementRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StatusProlongementService {
    private final StatusProlongementRepository repo;

    @Autowired
    public StatusProlongementService(StatusProlongementRepository repo) {
        this.repo = repo;
    }

    public StatusProlongement create(StatusProlongement object) {
        return repo.save(object);
    }

    public List<StatusProlongement> read() {
        return repo.findAll();
    }

    public Optional<StatusProlongement> readById(int id) {
        return repo.findById(id);
    }

    public StatusProlongement update(int id, StatusProlongement object) {
        Optional<StatusProlongement> optional = repo.findById(id);
        if (optional.isPresent()) {
            StatusProlongement existing = optional.get();
            existing.setProlongementPret(object.getProlongementPret());
            existing.setTypeStatusPret(object.getTypeStatusPret());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}