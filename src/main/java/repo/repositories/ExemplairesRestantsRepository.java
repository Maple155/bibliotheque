package repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import repo.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ExemplairesRestantsRepository extends JpaRepository<V_exemplairesRestants, Integer> {

    @Query("SELECT v FROM V_exemplairesRestants v WHERE v.id_exemplaire = :id_exemplaire")
    V_exemplairesRestants findByExemplaire(@Param("id_exemplaire") Integer id_exemplaire);

}