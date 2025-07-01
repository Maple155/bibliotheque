package repo.repositories;

import repo.models.Rarete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RareteRepository extends JpaRepository<Rarete, Integer> {
}