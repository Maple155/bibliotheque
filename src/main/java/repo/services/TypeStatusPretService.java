package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.TypeStatusPret;
import repo.repositories.TypeStatusPretRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TypeStatusPretService {
    private final TypeStatusPretRepository repo;

    @Autowired
    public TypeStatusPretService(TypeStatusPretRepository repo) {
        this.repo = repo;
    }

    public TypeStatusPret create(TypeStatusPret object) {
        return repo.save(object);
    }

    public List<TypeStatusPret> read() {
        return repo.findAll();
    }

    public Optional<TypeStatusPret> readById(int id) {
        return repo.findById(id);
    }

    public TypeStatusPret update(int id, TypeStatusPret object) {
        Optional<TypeStatusPret> optional = repo.findById(id);
        if (optional.isPresent()) {
            TypeStatusPret existing = optional.get();
            existing.setType(object.getType());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}