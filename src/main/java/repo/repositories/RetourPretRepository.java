package repo.repositories;

import repo.models.RetourPret;
import repo.models.V_pretsAvecDateRetour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RetourPretRepository extends JpaRepository<RetourPret, Integer> {
    
    @Query("SELECT rp FROM RetourPret rp WHERE rp.pret.id = :idPret")
    RetourPret findByPretId(@Param("idPret") int idPret);
}