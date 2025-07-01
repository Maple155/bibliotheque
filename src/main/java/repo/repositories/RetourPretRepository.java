package repo.repositories;

import repo.models.RetourPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetourPretRepository extends JpaRepository<RetourPret, Integer> {
}