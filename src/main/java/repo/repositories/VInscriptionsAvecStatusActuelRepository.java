package repo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import repo.models.VInscriptionsAvecStatusActuel;
import repo.models.V_pretsAvecStatusActuel;

@Repository
public interface VInscriptionsAvecStatusActuelRepository extends JpaRepository<VInscriptionsAvecStatusActuel, Integer> {

    @Query("SELECT v FROM VInscriptionsAvecStatusActuel v WHERE v.idAdherant = :idAdherant")
    List<VInscriptionsAvecStatusActuel> findByAdherant(@Param("idAdherant") Integer idAdherant);    
}