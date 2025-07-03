package repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repo.models.VReservationsAvecStatusActuel;

@Repository
public interface VReservationsAvecStatusActuelRepository extends JpaRepository<VReservationsAvecStatusActuel, Integer> {
    // Ajoute ici tes méthodes personnalisées si besoin
}