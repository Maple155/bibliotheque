package repo.repositories;

import repo.models.StatusProlongement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusProlongementRepository extends JpaRepository<StatusProlongement, Integer> {
}