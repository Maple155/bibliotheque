package repo.repositories;

import repo.models.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Integer> {
}