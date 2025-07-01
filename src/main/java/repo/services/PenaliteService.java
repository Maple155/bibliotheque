package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Penalite;
import repo.repositories.PenaliteRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PenaliteService {
    private final PenaliteRepository repo;

    @Autowired
    public PenaliteService(PenaliteRepository repo) {
        this.repo = repo;
    }

    public Penalite create(Penalite object) {
        return repo.save(object);
    }

    public List<Penalite> read() {
        return repo.findAll();
    }

    public Optional<Penalite> readById(int id) {
        return repo.findById(id);
    }

    public Penalite update(int id, Penalite object) {
        Optional<Penalite> optional = repo.findById(id);
        if (optional.isPresent()) {
            Penalite existing = optional.get();
            existing.setPret(object.getPret());
            existing.setDate(object.getDate());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}