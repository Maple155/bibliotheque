package repo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import repo.models.PenaliteAdherant;

@Repository
public interface PenaliteAdherantRepository extends JpaRepository<PenaliteAdherant, Integer> {

    @Query("SELECT p FROM PenaliteAdherant p WHERE p.idAdherant = :idAdherant")
    List<PenaliteAdherant> findByAdherant(@Param("idAdherant") int idAdherant);

}