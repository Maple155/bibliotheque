package repo.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.Adherant;
import repo.repositories.AdherantRepository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AdherantService {
    private final AdherantRepository repo;

    @Autowired
    public AdherantService(AdherantRepository repo) {
        this.repo = repo;
    }

    public Adherant create(Adherant object) {
        return repo.save(object);
    }

    @Transactional(readOnly = true)
    public List<Adherant> read() {
        List<Adherant> adherants = repo.findAll();
        
        if (adherants != null) {
            for (Adherant adherant : adherants) {
                Hibernate.initialize(adherant.getTypeAdherant());
            }    
        }

        return adherants;
    }

    @Transactional(readOnly = true)
    public Optional<Adherant> readById(int id) {
        Optional<Adherant> tempOpt = repo.findById(id);
        if (tempOpt.isPresent()) {
            Hibernate.initialize(tempOpt.get().getTypeAdherant());
        }
        return tempOpt;
    }
    
    public Adherant update(int id, Adherant object) {
        Optional<Adherant> optional = repo.findById(id);
        if (optional.isPresent()) {
            Adherant existing = optional.get();
            existing.setTypeAdherant(object.getTypeAdherant());
            existing.setNom(object.getNom());
            existing.setPrenom(object.getPrenom());
            return repo.save(existing);
        }
        return null;
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Adherant findAdherant(String nom, String prenom) {
        Adherant adherant = repo.findAdherant(nom, prenom);

        if (adherant != null) {
            Hibernate.initialize(adherant.getTypeAdherant());
        }

        return adherant;
    }

    public static int calculerAge(Date dateNaissance) {
        if (dateNaissance == null) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être nulle.");
        }

        LocalDate naissance = dateNaissance.toLocalDate();
        LocalDate aujourdHui = LocalDate.now();

        if (naissance.isAfter(aujourdHui)) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être dans le futur.");
        }

        return Period.between(naissance, aujourdHui).getYears();
    }
}