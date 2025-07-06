package repo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import repo.models.VProlongementsAvecStatusActuel;

@Repository
public interface VProlongementsAvecStatusActuelRepository extends JpaRepository<VProlongementsAvecStatusActuel, Integer> {
    @Query("SELECT pas FROM VProlongementsAvecStatusActuel pas WHERE pas.idAdherant = :idAdherant AND pas.statutActuel = :statutActuel")
    List<VProlongementsAvecStatusActuel> readByAdherantStatut(@Param("idAdherant") int idAdherant, @Param("statutActuel") String statutActuel);
}