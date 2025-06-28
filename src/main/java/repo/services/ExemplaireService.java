package repo.services;

import repo.models.Exemplaire;
import repo.repositories.ExemplaireRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {

    private final ExemplaireRepository exemplaireRepository;

    public ExemplaireService(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    public Exemplaire save(Exemplaire exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    @Transactional(readOnly = true)
    public List<Exemplaire> getAll() {
        return exemplaireRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Exemplaire> getExemplaireById(int id) {
        return exemplaireRepository.findById(id);
    }

    public void delete(int id) {
        exemplaireRepository.deleteById(id);
    }
}