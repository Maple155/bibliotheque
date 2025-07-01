package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.StatusPret;
import repo.repositories.StatusPretRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StatusPretService {
    private final StatusPretRepository repo;

    @Autowired
    public StatusPretService(StatusPretRepository repo) {
        this.repo = repo;
    }

    public StatusPret create(StatusPret object) {
        return repo.save(object);
    }

    public List<StatusPret> read() {
        return repo.findAll();
    }

    public Optional<StatusPret> readById(int id) {
        return repo.findById(id);
    }

    public StatusPret update(int id, StatusPret object) {
        Optional<StatusPret> optional = repo.findById(id);
        if (optional.isPresent()) {
            StatusPret existing = optional.get();
            existing.setPret(object.getPret());
            existing.setTypeStatusPret(object.getTypeStatusPret());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}