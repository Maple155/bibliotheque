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
public class LoginController {
    @Autowired
    private AdherantService adherantService;

    @Autowired
    private ExemplairesRestantsService exemplairesRestantsService;

    @Autowired
    private TypePretService typePretService;

    @GetMapping("/")
    public String getLogin(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            HttpSession session,
            Model model) {

        Adherant adherant = adherantService.findAdherant(nom, prenom);
        if (adherant != null) {
            session.setAttribute("adherant", adherant);

            List<V_exemplairesRestants> exemplairesRestants = exemplairesRestantsService.read();
            List<TypePret> typePrets= typePretService.getAll();

            model.addAttribute("liste_livre", exemplairesRestants);
            model.addAttribute("adherant", adherant);
            model.addAttribute("typesPret", typePrets);

            return "home";
        }

        return "redirect:/?error=utilisateur introuvable";
    }

    @GetMapping("/admin")
    public String loginAdmin ()
    {
        return "loginAdmin";
    }

    @PostMapping("/checkLogin") 
    public String checkLoginAdmin (
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        HttpSession session,
        Model model) {
            
        if (nom.equals("ranto") && prenom.equals("ranto")) {
            List<V_exemplairesRestants> exemplairesRestants = exemplairesRestantsService.read();
            List<TypePret> typePrets= typePretService.getAll();
            Adherant adherant = (Adherant) session.getAttribute("adherant");

            session.setAttribute("admin", "1");

            model.addAttribute("liste_livre", exemplairesRestants);
            model.addAttribute("adherant", adherant);
            model.addAttribute("typesPret", typePrets);
            model.addAttribute("success", "connecter en tant qu' admin reussi");
            return "home";            
        }

        model.addAttribute("error", "Admin introuvable");
        return "redirect:/admin";
    }

}
