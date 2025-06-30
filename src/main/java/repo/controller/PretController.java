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
    private ExemplaireService exemplaireService;

    @PostMapping("/preterLivre")
    public String preterLivre (
        @RequestParam("id_adherant") String adherant,
        @RequestParam("id_exemplaire") String exemplaire,
        @RequestParam("type_pret") String type_pret,
        HttpSession session,
        Model model) {

        int id_adherant = Integer.parseInt(adherant);
        int id_exemplaire = Integer.parseInt(exemplaire);
        int id_type_pret = Integer.parseInt(type_pret);

        Adherant current_adherant = adherantService.getAdherantById(id_adherant).orElse(null);
        Exemplaire currExemplaire = exemplaireService.getExemplaireById(id_exemplaire).orElse(null);
        TypePret currentPret = typePretService.getTypePretById(id_type_pret).orElse(null);

        List<V_exemplairesRestants> exemplairesRestants = exemplairesRestantsService.read();
        List<TypePret> typePrets= typePretService.getAll();

        model.addAttribute("liste_livre", exemplairesRestants);
        model.addAttribute("adherant", current_adherant);
        model.addAttribute("typesPret", typePrets);
        
        return "home";
    }
}
