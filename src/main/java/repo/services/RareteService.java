package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Rarete;
import repo.repositories.RareteRepository;
import java.util.List;
import java.util.Optional;

@Service
public class RareteService {
    private final RareteRepository repo;

    @Autowired
    public RareteService(RareteRepository repo) {
        this.repo = repo;
    }

    public Rarete create(Rarete object) {
        return repo.save(object);
    }

    public List<Rarete> read() {
        return repo.findAll();
    }

    public Optional<Rarete> readById(int id) {
        return repo.findById(id);
    }

    public Rarete update(int id, Rarete object) {
        Optional<Rarete> optional = repo.findById(id);
        if (optional.isPresent()) {
            Rarete existing = optional.get();
            existing.setType(object.getType());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}