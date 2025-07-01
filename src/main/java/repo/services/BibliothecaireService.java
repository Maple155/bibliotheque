package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Bibliothecaire;
import repo.repositories.BibliothecaireRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BibliothecaireService {
    private final BibliothecaireRepository repo;

    @Autowired
    public BibliothecaireService(BibliothecaireRepository repo) {
        this.repo = repo;
    }

    public Bibliothecaire create(Bibliothecaire object) {
        return repo.save(object);
    }

    public List<Bibliothecaire> read() {
        return repo.findAll();
    }

    public Optional<Bibliothecaire> readById(int id) {
        return repo.findById(id);
    }

    public Bibliothecaire update(int id, Bibliothecaire object) {
        Optional<Bibliothecaire> optional = repo.findById(id);
        if (optional.isPresent()) {
            Bibliothecaire existing = optional.get();
            existing.setNom(object.getNom());
            existing.setPrenom(object.getPrenom());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}