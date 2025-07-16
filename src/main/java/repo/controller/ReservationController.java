package repo.controller;

import java.lang.reflect.Type;
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
public class ReservationController {
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

    @GetMapping("/reserver")
    public String reserverExemplaire(
            HttpSession session,
            Model model) {
        List<Exemplaire> exemplaires = exemplaireService.findAllWithLivre();
        List<TypePret> typePrets = typePretService.read();

        model.addAttribute("exemplaires", exemplaires);
        model.addAttribute("typesPret", typePrets);
        return "reservation";
    }

    @PostMapping("reserverExemplaire")
    public String getReservation(
            @RequestParam("id_exemplaire") String str_id,
            @RequestParam("date_res") String str_date,
            @RequestParam("type_pret") String str_id_type,
            HttpSession session,
            Model model) {

        int id_type_pret = Integer.parseInt(str_id_type);
        int id_exemplaire = Integer.parseInt(str_id);
        Exemplaire exemplaire = exemplaireService.readById(id_exemplaire).orElse(null);

        Date date_res = Date.valueOf(str_date);

        Adherant adherant = (Adherant) session.getAttribute("adherant");
        TypePret tp = typePretService.readById(id_type_pret).get();
        List<Exemplaire> exemplaires = exemplaireService.findAllWithLivre();
        List<TypePret> typePrets = typePretService.read();
        List<ConditionPret> conditions = conditionPretService.read();
        List<VReservationsAvecStatusActuel> reservations = RSA.findByAdherantByStatus(adherant.getId(), "en attente");

        model.addAttribute("typesPret", typePrets);
        model.addAttribute("exemplaires", exemplaires);

        // if (conditions != null) {
        //     for (ConditionPret condition : conditions) {
        //         if (adherant.getTypeAdherant().getType().equals(condition.getTypeAdherant().getType())) {
        //             if (condition.getReservationMax() <= reservations.size()) {
        //                 model.addAttribute("error", "Vous avez fait trop de reservation");
        //                 return "reservation";
        //             }
        //         }
        //     }
        // }

        if (conditionPretService.isFerie(date_res.toLocalDate())) {
            model.addAttribute("error", date_res.toString() + " est un jour ferie");
            return "home";
        }

        // condition 1
        // Mbola any aminazy ve le boky ?
        V_pretsAvecDateRetour prets = vPretsAvecDateRetourService.readByPretEtAdherant(id_exemplaire, adherant.getId());
        if (prets != null) {
            RetourPret date_retour = retourPretService.readByPret(prets.getIdPret());
            if (date_retour != null) {
                int compare = date_retour.getDateRetour().compareTo(date_res);
                if (compare < 0) {
                    model.addAttribute("error", "Vous ne pouvez pas encore reserver sur cette date");
                    return "reservation";
                }
            } else {
                int compare1 = prets.getDateRetourPrevue().compareTo(date_res);
                if (compare1 < 0) {
                    model.addAttribute("error", "Vous ne pouvez pas encore reserver sur cette date");
                    return "reservation";
                }
            }
        }

        // condition 2
        // Mbola any am olona ve le boky ?
        List<V_pretsAvecDateRetour> prets_list = vPretsAvecDateRetourService.readByExemplaire(id_exemplaire);
        if (!prets_list.isEmpty()) {
            for (V_pretsAvecDateRetour pret : prets_list) {
                if (pret.getDateDebut().compareTo(date_res) <= 0 &&
                        pret.getDateRetourPrevue().compareTo(date_res) >= 0) {
                    model.addAttribute("error", "Vous ne pouvez pas encore réserver sur cette date");
                    return "reservation";
                }
            }

        }

        // condition 3
        // Nahazo penalite ve ilay olona ?
        List<PenaliteAdherant> penalites = penaliteAdherantService.findByAdherant(adherant.getId());
        if (!penalites.isEmpty()) {
            int nbJour = penaliteAdherantService.totalPenalite(adherant.getId());
            Date dateDebut = penaliteAdherantService.dateDebutPenalite(adherant.getId());
            Date dateFin = penaliteAdherantService.ajouterJours(dateDebut, nbJour);

            if (dateDebut.before(date_res) && dateFin.after(date_res)) {
                model.addAttribute("error",
                    "Vous ne pouvez pas encore réserver à cause d'une pénalisation. " +
                    "Période de pénalité : Début : " + dateDebut +
                    ", Fin : " + dateFin +
                    ". Date de réservation : " + date_res);
                return "reservation";
            }
            
        }

        // condition 4
        // A jour ve ny inscription any ?
        Inscription currInscription = inscriptionService.getCurrentInscription(adherant.getId());
        if (currInscription != null) {
            if (currInscription.getDateDebut().after(date_res) ||
                    currInscription.getDatefin().before(date_res)) {
                model.addAttribute("error",
                        "Vous devez vous réinscrire. " +
                                "Période d'inscription : Début : " + currInscription.getDateDebut() +
                                ", Fin : " + currInscription.getDatefin() +
                                ", Date de réservation : " + date_res);
                return "reservation";
            }
        } else if (currInscription == null) {
            model.addAttribute("error", "Vous devez vous reinscrire");
            return "reservation";
        }

        Reservation reservation = new Reservation();
        reservation.setAdherant(adherant);
        reservation.setDateReservation(date_res);
        reservation.setExemplaire(exemplaire);
        reservation.setTypePret(tp);
        reservation = reservationService.create(reservation);

        TypeStatusPret typeStatusPret = typeStatusPretService.readById(1).orElse(null);

        StatusReservation statusReservation = new StatusReservation();
        statusReservation.setReservation(reservation);
        statusReservation.setTypeStatusPret(typeStatusPret);
        statusReservation = statusReservationService.create(statusReservation);

        model.addAttribute("success",
                "En attente de validation d'un bibliothécaire. " +
                        "Période d'inscription : Début : " +
                        (currInscription != null ? currInscription.getDateDebut() : "null") +
                        ", Fin : " +
                        (currInscription != null ? currInscription.getDatefin() : "null") +
                        ", Date de réservation : " + date_res);
        return "reservation";
    }

