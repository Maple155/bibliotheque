package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.TypeStatusInscription;
import repo.repositories.TypeStatusInscriptionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TypeStatusInscriptionService {
    private final TypeStatusInscriptionRepository repo;

    @Autowired
    public TypeStatusInscriptionService(TypeStatusInscriptionRepository repo) {
        this.repo = repo;
    }

    public TypeStatusInscription create(TypeStatusInscription object) {
        return repo.save(object);
    }

    public List<TypeStatusInscription> read() {
        return repo.findAll();
    }

    public Optional<TypeStatusInscription> readById(int id) {
        return repo.findById(id);
    }

    public TypeStatusInscription update(int id, TypeStatusInscription object) {
        Optional<TypeStatusInscription> optional = repo.findById(id);
        if (optional.isPresent()) {
            TypeStatusInscription existing = optional.get();
            existing.setType(object.getType());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}