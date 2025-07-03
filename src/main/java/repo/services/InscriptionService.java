package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Inscription;
import repo.repositories.InscriptionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {
    private final InscriptionRepository repo;

    @Autowired
    public InscriptionService(InscriptionRepository repo) {
        this.repo = repo;
    }

    public Inscription create(Inscription object) {
        return repo.save(object);
    }

    public List<Inscription> read() {
        return repo.findAll();
    }

    public Optional<Inscription> readById(int id) {
        return repo.findById(id);
    }

    public Inscription update(int id, Inscription object) {
        Optional<Inscription> optional = repo.findById(id);
        if (optional.isPresent()) {
            Inscription existing = optional.get();
            existing.setAdherant(object.getAdherant());
            existing.setDateDebut(object.getDateDebut());
            existing.setDatefin(object.getDatefin());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public Inscription getCurrentInscription (int idAdherant) {
        return repo.getCurrentInscription(idAdherant);
    }
}