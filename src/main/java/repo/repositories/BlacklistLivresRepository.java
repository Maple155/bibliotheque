package repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repo.models.BlacklistLivres;

@Repository
public interface BlacklistLivresRepository extends JpaRepository<BlacklistLivres, Integer> {

}