package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Pret;
import repo.repositories.PretRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {
    private final PretRepository repo;

    @Autowired
    public PretService(PretRepository repo) {
        this.repo = repo;
    }

    public Pret create(Pret object) {
        return repo.save(object);
    }

    public List<Pret> read() {
        return repo.findAll();
    }

    public Optional<Pret> readById(int id) {
        return repo.findById(id);
    }

    public Pret update(int id, Pret object) {
        Optional<Pret> optional = repo.findById(id);
        if (optional.isPresent()) {
            Pret existing = optional.get();
            existing.setAdherant(object.getAdherant());
            existing.setExemplaire(object.getExemplaire());
            existing.setTypePret(object.getTypePret());
            existing.setDateDebut(object.getDateDebut());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}