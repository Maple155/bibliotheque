package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Livre;
import repo.repositories.LivreRepository;
import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    private final LivreRepository repo;

    @Autowired
    public LivreService(LivreRepository repo) {
        this.repo = repo;
    }

    public Livre create(Livre object) {
        return repo.save(object);
    }

    public List<Livre> read() {
        return repo.findAll();
    }

    public Optional<Livre> readById(int id) {
        return repo.findById(id);
    }

    public Livre update(int id, Livre object) {
        Optional<Livre> optional = repo.findById(id);
        if (optional.isPresent()) {
            Livre existing = optional.get();
            existing.setTitre(object.getTitre());
            existing.setNbPage(object.getNbPage());
            existing.setAuteur(object.getAuteur());
            existing.setDatePublication(object.getDatePublication());
            existing.setNbChapitre(object.getNbChapitre());
            existing.setLangue(object.getLangue());
            existing.setEditeur(object.getEditeur());
            existing.setGenre(object.getGenre());
            existing.setRarete(object.getRarete());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}