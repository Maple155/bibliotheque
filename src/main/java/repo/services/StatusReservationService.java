package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.StatusReservation;
import repo.repositories.StatusReservationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StatusReservationService {
    private final StatusReservationRepository repo;

    @Autowired
    public StatusReservationService(StatusReservationRepository repo) {
        this.repo = repo;
    }

    public StatusReservation create(StatusReservation object) {
        return repo.save(object);
    }

    public List<StatusReservation> read() {
        return repo.findAll();
    }

    public Optional<StatusReservation> readById(int id) {
        return repo.findById(id);
    }

    public StatusReservation update(int id, StatusReservation object) {
        Optional<StatusReservation> optional = repo.findById(id);
        if (optional.isPresent()) {
            StatusReservation existing = optional.get();
            existing.setReservation(object.getReservation());
            existing.setTypeStatusPret(object.getTypeStatusPret());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}