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
public class PrologementController {
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
    private LivreService livreService;
    @Autowired
    private ReservationService reservationService;
    @Autowired 
    private StatusReservationService statusReservationService;
    @Autowired
    private PenaliteAdherantService penaliteAdherantService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private VReservationsAvecStatusActuelService RSA;
    @Autowired
    private ProlongementPretService prolongementPretService;
    @Autowired
    private StatusProlongementService statusProlongementService;
    @Autowired
    private VProlongementsAvecStatusActuelService PSA;

    @GetMapping("/prolonger")
    public String reserverExemplaire (
        HttpSession session,
        Model model
    ) {
        Adherant adherant = (Adherant) session.getAttribute("adherant");
        List<V_pretsAvecDateRetour> allPrets = vPretsAvecDateRetourService.readByAdherant(adherant.getId());

        model.addAttribute("allPrets", allPrets);
        return "prolongementAd";
    }

    @PostMapping("prolonger")
    public String getReservation (
        @RequestParam("id_pret") String str_pret,
        @RequestParam("id_adherant") String str_adherant,
        HttpSession session,
        Model model
    ) {

        int id_pret = Integer.parseInt(str_pret);
        int id_adherant = Integer.parseInt(str_adherant);

        Adherant adherant = (Adherant) session.getAttribute("adherant");
        Pret p = pretService.readById(id_pret).orElse(null);
        V_pretsAvecDateRetour p2 = vPretsAvecDateRetourService.readByPret(id_pret);

        List<V_pretsAvecDateRetour> allPrets = vPretsAvecDateRetourService.readByAdherant(id_adherant);
        
        model.addAttribute("allPrets", allPrets);
        V_pretsAvecDateRetour pret = vPretsAvecDateRetourService.readByPret(id_pret); 
        
        // condition 4
        // Nahazo penalite ve ilay olona ? 
        List<PenaliteAdherant> penalites = penaliteAdherantService.findByAdherant(adherant.getId());
        if (!penalites.isEmpty()) {
            int nbJour = penaliteAdherantService.totalPenalite(adherant.getId());
            Date dateDebut = penaliteAdherantService.dateDebutPenalite(adherant.getId());
            Date dateFin = penaliteAdherantService.ajouterJours(dateDebut, nbJour);
        
            if (dateDebut.before(pret.getDateRetourPrevue()) &&
                    dateFin.after(pret.getDateRetourPrevue())) {
                    model.addAttribute("error", "Vous ne pouvez pas encore réserver à cause d'une penalisation"); 
                    return "home";   
            }
        }

        // condition 1
        // A jour ve ny inscription any ?
        Inscription currInscription = inscriptionService.getCurrentInscription(adherant.getId());
        if (currInscription != null) {
            if (currInscription.getDateDebut().after(pret.getDateRetourPrevue()) ||
                    currInscription.getDatefin().before(pret.getDateRetourPrevue())) {
                    model.addAttribute("error",
                        "Vous devez vous réinscrire. " +
                        "Période actuelle : " +
                        "Début : " + currInscription.getDateDebut() + ", Fin : " + currInscription.getDatefin()  +
                        "Date de prolongement : " + pret.getDateRetourPrevue());
                    return "prologementAd";   
            }
        } else if (currInscription == null){
            model.addAttribute("error", "Vous devez vous reinscrire"); 
            return "prolongementAd";   
        }

        ProlongementPret pp = new ProlongementPret();
        pp.setPret(p);
        pp.setDateProlongement(p2.getDateRetourPrevue());
        pp = prolongementPretService.create(pp);

        TypeStatusPret typeStatusPret = typeStatusPretService.readById(1).orElse(null);
        
        StatusProlongement sp = new StatusProlongement();
        sp.setProlongementPret(pp);
        sp.setTypeStatusPret(typeStatusPret);
        sp = statusProlongementService.create(sp);
        
        model.addAttribute("success",
            "En attente de validation d'un bibliothécaire. " +
            "Période d'inscription : " +
            "Début : " + currInscription.getDateDebut() +
            ", Fin : " + currInscription.getDatefin() +
            ". Date de prolongement : " + pret.getDateRetourPrevue());
        return "prolongementAd";
    }

    @GetMapping("listeProlongement")
    public String getListeReservation (
        HttpSession session,
        Model model) {
        List<VProlongementsAvecStatusActuel> prolongements = PSA.findAll();

        model.addAttribute("prolongements", prolongements);
        return "ListeProlongement";
    }

    @PostMapping("validerPro")
    public String validerReservation(
        HttpSession session,
        Model model,
        @RequestParam("id_prolongement") String str_pro,
        @RequestParam("action") String str_action
        ) {
        
        int id_pro = Integer.parseInt(str_pro);
        ProlongementPret prolongement = prolongementPretService.readById(id_pro).orElse(null);
        V_pretsAvecDateRetour p = vPretsAvecDateRetourService.readById(prolongement.getPret().getId()).orElse(null);
        
        if (str_action.equals("valider")) { 
            TypeStatusPret typeStatusPret = typeStatusPretService.readById(3).orElse(null);
            StatusProlongement sp = new StatusProlongement();
            sp.setProlongementPret(prolongement);
            sp.setTypeStatusPret(typeStatusPret);
            sp = statusProlongementService.create(sp);

            StatusPret sp1 = new StatusPret();
            sp1.setPret(prolongement.getPret());
            sp1.setTypeStatusPret(typeStatusPret);
            sp1 = statusPretService.create(sp1);

            Pret pret = new Pret(prolongement.getPret().getAdherant(), prolongement.getPret().getExemplaire(), prolongement.getPret().getTypePret(), p.getDateRetourPrevue());
            pret = pretService.create(pret);
    
            TypeStatusPret type_StatusPret = typeStatusPretService.readById(1).orElse(null);
    
            StatusPret status = new StatusPret();
            status.setPret(pret);
            status.setTypeStatusPret(type_StatusPret);
            status = statusPretService.create(status);
        } else if (str_action.equals("refuser")) {
            TypeStatusPret typeStatusPret = typeStatusPretService.readById(4).orElse(null);
        
            StatusProlongement sp = new StatusProlongement();
            sp.setProlongementPret(prolongement);
            sp.setTypeStatusPret(typeStatusPret);
            sp = statusProlongementService.create(sp);
        }

        List<VProlongementsAvecStatusActuel> prolongements = PSA.findAll();

        model.addAttribute("prolongements", prolongements);
        return "ListeProlongement";
    }
}
