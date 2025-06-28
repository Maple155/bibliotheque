package repo.services;

import repo.models.Livre;
import repo.repositories.LivreRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    @Transactional(readOnly = true)
    public List<Livre> getAll() {
        return livreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Livre> getLivreById(int id) {
        return livreRepository.findById(id);
    }

    public void delete(int id) {
        livreRepository.deleteById(id);
    }
}