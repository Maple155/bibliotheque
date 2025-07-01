package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.TypeAdherant;
import repo.repositories.TypeAdherantRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TypeAdherantService {
    private final TypeAdherantRepository repo;

    @Autowired
    public TypeAdherantService(TypeAdherantRepository repo) {
        this.repo = repo;
    }

    public TypeAdherant create(TypeAdherant object) {
        return repo.save(object);
    }

    public List<TypeAdherant> read() {
        return repo.findAll();
    }

    public Optional<TypeAdherant> readById(int id) {
        return repo.findById(id);
    }

    public TypeAdherant update(int id, TypeAdherant object) {
        Optional<TypeAdherant> optional = repo.findById(id);
        if (optional.isPresent()) {
            TypeAdherant existing = optional.get();
            existing.setType(object.getType());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}