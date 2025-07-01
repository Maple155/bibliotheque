package repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import repo.models.V_pretsAvecDateRetour;

@Repository
public interface VPretsAvecDateRetourRepository extends JpaRepository<V_pretsAvecDateRetour, Integer> {

   @Query("SELECT v FROM V_pretsAvecDateRetour v WHERE v.idAdherant = :idAdherant")
   List<V_pretsAvecDateRetour> findByAdherant(@Param("idAdherant") Integer idAdherant);
   
}
