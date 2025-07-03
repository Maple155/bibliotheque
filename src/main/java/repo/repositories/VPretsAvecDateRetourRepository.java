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
   
   @Query("SELECT v FROM V_pretsAvecDateRetour v WHERE v.idPret = :idPret")
   V_pretsAvecDateRetour findByPret(@Param("idPret") Integer idPret);

   @Query("SELECT v FROM V_pretsAvecDateRetour v WHERE v.idExemplaire = :idExemplaire")
   List<V_pretsAvecDateRetour> findByExemplaire(@Param("idExemplaire") Integer idExemplaire);

   @Query("SELECT v FROM V_pretsAvecDateRetour v WHERE v.idPret = :idPret AND v.idAdherant = :idAdherant")
   V_pretsAvecDateRetour findByPretEtAdherant(@Param("idPret") Integer idPret, @Param("idAdherant") Integer idAdherant);
}
