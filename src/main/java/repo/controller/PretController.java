package repo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import repo.models.*;
import repo.services.*;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class PretController {
    @Autowired
    private AdherantService adherantService;

    @Autowired
    private ExemplairesRestantsService exemplairesRestantsService;

    @Autowired
    private TypePretService typePretService;

    @Autowired
    private BlacklistLivresService blacklistLivresService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private PretService pretService;

    @PostMapping("/preterLivre")
    public String preterLivre (
        @RequestParam("id_adherant") String adherant,
        @RequestParam("id_exemplaire") String exemplaire,
        @RequestParam("type_pret") String type_pret,
        @RequestParam("date_pret") String date,
        @RequestParam("nombre") String str_nombre,
        HttpSession session,
        Model model) {

        int id_adherant = Integer.parseInt(adherant);
        int id_exemplaire = Integer.parseInt(exemplaire);
        int id_type_pret = Integer.parseInt(type_pret);
        Date date_pret = Date.valueOf(date);
        int nombre = Integer.parseInt(str_nombre);

        Adherant currAdherant = adherantService.getAdherantById(id_adherant).orElse(null);
        Exemplaire currExemplaire = exemplaireService.getExemplaireById(id_exemplaire).orElse(null);
        TypePret currPret = typePretService.getTypePretById(id_type_pret).orElse(null);
        
        List<V_exemplairesRestants> exemplairesRestants = exemplairesRestantsService.read();
        List<TypePret> typePrets= typePretService.getAll();

        List<BlacklistLivres> blacklistLivres = blacklistLivresService.read();
        V_exemplairesRestants exemplaireRestants = exemplairesRestantsService.findByExemplaire(id_exemplaire);

        model.addAttribute("liste_livre", exemplairesRestants);
        model.addAttribute("adherant", currAdherant);
        model.addAttribute("typesPret", typePrets);

        if (!blacklistLivres.isEmpty()) {    
            for (int i = 0; i < blacklistLivres.size(); i++) {
                if (blacklistLivres.get(i).getLivre().getId() == currExemplaire.getLivre().getId() && 
                blacklistLivres.get(i).getTypeAdherant().getId() == currAdherant.getTypeAdherant().getId()) {
                    model.addAttribute("error", "Les " + blacklistLivres.get(i).getTypeAdherant().getType() + " ne peuventpas emprunter ce livre");
                    
                    return "home";
                }
            }
        }

        if (exemplaireRestants.getNbExemplairesRestants() == 0) {
            model.addAttribute("error", "Exemplaires insuffisantes");
                    
            return "home";
        }

        Pret pret = new Pret(currAdherant, currExemplaire, nombre, currPret, date_pret);
        pretService.save(pret);

        model.addAttribute("success", "Emprunter avec succes");
        return "home";
    }

    @GetMapping("/mesPrets")
    public String getPretsByAdherant(
        HttpSession session,
        Model model) {

        return "mesPrets";
    }
}
