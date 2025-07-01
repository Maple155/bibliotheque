package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Admin;
import repo.repositories.AdminRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository repo;

    @Autowired
    public AdminService(AdminRepository repo) {
        this.repo = repo;
    }

    public Admin create(Admin object) {
        return repo.save(object);
    }

    public List<Admin> read() {
        return repo.findAll();
    }

    public Optional<Admin> readById(int id) {
        return repo.findById(id);
    }

    public Admin update(int id, Admin object) {
        Optional<Admin> optional = repo.findById(id);
        if (optional.isPresent()) {
            Admin existing = optional.get();
            existing.setNom(object.getNom());
            existing.setPrenom(object.getPrenom());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}