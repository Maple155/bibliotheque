package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.models.Exemplaire;
import repo.models.ProlongementPret;
import repo.repositories.ProlongementPretRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProlongementPretService {
    private final ProlongementPretRepository repo;

    @Autowired
    public ProlongementPretService(ProlongementPretRepository repo) {
        this.repo = repo;
    }

    public ProlongementPret create(ProlongementPret object) {
        return repo.save(object);
    }

    @Transactional
    public List<ProlongementPret> read() {
        List<ProlongementPret> prolongement = repo.findAll();
            if (prolongement != null) {
                for (ProlongementPret prolongementPret : prolongement) {
                    Hibernate.initialize(prolongementPret.getPret());   
                }
            }
            return prolongement;
    }

    public Optional<ProlongementPret> readById(int id) {
        Optional<ProlongementPret> prolongementOpt = repo.findById(id);
        if (prolongementOpt != null) {
            Hibernate.initialize(prolongementOpt.get().getPret());
        }
        return prolongementOpt;
    }

    public ProlongementPret update(int id, ProlongementPret object) {
        Optional<ProlongementPret> optional = repo.findById(id);
        if (optional.isPresent()) {
            ProlongementPret existing = optional.get();
            existing.setPret(object.getPret());
            existing.setDateProlongement(object.getDateProlongement());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}