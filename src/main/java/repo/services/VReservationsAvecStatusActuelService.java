package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.VReservationsAvecStatusActuel;
import repo.repositories.VReservationsAvecStatusActuelRepository;

import java.util.List;

@Service
public class VReservationsAvecStatusActuelService {
    @Autowired
    private VReservationsAvecStatusActuelRepository vReservationsAvecStatusActuelRepository;

    public List<VReservationsAvecStatusActuel> findAll() {
        return vReservationsAvecStatusActuelRepository.findAll();
    }

    public List<VReservationsAvecStatusActuel> findByAdherantByStatus(int idAdherant, String status) {
        return vReservationsAvecStatusActuelRepository.readByAdherantStatut(idAdherant, status);
    }
}