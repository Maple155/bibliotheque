package repo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import repo.models.VReservationsAvecStatusActuel;

@Repository
public interface VReservationsAvecStatusActuelRepository extends JpaRepository<VReservationsAvecStatusActuel, Integer> {
    @Query("SELECT v FROM VReservationsAvecStatusActuel v WHERE v.idAdherant = :idAdherant AND v.statutActuel = :statutActuel")
    List<VReservationsAvecStatusActuel> readByAdherantStatut(@Param("idAdherant") int idAdherant, @Param("statutActuel") String statutActuel);
}