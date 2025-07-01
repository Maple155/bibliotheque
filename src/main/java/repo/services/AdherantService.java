package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Adherant;
import repo.repositories.AdherantRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AdherantService {
    private final AdherantRepository repo;

    @Autowired
    public AdherantService(AdherantRepository repo) {
        this.repo = repo;
    }

    public Adherant create(Adherant object) {
        return repo.save(object);
    }

    public List<Adherant> read() {
        return repo.findAll();
    }

    public Optional<Adherant> readById(int id) {
        return repo.findById(id);
    }

    public Adherant update(int id, Adherant object) {
        Optional<Adherant> optional = repo.findById(id);
        if (optional.isPresent()) {
            Adherant existing = optional.get();
            existing.setTypeAdherant(object.getTypeAdherant());
            existing.setNom(object.getNom());
            existing.setPrenom(object.getPrenom());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public Adherant findAdherant(String nom, String prenom) {
        return repo.findAdherant(nom, prenom);
    }
}