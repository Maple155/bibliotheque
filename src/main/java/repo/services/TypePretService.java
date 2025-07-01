package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.TypePret;
import repo.repositories.TypePretRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TypePretService {
    private final TypePretRepository repo;

    @Autowired
    public TypePretService(TypePretRepository repo) {
        this.repo = repo;
    }

    public TypePret create(TypePret object) {
        return repo.save(object);
    }

    public List<TypePret> read() {
        return repo.findAll();
    }

    public Optional<TypePret> readById(int id) {
        return repo.findById(id);
    }

    public TypePret update(int id, TypePret object) {
        Optional<TypePret> optional = repo.findById(id);
        if (optional.isPresent()) {
            TypePret existing = optional.get();
            existing.setType(object.getType());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}