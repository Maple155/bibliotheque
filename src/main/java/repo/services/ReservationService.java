package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public List<Reservation> read() {
        List<Reservation> reservations = repo.findAll();

        if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                Hibernate.initialize(reservation.getAdherant());
                Hibernate.initialize(reservation.getExemplaire());
                Hibernate.initialize(reservation.getTypePret());
            }
        }

        return reservations;
    }

    @Transactional
    public Optional<Reservation> readById(int id) {
        Optional<Reservation> resOpt = repo.findById(id);
        
        if (resOpt != null) {
            Hibernate.initialize(resOpt.get().getAdherant());
            Hibernate.initialize(resOpt.get().getExemplaire());
            Hibernate.initialize(resOpt.get().getTypePret());
        }

        return resOpt;
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