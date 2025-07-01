package repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repo.models.HistoriquePret;

@Repository
public interface HistoriquePretRepository extends JpaRepository<HistoriquePret, Integer> {

}