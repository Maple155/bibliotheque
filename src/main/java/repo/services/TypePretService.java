package repo.services;

import repo.models.TypePret;
import repo.repositories.TypePretRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePretService {

    private final TypePretRepository typePretRepository;

    public TypePretService(TypePretRepository typePretRepository) {
        this.typePretRepository = typePretRepository;
    }

    public TypePret save(TypePret typePret) {
        return typePretRepository.save(typePret);
    }

    @Transactional(readOnly = true)
    public List<TypePret> getAll() {
        return typePretRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<TypePret> getTypePretById(int id) {
        return typePretRepository.findById(id);
    }

    public void delete(int id) {
        typePretRepository.deleteById(id);
    }
}