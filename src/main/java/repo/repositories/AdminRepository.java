package repo.repositories;

import repo.models.Adherant;
import repo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("SELECT a FROM Admin a WHERE a.nom = :nom AND a.prenom = :prenom")
    Admin findAdmin(@Param("nom") String nom, @Param("prenom") String prenom);
}