package repo.services;

import repo.models.ProlongementPret;
import repo.repositories.ProlongementPretRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProlongementPretService {

    private final ProlongementPretRepository prolongementPretRepository;

    public ProlongementPretService(ProlongementPretRepository prolongementPretRepository) {
        this.prolongementPretRepository = prolongementPretRepository;
    }

    public ProlongementPret save(ProlongementPret prolongementPret) {
        return prolongementPretRepository.save(prolongementPret);
    }

    @Transactional(readOnly = true)
    public List<ProlongementPret> getAll() {
        return prolongementPretRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProlongementPret> getProlongementPretById(int id) {
        return prolongementPretRepository.findById(id);
    }

    public void delete(int id) {
        prolongementPretRepository.deleteById(id);
    }
}