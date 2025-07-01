package repo.repositories;

import repo.models.StatusReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusReservationRepository extends JpaRepository<StatusReservation, Integer> {
}