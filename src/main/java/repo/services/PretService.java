package repo.services;

import repo.models.Pret;
import repo.repositories.PretRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PretService {

    private final PretRepository pretRepository;

    public PretService(PretRepository pretRepository) {
        this.pretRepository = pretRepository;
    }

    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    @Transactional(readOnly = true)
    public List<Pret> getAll() {
        return pretRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pret> getPretById(int id) {
        return pretRepository.findById(id);
    }

    public void delete(int id) {
        pretRepository.deleteById(id);
    }
}