package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.DureeInscription;
import repo.repositories.DureeInscriptionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DureeInscriptionService {
    private final DureeInscriptionRepository repo;

    @Autowired
    public DureeInscriptionService(DureeInscriptionRepository repo) {
        this.repo = repo;
    }

    public DureeInscription create(DureeInscription object) {
        return repo.save(object);
    }

    public List<DureeInscription> read() {
        return repo.findAll();
    }

    public Optional<DureeInscription> readById(int id) {
        return repo.findById(id);
    }

    public DureeInscription update(int id, DureeInscription object) {
        Optional<DureeInscription> optional = repo.findById(id);
        if (optional.isPresent()) {
            DureeInscription existing = optional.get();
            existing.setTypeAdherant(object.getTypeAdherant());
            existing.setDuree(object.getDuree());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}