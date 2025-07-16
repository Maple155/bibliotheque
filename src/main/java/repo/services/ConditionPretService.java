package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repo.models.ConditionPret;
import repo.repositories.ConditionPretRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ConditionPretService {
    private final ConditionPretRepository repo;

    @Autowired
    public ConditionPretService(ConditionPretRepository repo) {
        this.repo = repo;
    }

    public ConditionPret create(ConditionPret object) {
        return repo.save(object);
    }

    @Transactional(readOnly = true)
    public List<ConditionPret> read() {
        List<ConditionPret> list = repo.findAll();
        for (ConditionPret cp : list) {
            Hibernate.initialize(cp.getTypePret());
            Hibernate.initialize(cp.getTypeAdherant());
        }
        return list;
    }

    public Optional<ConditionPret> readById(int id) {
        return repo.findById(id);
    }

    public ConditionPret update(int id, ConditionPret object) {
        Optional<ConditionPret> optional = repo.findById(id);
        if (optional.isPresent()) {
            ConditionPret existing = optional.get();
            existing.setTypeAdherant(object.getTypeAdherant());
            existing.setTypePret(object.getTypePret());
            existing.setExemplaireMax(object.getExemplaireMax());
            existing.setDureeMax(object.getDureeMax());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}