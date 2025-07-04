package repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.models.PenaliteAdherant;
import repo.repositories.PenaliteAdherantRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class PenaliteAdherantService {
    @Autowired
    private PenaliteAdherantRepository penaliteAdherantRepository;

    public List<PenaliteAdherant> findAll() {
        return penaliteAdherantRepository.findAll();
    }

    public List<PenaliteAdherant> findByAdherant(int idAdherant) {
        return penaliteAdherantRepository.findByAdherant(idAdherant);
    }

    public int totalPenalite(int idAdherant) {
        List<PenaliteAdherant> penalites = findByAdherant(idAdherant);
        int somme = 0;

        for (PenaliteAdherant penaliteAdherant : penalites) {
            somme += penaliteAdherant.getNbJour();
        }
           
        return somme;
    } 

    public Date dateDebutPenalite(int idAdherant) {
        List<PenaliteAdherant> penalites = findByAdherant(idAdherant);
    
        if (penalites == null || penalites.isEmpty()) {
            return null; 
        }
    
        Date result = penalites.get(0).getDatePenalite(); 
    
        for (PenaliteAdherant penaliteAdherant : penalites) {
            Date temp = penaliteAdherant.getDatePenalite();
            if (temp.compareTo(result) < 0) {
                result = temp; 
            }
        }
    
        return result;
    }
    
    public Date ajouterJours(Date dateDepart, int nbJour) {
        if (dateDepart == null) {
            throw new IllegalArgumentException("La date de départ ne peut pas être null");
        }

        LocalDate depart = dateDepart.toLocalDate();
        LocalDate futur = depart.plusDays(nbJour);

        return Date.valueOf(futur);
    }
}