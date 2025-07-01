package repo.repositories;

import repo.models.StatusInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusInscriptionRepository extends JpaRepository<StatusInscription, Integer> {
}