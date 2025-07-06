package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.VProlongementsAvecStatusActuel;
import repo.repositories.VProlongementsAvecStatusActuelRepository;

import java.util.List;

@Service
public class VProlongementsAvecStatusActuelService {
    @Autowired
    private VProlongementsAvecStatusActuelRepository vProlongementsAvecStatusActuelRepository;

    public List<VProlongementsAvecStatusActuel> findAll() {
        return vProlongementsAvecStatusActuelRepository.findAll();
    }

    public List<VProlongementsAvecStatusActuel> readByAdherantStatut (int idAdherant, String status) {
        return vProlongementsAvecStatusActuelRepository.readByAdherantStatut(idAdherant, status);
    }
}