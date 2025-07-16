package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repo.models.Admin;
import repo.models.Ferie;
import repo.repositories.AdminRepository;
import repo.repositories.FerieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FerieService {
    private final FerieRepository repo;

    @Autowired
    public FerieService(FerieRepository repo) {
        this.repo = repo;
    }

    
    public List<Ferie> read() {
        return repo.findAll();
    }
}