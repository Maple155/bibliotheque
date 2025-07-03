package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.RetourPret;
import repo.repositories.RetourPretRepository;
import java.util.List;
import java.util.Optional;

@Service
public class RetourPretService {
    private final RetourPretRepository repo;

    @Autowired
    public RetourPretService(RetourPretRepository repo) {
        this.repo = repo;
    }

    public RetourPret create(RetourPret object) {
        return repo.save(object);
    }

    public List<RetourPret> read() {
        return repo.findAll();
    }

    public Optional<RetourPret> readById(int id) {
        return repo.findById(id);
    }

    public RetourPret update(int id, RetourPret object) {
        Optional<RetourPret> optional = repo.findById(id);
        if (optional.isPresent()) {
            RetourPret existing = optional.get();
            existing.setPret(object.getPret());
            existing.setDateRetour(object.getDateRetour());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public RetourPret readByPret (int id_pret) {
        return repo.findByPretId(id_pret);
    }
}