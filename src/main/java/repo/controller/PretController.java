package repo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private BlacklistAgeService blacklistAgeService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private PretService pretService;
    @Autowired
    private VPretsAvecDateRetourService vPretsAvecDateRetourService;
    @Autowired
    private StatusPretService statusPretService;
    @Autowired
    private TypeStatusPretService typeStatusPretService;
    @Autowired
    private V_pretsAvecStatusActuelService v_pretsAvecStatusActuelService;
    @Autowired
    private ConditionPretService conditionPretService;
    @Autowired
    private PenaliteService penaliteService;
    @Autowired
    private RetourPretService retourPretService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired 
    private PenaliteAdherantService penaliteAdherantService;

    @PostMapping("/preterLivre")
    public String preterLivre(
            @RequestParam("id_adherant") String adherant,
            @RequestParam("id_exemplaire") String exemplaire,
            @RequestParam("type_pret") String type_pret,
            @RequestParam("date_pret") String date,
            HttpSession session,
            Model model) {

        int id_adherant = Integer.parseInt(adherant);
        int id_exemplaire = Integer.parseInt(exemplaire);
        int id_type_pret = Integer.parseInt(type_pret);
        Date date_pret = Date.valueOf(date);

        Adherant currAdherant = adherantService.readById(id_adherant).orElse(null);
        Exemplaire currExemplaire = exemplaireService.readById(id_exemplaire).orElse(null);
        TypePret currPret = typePretService.readById(id_type_pret).orElse(null);

        List<V_exemplairesRestants> exemplairesRestants = exemplairesRestantsService.read();
        List<TypePret> typePrets = typePretService.read();

        List<BlacklistLivres> blacklistLivres = blacklistLivresService.read();

        model.addAttribute("liste_livre", exemplairesRestants);
        model.addAttribute("adherant", currAdherant);
        model.addAttribute("typesPret", typePrets);

        // condition 1
        // Misy type d'adherant tsy afaka maka boky specifique
        if (!blacklistLivres.isEmpty()) {
            for (int i = 0; i < blacklistLivres.size(); i++) {
                if (blacklistLivres.get(i).getLivre().getId() ==
                currExemplaire.getLivre().getId() &&
                blacklistLivres.get(i).getTypeAdherant().getId() ==
                currAdherant.getTypeAdherant().getId()) {
                    model.addAttribute("error", "Les " +
                    blacklistLivres.get(i).getTypeAdherant().getType() + " ne peuvent pas emprunter ce livre");

                    return "home";
                }
            }
        }
        List<BlacklistAge> blacklistAges = blacklistAgeService.read();
        int age_adherant = AdherantService.calculerAge(currAdherant.getNaissance());

        List<V_pretsAvecStatusActuel> prets = v_pretsAvecStatusActuelService.readByAdherant(id_adherant, "en cours");
        List<ConditionPret> conditionPrets = conditionPretService.read();

        // condition 2
        // Efa bdb ny pret nataony
        if (!conditionPrets.isEmpty()) {
            for (int i = 0; i < conditionPrets.size(); i++) {
                if (conditionPrets.get(i).getTypeAdherant() == currAdherant.getTypeAdherant()) {
                    if (conditionPrets.get(i).getTypePret() == currPret && !currPret.getType().equals("sur place")) {
                        if (prets.size() >= conditionPrets.get(i).getExemplaireMax()) {
                            model.addAttribute("error",
                                    "Vous avez atteint le nombre maximum de pret que vous pouvez faire");
                            return "home";
                        }
                    }
                }
            }
        }

        // Condition 3
        // Tsy ampy taona izy
        if (!blacklistAges.isEmpty()) {
            for (int i = 0; i < blacklistAges.size(); i++) {
                if (blacklistAges.get(i).getLivre().getId() == currExemplaire.getLivre().getId()) {
                    if (blacklistAges.get(i).getAgeMin() > age_adherant) {
                        model.addAttribute("error", "Vous n'avez pas l'age requis pour ce livre");
                        return "home";
                    }
                }
            }
        }

        // condition 4
        // Nahazo penalite ve ilay olona ? 
        int nbJour = penaliteAdherantService.totalPenalite(currAdherant.getId());
        if (Integer.valueOf(nbJour) != null) {
            Date dateDebut = penaliteAdherantService.dateDebutPenalite(currAdherant.getId());
            Date dateFin = penaliteAdherantService.ajouterJours(dateDebut, nbJour);
        
            if (dateDebut.before(date_pret) &&
                    dateFin.after(date_pret)) {
                model.addAttribute("error", "Vous ne pouvez pas encore réserver à cause de votre penalisation"); 
                return "home";   
            }
        }
        
        // condition 5
        // A jour ve ny inscription any ?
        Inscription currInscription = inscriptionService.getCurrentInscription(currAdherant.getId());
        if (currInscription != null) {
            if (currInscription.getDateDebut().after(date_pret) ||
                currInscription.getDatefin().before(date_pret)) {
                model.addAttribute("error",
                    "Vous devez vous reinscrire - période valide : " 
                    + currInscription.getDateDebut() + " au " + currInscription.getDatefin()
                    + " — date de prêt : " + date_pret);
                return "home";
            }
        } else if (currInscription == null){
            model.addAttribute("error", "Vous devez vous reinscrire"); 
            return "home";   
        }

        Pret pret = new Pret(currAdherant, currExemplaire, currPret, date_pret);
        pret = pretService.create(pret);

        TypeStatusPret type_StatusPret = typeStatusPretService.readById(2).orElse(null);

        StatusPret status = new StatusPret();
        status.setPret(pret);
        status.setTypeStatusPret(type_StatusPret);
        status = statusPretService.create(status);

        model.addAttribute("success", "Emprunter avec succes - période valide : " + currInscription.getDateDebut() + " au " + currInscription.getDatefin() + "— date de prêt : " + date_pret);
        return "home";
    }

    @GetMapping("/mesPrets")
    public String getPretsByAdherant(
            HttpSession session,
            Model model) {

        Adherant adherant = (Adherant) session.getAttribute("adherant");
        List<V_pretsAvecDateRetour> allPrets = vPretsAvecDateRetourService.readByAdherant(adherant.getId());

        model.addAttribute("allPrets", allPrets);
        return "mesPrets";
    }

    @GetMapping("/allPrets")
    public String getAll(
            HttpSession session,
            Model model) {

        List<V_pretsAvecDateRetour> allPrets = vPretsAvecDateRetourService.read();

        model.addAttribute("allPrets", allPrets);
        return "allPrets";
    }

    @PostMapping("/rendreExemplaire")
    public String rendreExemplaire(
            @RequestParam("id_pret") String id,
            @RequestParam("id_adherant") String id_ad,
            @RequestParam("date_retour") String date,
            HttpSession session,
            Model model) {

        int id_adherant = Integer.parseInt(id_ad);
        int id_pret = Integer.parseInt(id);
        Date date_retour = Date.valueOf(date);

        Pret pret = pretService.readById(id_pret).orElse(null);
        V_pretsAvecDateRetour currPret = vPretsAvecDateRetourService.readByPret(id_pret);
        int compare = currPret.getDateRetourPrevue().compareTo(date_retour);
    
        // Tara ilay boky vô naverina
        if (date_retour.after(currPret.getDateRetourPrevue())) {

            List<Penalite> p = penaliteService.read();
            int nbJour = penaliteAdherantService.totalPenalite(id_adherant);
            Date dateDebut = penaliteAdherantService.dateDebutPenalite(id_adherant);
            Date dateFin = penaliteAdherantService.ajouterJours(dateDebut, nbJour);

            if (dateFin.before(date_retour)) {
                for (Penalite penalite : p) {
                    penaliteService.delete(penalite.getId());
                }    
            }

            LocalDate prevue = currPret.getDateRetourPrevue().toLocalDate();
            LocalDate reel = date_retour.toLocalDate();
            long joursDiff = ChronoUnit.DAYS.between(prevue, reel);
            Long diff = Long.valueOf(joursDiff);

            Penalite penalite = new Penalite();
            penalite.setDate(date_retour);
            penalite.setPret(pret);
            penalite.setNbJour(diff.intValue());
            
            penalite = penaliteService.create(penalite);
            model.addAttribute("error",
            "Pénalité : Livre rendu en retard. " +
            "Date début : " + currPret.getDateDebut() +
            ", Date retour prévue : " + currPret.getDateRetourPrevue() +
            ", Date retour réelle : " + date_retour);
        } else {
            model.addAttribute("success",
            "Livre remis avec succès. " +
            "Date début : " + currPret.getDateDebut() +
            ", Date retour prévue : " + currPret.getDateRetourPrevue() +
            ", Date retour réelle : " + date_retour);
        }

        RetourPret retourPret = new RetourPret();
        retourPret.setDateRetour(date_retour);
        retourPret.setPret(pret);

        retourPret = retourPretService.create(retourPret);

        TypeStatusPret typeStatusPret = typeStatusPretService.readById(3).orElse(null);

        StatusPret statusPret = new StatusPret();
        statusPret.setPret(pret);
        statusPret.setTypeStatusPret(typeStatusPret);

        statusPret = statusPretService.create(statusPret);

        List<V_pretsAvecDateRetour> allPrets = vPretsAvecDateRetourService.readByAdherant(id_adherant);

        model.addAttribute("allPrets", allPrets);
        return "mesPrets";
    }

}
