package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.V_pretsAvecDateRetour;
import repo.repositories.VPretsAvecDateRetourRepository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VPretsAvecDateRetourService {
    private final VPretsAvecDateRetourRepository repo;

    @Autowired
    public VPretsAvecDateRetourService(VPretsAvecDateRetourRepository rep) {
        this.repo = rep;
    }

    public List<V_pretsAvecDateRetour> read() {
        return repo.findAll();
    }

    public Optional<V_pretsAvecDateRetour> readById(int idPret) {
        return repo.findById(idPret);
    }

    public List<V_pretsAvecDateRetour> readByAdherant(int id_adherant) {
        return repo.findByAdherant(id_adherant);
    }

    public V_pretsAvecDateRetour readByPret(int id_pret) {
        return repo.findByPret(id_pret);
    }

    public int comparerDates(Date d1, Date d2) {
        return d1.compareTo(d2);
    }

    public V_pretsAvecDateRetour readByPretEtAdherant (int id_pret, int id_adherant) {
        return repo.findByPretEtAdherant(id_pret, id_adherant);
    }

    public List<V_pretsAvecDateRetour> readByExemplaire (int idExemplaire) {
        return repo.findByExemplaire(idExemplaire);
    }

    public List<V_pretsAvecDateRetour> readByStatusByAdherant (int idAdherant, String status) {
        return repo.findByStatusByAdherant(idAdherant, status);
    }
}