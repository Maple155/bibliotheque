package repo.repositories;

import repo.models.ConditionPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionPretRepository extends JpaRepository<ConditionPret, Integer> {
}