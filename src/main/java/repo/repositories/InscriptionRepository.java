package repo.repositories;

import repo.models.Inscription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    @Query("SELECT i FROM Inscription i WHERE adherant.id = :idAdherant ORDER BY id DESC LIMIT 1")
    Inscription getCurrentInscription(@Param("idAdherant") int idAdherant);

    @Query("SELECT i FROM Inscription i WHERE adherant.id = :idAdherant ORDER BY id DESC")
    List<Inscription> getInscriptionsByAdherant(@Param("idAdherant") int idAdherant);
}   