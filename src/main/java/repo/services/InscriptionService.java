package repo.services;

import repo.models.Inscription;
import repo.repositories.InscriptionRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    @Transactional(readOnly = true)
    public List<Inscription> getAll() {
        return inscriptionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Inscription> getInscriptionById(int id) {
        return inscriptionRepository.findById(id);
    }

    public void delete(int id) {
        inscriptionRepository.deleteById(id);
    }
}