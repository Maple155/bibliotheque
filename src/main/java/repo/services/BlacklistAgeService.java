package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repo.models.BlacklistAge;
import repo.repositories.BlacklistAgeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BlacklistAgeService {
    private final BlacklistAgeRepository repo;

    @Autowired
    public BlacklistAgeService(BlacklistAgeRepository repo) {
        this.repo = repo;
    }

    public BlacklistAge create(BlacklistAge object) {
        return repo.save(object);
    }

    @Transactional
    public List<BlacklistAge> read() {
        List<BlacklistAge> list = repo.findAll();
        for (BlacklistAge b : list) {
            Hibernate.initialize(b.getLivre());
        }
    return list;
    }

    public Optional<BlacklistAge> readById(int id) {
        return repo.findById(id);
    }

    public BlacklistAge update(int id, BlacklistAge object) {
        Optional<BlacklistAge> optional = repo.findById(id);
        if (optional.isPresent()) {
            BlacklistAge existing = optional.get();
            existing.setAgeMin(object.getAgeMin());
            existing.setLivre(object.getLivre());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}