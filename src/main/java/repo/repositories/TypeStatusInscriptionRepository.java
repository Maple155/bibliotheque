package repo.repositories;

import repo.models.TypeStatusInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeStatusInscriptionRepository extends JpaRepository<TypeStatusInscription, Integer> {
}