package repo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repo.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ExemplairesRestantsRepository extends JpaRepository<V_exemplairesRestants, Integer> {

    @Query("SELECT v FROM V_exemplairesRestants v WHERE v.idExemplaire = :idExemplaire")
    V_exemplairesRestants findByExemplaire(@Param("idExemplaire") Integer idExemplaire);    

    @Query("SELECT v FROM V_exemplairesRestants v WHERE v.idLivre = :idLivre")
    List<V_exemplairesRestants> findByLivre(@Param("idLivre") Integer idLivre);    
}