package repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repo.models.VProlongementsAvecStatusActuel;

@Repository
public interface VProlongementsAvecStatusActuelRepository extends JpaRepository<VProlongementsAvecStatusActuel, Integer> {
    // Ajoute ici tes méthodes personnalisées si besoin
}