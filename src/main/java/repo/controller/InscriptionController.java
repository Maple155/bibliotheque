package repo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repo.models.*;
import repo.services.*;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class InscriptionController {
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private StatusInscriptionService statusInscriptionService;
    @Autowired
    private TypeStatusInscriptionService typeStatusInscriptionService;
    @Autowired
    private TypePretService typePretService;
    @Autowired
    private ExemplairesRestantsService exemplairesRestantsService;


    @GetMapping("/inscription")
    public String inscription (
        HttpSession session,
        Model model
    ) {

        return "inscription";
    }

    @PostMapping("/inscription") 
    public String doInscription (
        @Param("date_debut") String str_debut,
        @Param("date_fin") String str_fin,
        HttpSession session,
        Model model
    ) {

        Adherant adherant = (Adherant) session.getAttribute("adherant");
        Date date_debut = Date.valueOf(str_debut);
        Date date_fin = Date.valueOf(str_fin);
        List<V_exemplairesRestants> exemplairesRestants = exemplairesRestantsService.read();
        List<TypePret> typePrets= typePretService.read();

        model.addAttribute("liste_livre", exemplairesRestants);
        model.addAttribute("adherant", adherant);
        model.addAttribute("typesPret", typePrets);

        try {
            Inscription inscription = new Inscription();
            inscription.setAdherant(adherant);
            inscription.setDateDebut(date_debut);
            inscription.setDatefin(date_fin);
    
            TypeStatusInscription typeStatusInscription = typeStatusInscriptionService.readById(1).orElse(null);
            
            StatusInscription SI = new StatusInscription();
            SI.setInscription(inscription);
            SI.setTypeStatusInscription(typeStatusInscription);
    
            SI = statusInscriptionService.create(SI);
    
    
            model.addAttribute("success", "inscription reussi");
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'inscription : " + e.getMessage());
        }
        return "home";
    }
}
