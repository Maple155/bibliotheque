package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repo.models.Pret;
import repo.repositories.PretRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {
    private final PretRepository repo;

    @Autowired
    public PretService(PretRepository repo) {
        this.repo = repo;
    }

    public Pret create(Pret object) {
        return repo.save(object);
    }

    @Transactional
    public List<Pret> read() {
        List<Pret> temp = repo.findAll();
        if (temp != null) {
            for (Pret pret : temp) {
                Hibernate.initialize(pret.getAdherant());
                Hibernate.initialize(pret.getExemplaire());
                Hibernate.initialize(pret.getTypePret());
            }
        }
        return temp;
    }

    @Transactional
    public Optional<Pret> readById(int id) {
        Optional<Pret> pret = repo.findById(id);
        if (pret != null) {
            Hibernate.initialize(pret.get().getAdherant());
            Hibernate.initialize(pret.get().getExemplaire());
            Hibernate.initialize(pret.get().getTypePret());
        }
        return pret;
    }

    public Pret update(int id, Pret object) {
        Optional<Pret> optional = repo.findById(id);
        if (optional.isPresent()) {
            Pret existing = optional.get();
            existing.setAdherant(object.getAdherant());
            existing.setExemplaire(object.getExemplaire());
            existing.setTypePret(object.getTypePret());
            existing.setDateDebut(object.getDateDebut());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}