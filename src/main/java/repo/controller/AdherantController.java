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
public class AdherantController {
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

    @Autowired
    private VPretsAvecDateRetourService vPretsAvecDateRetourService;

    @Autowired 
    private ConditionPretService conditionPretService;

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private PenaliteAdherantService penaliteAdherantService;

    @GetMapping("/adherant")
    public String livre_exemplaire(Model model) {
        return "adherant"; 
    }

    @GetMapping(value = "/getAdherant", produces = "application/json")
    @ResponseBody
    public Map<String, List<Adherant>> getadherant() {
        Map<String, List<Adherant>> result = new HashMap<>();

        List<Adherant> adherant = adherantService.read();
        if (adherant == null) {
            result.put("error", adherant);
            return result;
        }

        result.put("adherant", adherant);
        return result;
    }

    @GetMapping(value = "/adherant", produces = "application/json")
    @ResponseBody
    public Map<String, Adherant> adherant(
        @RequestParam("adherant") Integer idAdherant) {
        Map<String, Adherant> result = new HashMap<>();

        Adherant adherant = adherantService.readById(idAdherant).get();
        if (adherant == null) {
            result.put("error", adherant);
            return result;
        }

        result.put("adherant", adherant);
        return result;
    }

    @GetMapping(value = "/getPretAdherant", produces = "application/json")
    @ResponseBody
    public Map<String, List<V_pretsAvecDateRetour>> getAdherantPret(
            @RequestParam("adherant") Integer idAdherant) {
        Map<String, List<V_pretsAvecDateRetour>> response = new HashMap<>();
        try {
            List<V_pretsAvecDateRetour> prets = vPretsAvecDateRetourService.readByStatusByAdherant(idAdherant, "en cours");

            if (prets == null) {
                response.put("error", prets);
                return response;
            }
    
            response.put("prets", prets);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = "/getCota", produces = "application/json")
    @ResponseBody
    public Map<String, List<ConditionPret>> getCota(
            @RequestParam("adherant") Integer idAdherant) {
        Map<String, List<ConditionPret>> response = new HashMap<>();
        try {
            List<ConditionPret> cota = conditionPretService.read();
            Adherant adherant = adherantService.readById(idAdherant).orElse(null);
            List<ConditionPret> conditionPrets = new ArrayList<>();
            if (cota == null) {
                response.put("error", conditionPrets);
                return response;
            }

            for (ConditionPret condition : cota) {
                if (condition.getTypeAdherant().getType().equals(adherant.getTypeAdherant().getType())) {
                    conditionPrets.add(condition);
                }
            }
    
            response.put("cota", conditionPrets);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = "/getAbonnement", produces = "application/json")
    @ResponseBody
    public Map<String, List<Inscription>> getAbonnement(
            @RequestParam("adherant") Integer idAdherant) {
        Map<String, List<Inscription>> response = new HashMap<>();
        try {

            List<Inscription> inscriptions = inscriptionService.getInscriptionsByAdherant(idAdherant);

            if (inscriptions == null) {
                response.put("error", inscriptions);
                return response;
            } 

            response.put("inscriptions", inscriptions);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = "/getPenalite", produces = "application/json")
    @ResponseBody
    public Map<String, List<PenaliteAdherant>> getPenalite(
            @RequestParam("adherant") Integer idAdherant) {
        Map<String, List<PenaliteAdherant>> response = new HashMap<>();
        try {

            List<PenaliteAdherant> penalites = penaliteAdherantService.findByAdherant(idAdherant);

            if (penalites == null) {
                response.put("error", penalites);
                return response;
            } 

            response.put("penalite", penalites);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
