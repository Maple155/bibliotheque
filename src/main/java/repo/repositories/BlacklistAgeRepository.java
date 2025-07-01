package repo.repositories;

import repo.models.BlacklistAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistAgeRepository extends JpaRepository<BlacklistAge, Integer> {
}