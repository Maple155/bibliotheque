package repo.repositories;

import repo.models.ProlongementPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProlongementPretRepository extends JpaRepository<ProlongementPret, Integer> {

}