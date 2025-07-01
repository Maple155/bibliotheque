package repo.repositories;

import repo.models.BlacklistLivres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistLivresRepository extends JpaRepository<BlacklistLivres, Integer> {
}