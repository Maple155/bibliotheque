package repo.repositories;

import repo.models.TypeStatusPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeStatusPretRepository extends JpaRepository<TypeStatusPret, Integer> {
}