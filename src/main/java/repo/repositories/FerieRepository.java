package repo.repositories;

import repo.models.Adherant;
import repo.models.Ferie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FerieRepository extends JpaRepository<Ferie, Integer> {
}
