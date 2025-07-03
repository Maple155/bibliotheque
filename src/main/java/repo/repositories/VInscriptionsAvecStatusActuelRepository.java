package repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repo.models.VInscriptionsAvecStatusActuel;

@Repository
public interface VInscriptionsAvecStatusActuelRepository extends JpaRepository<VInscriptionsAvecStatusActuel, Integer> {
    // Ajoute ici tes méthodes personnalisées si besoin
}