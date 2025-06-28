package repo.services;

import repo.models.ConditionPret;
import repo.repositories.ConditionPretRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConditionPretService {

    private final ConditionPretRepository conditionPretRepository;

    public ConditionPretService(ConditionPretRepository conditionPretRepository) {
        this.conditionPretRepository = conditionPretRepository;
    }

    public ConditionPret save(ConditionPret conditionPret) {
        return conditionPretRepository.save(conditionPret);
    }

    @Transactional(readOnly = true)
    public List<ConditionPret> getAll() {
        return conditionPretRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ConditionPret> getConditionPretById(int id) {
        return conditionPretRepository.findById(id);
    }

    public void delete(int id) {
        conditionPretRepository.deleteById(id);
    }
}