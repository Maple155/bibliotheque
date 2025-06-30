package repo.services;

import repo.models.Adherant;
import repo.repositories.AdherantRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherantService {

    private final AdherantRepository adherantRepository;

    public AdherantService(AdherantRepository adherantRepository) {
        this.adherantRepository = adherantRepository;
    }

    public Adherant save(Adherant adherant) {
        return adherantRepository.save(adherant);
    }

    @Transactional(readOnly = true)
    public List<Adherant> getAll() {
        return adherantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Adherant> getAdherantById(int id) {
        return adherantRepository.findById(id);
    }

    public void delete(int id) {
        adherantRepository.deleteById(id);
    }

    public Adherant findAdherant( String nom, String prenom) {
        return adherantRepository.findAdherant(nom, prenom);
    }
}