package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repo.models.Adherant;
import repo.models.Exemplaire;
import repo.repositories.ExemplaireRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {
    private final ExemplaireRepository repo;

    @Autowired
    public ExemplaireService(ExemplaireRepository repo) {
        this.repo = repo;
    }

    public Exemplaire create(Exemplaire object) {
        return repo.save(object);
    }

    public List<Exemplaire> read() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Exemplaire> readById(int id) {
        Optional<Exemplaire> exemplaireOpt = repo.findById(id);
        if (exemplaireOpt != null) {
            Hibernate.initialize(exemplaireOpt.get().getLivre());
        }
        return exemplaireOpt;
    }

    public Exemplaire update(int id, Exemplaire object) {
        Optional<Exemplaire> optional = repo.findById(id);
        if (optional.isPresent()) {
            Exemplaire existing = optional.get();
            existing.setLivre(object.getLivre());
            existing.setNumeroExemplaire(object.getNumeroExemplaire());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public List<Exemplaire> findAllWithLivre() {
        return repo.findAllWithLivre();
    }

    @Transactional(readOnly = true)
    public List<Exemplaire> findByLivre(int idLivre) {
        List<Exemplaire> exemplaires = repo.findByLivre(idLivre);
        
        if (exemplaires != null) {
            for (Exemplaire exemplaire : exemplaires) {
                Hibernate.initialize(exemplaire.getLivre());
                Hibernate.initialize(exemplaire.getLivre().getRarete());
            }
        }
        return exemplaires;
    }
}