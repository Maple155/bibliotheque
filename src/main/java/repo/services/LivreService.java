package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<Livre> read() {
        List<Livre> livres = repo.findAll();

        if (livres != null) {
            for (Livre livre : livres) {
                Hibernate.initialize(livre.getRarete());
            }
        }

        return livres;
    }

    @Transactional(readOnly = true)
    public Optional<Livre> readById(int id) {
        Optional<Livre> livre = repo.findById(id);

        if (livre != null) {
            Hibernate.initialize(livre.get().getRarete());
        }

        return livre;
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