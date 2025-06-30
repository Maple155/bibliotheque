package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.V_exemplairesRestants;
import repo.repositories.ExemplairesRestantsRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ExemplairesRestantsService {
    private final ExemplairesRestantsRepository repo;

    @Autowired
    public ExemplairesRestantsService(ExemplairesRestantsRepository rep) {
        this.repo = rep;
    }

    public V_exemplairesRestants create(V_exemplairesRestants object) {
        return repo.save(object);
    }

    public List<V_exemplairesRestants> read() {
        return repo.findAll();
    }

    public Optional<V_exemplairesRestants> readById(int id) {
        return repo.findById(id);
    }

    public V_exemplairesRestants update(int id, V_exemplairesRestants object) {
        try {
            Optional<V_exemplairesRestants> optional = repo.findById(id);
            if (optional.isPresent()) {
                V_exemplairesRestants existing = optional.get();
                
                existing.setTitre(object.getTitre());
                existing.setNbPage(object.getNbPage());
                existing.setAuteur(object.getAuteur());
                existing.setDatePublication(object.getDatePublication());
                existing.setNbChapitre(object.getNbChapitre());
                existing.setLangue(object.getLangue());
                existing.setEditeur(object.getEditeur());
                existing.setGenre(object.getGenre());
                existing.setNbExemplairesTotaux(object.getNbExemplairesTotaux());
                existing.setNbExemplairesPretes(object.getNbExemplairesPretes());
                existing.setNbExemplairesRestants(object.getNbExemplairesRestants());
                existing.setId_exemplaire(object.getId_exemplaire());
                return repo.save(existing);
            }
            return null;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
