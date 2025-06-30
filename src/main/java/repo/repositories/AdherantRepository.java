package repo.repositories;

import repo.models.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AdherantRepository extends JpaRepository<Adherant, Integer> {

    @Query("SELECT a FROM Adherant a WHERE a.nom = :nom AND a.prenom = :prenom")
    Adherant findAdherant(@Param("nom") String nom, @Param("prenom") String prenom);

}
