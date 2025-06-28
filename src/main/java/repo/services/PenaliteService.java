package repo.services;

import repo.models.Penalite;
import repo.repositories.PenaliteRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaliteService {

    private final PenaliteRepository penaliteRepository;

    public PenaliteService(PenaliteRepository penaliteRepository) {
        this.penaliteRepository = penaliteRepository;
    }

    public Penalite save(Penalite penalite) {
        return penaliteRepository.save(penalite);
    }

    @Transactional(readOnly = true)
    public List<Penalite> getAll() {
        return penaliteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Penalite> getPenaliteById(int id) {
        return penaliteRepository.findById(id);
    }

    public void delete(int id) {
        penaliteRepository.deleteById(id);
    }
}