package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repo.models.ConditionPret;
import repo.models.Ferie;
import repo.repositories.ConditionPretRepository;
import repo.repositories.FerieRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@Service
public class ConditionPretService {
    private final ConditionPretRepository repo;
    private final FerieService ferieService;

    @Autowired
    public ConditionPretService(ConditionPretRepository repo, FerieService ferieService) {
        this.repo = repo;
        this.ferieService = ferieService;
    }

    public ConditionPret create(ConditionPret object) {
        return repo.save(object);
    }

    @Transactional(readOnly = true)
    public List<ConditionPret> read() {
        List<ConditionPret> list = repo.findAll();
        for (ConditionPret cp : list) {
            Hibernate.initialize(cp.getTypePret());
            Hibernate.initialize(cp.getTypeAdherant());
        }
        return list;
    }

    public Optional<ConditionPret> readById(int id) {
        return repo.findById(id);
    }

    public ConditionPret update(int id, ConditionPret object) {
        Optional<ConditionPret> optional = repo.findById(id);
        if (optional.isPresent()) {
            ConditionPret existing = optional.get();
            existing.setTypeAdherant(object.getTypeAdherant());
            existing.setTypePret(object.getTypePret());
            existing.setExemplaireMax(object.getExemplaireMax());
            existing.setDureeMax(object.getDureeMax());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public static LocalDate calculerNouvelleDate(LocalDate dateInitiale, int nombreDeJours) {
        return dateInitiale.plusDays(nombreDeJours);
    }

    public boolean isFerie(LocalDate date) {
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // String[] feries = {
    //     "13/07/2025",
    //     "26/07/2025",
    //     "20/07/2025",
    //     "19/07/2025",
    //     "27/07/2025",
    //     "03/08/2025",
    //     "10/08/2025",
    //     "17/08/2025"
    // };

    List<Ferie> jourFerie = ferieService.read();

    for (Ferie ferie : jourFerie) {
        LocalDate ferieDate = ferie.getDate_ferie().toLocalDate();
        if (date.equals(ferieDate)) {
            return true;
        }
    }
    
    // for (String ferieStr : feries) {
    //     LocalDate ferieDate = LocalDate.parse(ferieStr, formatter);
    //     if (date.equals(ferieDate)) {
    //         return true;
    //     }
    // }

    return false;
}
}