    @GetMapping("listeReservation")
    public String getListeReservation(
            HttpSession session,
            Model model) {
        List<VReservationsAvecStatusActuel> reservations = RSA.findAll();

        model.addAttribute("reservations", reservations);
        return "listeReservation";
    }

    @PostMapping("validerRes")
    public String validerReservation(
            HttpSession session,
            Model model,
            @RequestParam("id_reservation") String str_res,
            @RequestParam("action") String str_action) {

        int id_res = Integer.parseInt(str_res);
        Reservation res = reservationService.readById(id_res).orElse(null);

        Adherant adherant = adherantService.readById(res.getAdherant().getId()).orElse(null);
        Exemplaire exemplaire = exemplaireService.readById(res.getExemplaire().getId()).orElse(null);
        TypePret tp = typePretService.readById(res.getTypePret().getId()).orElse(null);

        if (str_action.equals("valider")) {
            TypeStatusPret typeStatusPret = typeStatusPretService.readById(3).orElse(null);

            StatusReservation statusReservation = new StatusReservation();
            statusReservation.setReservation(res);
            statusReservation.setTypeStatusPret(typeStatusPret);
            statusReservation = statusReservationService.create(statusReservation);

            Pret pret = new Pret(adherant, exemplaire, tp, res.getDateReservation());
            pret = pretService.create(pret);

            TypeStatusPret type_StatusPret = typeStatusPretService.readById(2).orElse(null);

            StatusPret status = new StatusPret();
            status.setPret(pret);
            status.setTypeStatusPret(type_StatusPret);
            status = statusPretService.create(status);
        } else if (str_action.equals("refuser")) {
            TypeStatusPret typeStatusPret = typeStatusPretService.readById(4).orElse(null);

            StatusReservation statusReservation = new StatusReservation();
            statusReservation.setReservation(res);
            statusReservation.setTypeStatusPret(typeStatusPret);
            statusReservation = statusReservationService.create(statusReservation);
        }

        List<VReservationsAvecStatusActuel> reservations = RSA.findAll();

        model.addAttribute("reservations", reservations);
        return "listeReservation";
    }
}
