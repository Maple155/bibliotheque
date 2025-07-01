package repo.repositories;

import repo.models.StatusPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPretRepository extends JpaRepository<StatusPret, Integer> {
}