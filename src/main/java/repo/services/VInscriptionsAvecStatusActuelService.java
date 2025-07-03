package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.VInscriptionsAvecStatusActuel;
import repo.repositories.VInscriptionsAvecStatusActuelRepository;

import java.util.List;

@Service
public class VInscriptionsAvecStatusActuelService {
    @Autowired
    private VInscriptionsAvecStatusActuelRepository vInscriptionsAvecStatusActuelRepository;

    public List<VInscriptionsAvecStatusActuel> findAll() {
        return vInscriptionsAvecStatusActuelRepository.findAll();
    }
}