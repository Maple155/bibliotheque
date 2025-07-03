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
        return "prologenementAd";
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
        // A jour ve ny inscription any ?
        Inscription currInscription = inscriptionService.getCurrentInscription(adherant.getId());
        if (currInscription != null) {
            if (currInscription.getDateDebut().compareTo(pret.getDateRetourPrevue()) <= 0 &&
                    currInscription.getDatefin().compareTo(pret.getDateRetourPrevue()) >= 0) {
                    model.addAttribute("error", "Vous devez vous reinscrire"); 
                    return "reservation";   
            }
        } else if (currInscription == null){
            model.addAttribute("error", "Vous devez vous reinscrire"); 
            return "reservation";   
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
        
        model.addAttribute("success", "En attente de validation d'un bibliothecaire");
        return "prologenementAd";
    }

    @GetMapping("listeProlongement")
    public String getListeReservation (
        HttpSession session,
        Model model) {
        List<VProlongementsAvecStatusActuel> prolongements = PSA.findAll();

        model.addAttribute("prolongements", prolongements);
        return "listeProlongement";
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
        return "listeProlongement";
    }
}
