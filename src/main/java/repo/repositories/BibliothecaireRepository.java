package repo.repositories;

import repo.models.Admin;
import repo.models.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Integer> {
    @Query("SELECT a FROM Bibliothecaire a WHERE a.nom = :nom AND a.prenom = :prenom")
    Bibliothecaire findbBibliothecaire(@Param("nom") String nom, @Param("prenom") String prenom);
}