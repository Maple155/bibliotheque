package repo.repositories;

import repo.models.DureeInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DureeInscriptionRepository extends JpaRepository<DureeInscription, Integer> {
}