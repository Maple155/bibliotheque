package repo.repositories;

import repo.models.TypeAdherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAdherantRepository extends JpaRepository<TypeAdherant, Integer> {
}