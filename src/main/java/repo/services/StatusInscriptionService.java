package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.StatusInscription;
import repo.repositories.StatusInscriptionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StatusInscriptionService {
    private final StatusInscriptionRepository repo;

    @Autowired
    public StatusInscriptionService(StatusInscriptionRepository repo) {
        this.repo = repo;
    }

    public StatusInscription create(StatusInscription object) {
        return repo.save(object);
    }

    public List<StatusInscription> read() {
        return repo.findAll();
    }

    public Optional<StatusInscription> readById(int id) {
        return repo.findById(id);
    }

    public StatusInscription update(int id, StatusInscription object) {
        Optional<StatusInscription> optional = repo.findById(id);
        if (optional.isPresent()) {
            StatusInscription existing = optional.get();
            existing.setTypeStatusInscription(object.getTypeStatusInscription());
            existing.setInscription(object.getInscription());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}