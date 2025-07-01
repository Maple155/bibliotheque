package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.V_pretsAvecStatusActuel;
import repo.repositories.V_pretsAvecStatusActuelRepository;
import java.util.List;
import java.util.Optional;

@Service
public class V_pretsAvecStatusActuelService {
    private final V_pretsAvecStatusActuelRepository repo;

    @Autowired
    public V_pretsAvecStatusActuelService(V_pretsAvecStatusActuelRepository repo) {
        this.repo = repo;
    }

    public List<V_pretsAvecStatusActuel> read() {
        return repo.findAll();
    }

    public Optional<V_pretsAvecStatusActuel> readById(int idPret) {
        return repo.findById(idPret);
    }

    public List<V_pretsAvecStatusActuel> readByAdherant(int idAdherant, String status) {
        return repo.findByAdherant(idAdherant, status);
    }
}