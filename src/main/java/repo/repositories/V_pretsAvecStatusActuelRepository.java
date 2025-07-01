package repo.repositories;

import repo.models.V_exemplairesRestants;
import repo.models.V_pretsAvecStatusActuel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface V_pretsAvecStatusActuelRepository extends JpaRepository<V_pretsAvecStatusActuel, Integer> {
    @Query("SELECT v FROM V_pretsAvecStatusActuel v WHERE v.idAdherant = :idAdherant AND v.statutActuel = :statutActuel")
    List<V_pretsAvecStatusActuel> findByAdherant(@Param("idAdherant") Integer idAdherant, @Param("statutActuel") String status);    
}