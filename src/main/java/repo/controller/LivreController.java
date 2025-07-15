package repo.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class LivreController {
    @Autowired
    private AdherantService adherantService;

    @Autowired
    private ExemplairesRestantsService exemplairesRestantsService;

    @Autowired
    private TypePretService typePretService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private BibliothecaireService bibliothecaireService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private ExemplaireService exemplaireService;

    @GetMapping("/livre_exemplaire")
    public String livre_exemplaire(Model model) {
        List<Livre> livres = livreService.read();
        model.addAttribute("livres", livres);
        return "livre_exemplaire"; 
    }
    
    @GetMapping(value = "/getLivre", produces = "application/json")
    @ResponseBody
    public Map<String, List<Livre>> getLivre() {
        Map<String, List<Livre>> result = new HashMap<>();

        List<Livre> livres = livreService.read();
        if (livres == null) {
            result.put("error", livres);
            return result;
        }

        result.put("livres", livres);
        return result;
    }

    @GetMapping(value = "/getExemplaire", produces = "application/json")
    @ResponseBody
    public Map<String, List<Exemplaire>> exemplaire(
            @RequestParam("livre") Integer id_livre) {
        Map<String, List<Exemplaire>> response = new HashMap<>();
        try {
            List<Exemplaire> exemplaires = exemplaireService.findByLivre(id_livre);

            if (exemplaires == null) {
                response.put("error", exemplaires);
                return response;
            }
    
            response.put("exemplaires", exemplaires);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = "/getExemplaireRestante", produces = "application/json")
    @ResponseBody
    public Map<String, List<V_exemplairesRestants>> exemplaireRestante(
            @RequestParam("livre") Integer id_livre) {
        Map<String, List<V_exemplairesRestants>> response = new HashMap<>();
        try {
            List<V_exemplairesRestants> reste_exemplaire = exemplairesRestantsService.findByLivre(id_livre);

            if (reste_exemplaire == null) {
                response.put("error", reste_exemplaire);
                return response;
            }
    
            response.put("reste_exemplaire", reste_exemplaire);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = "/livre", produces = "application/json")
    @ResponseBody
    public Map<String, Livre> livre(
            @RequestParam("livre") Integer id_livre) {
        Map<String, Livre> response = new HashMap<>();
        try {
            Livre livre = livreService.readById(id_livre).orElse(null);

            if (livre == null) {
                response.put("error", livre);
                return response;
            }
    
            response.put("livre", livre);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}