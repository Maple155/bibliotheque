package repo.services;

import repo.models.TypeAdherant;
import repo.repositories.TypeAdherantRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeAdherantService {

    private final TypeAdherantRepository typeAdherantRepository;

    public TypeAdherantService(TypeAdherantRepository typeAdherantRepository) {
        this.typeAdherantRepository = typeAdherantRepository;
    }

    public TypeAdherant save(TypeAdherant typeAdherant) {
        return typeAdherantRepository.save(typeAdherant);
    }

    @Transactional(readOnly = true)
    public List<TypeAdherant> getAll() {
        return typeAdherantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<TypeAdherant> getTypeAdherantById(int id) {
        return typeAdherantRepository.findById(id);
    }

    public void delete(int id) {
        typeAdherantRepository.deleteById(id);
    }
}