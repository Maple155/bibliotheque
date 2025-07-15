package repo.repositories;

import repo.models.Exemplaire;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {

    @Query("SELECT e FROM Exemplaire e JOIN FETCH e.livre")
    List<Exemplaire> findAllWithLivre();

    @Query("SELECT e FROM Exemplaire e JOIN FETCH e.livre WHERE e.livre.id = :idLivre ")
    List<Exemplaire> findByLivre(@Param("idLivre") Integer idLivre);    
}