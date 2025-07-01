package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Reservation;
import repo.repositories.ReservationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository repo;

    @Autowired
    public ReservationService(ReservationRepository repo) {
        this.repo = repo;
    }

    public Reservation create(Reservation object) {
        return repo.save(object);
    }

    public List<Reservation> read() {
        return repo.findAll();
    }

    public Optional<Reservation> readById(int id) {
        return repo.findById(id);
    }

    public Reservation update(int id, Reservation object) {
        Optional<Reservation> optional = repo.findById(id);
        if (optional.isPresent()) {
            Reservation existing = optional.get();
            existing.setAdherant(object.getAdherant());
            existing.setExemplaire(object.getExemplaire());
            existing.setDateReservation(object.getDateReservation());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}