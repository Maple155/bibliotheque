package repo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "v_reservations_avec_status_actuel")
public class VReservationsAvecStatusActuel {

    @Id
    @Column(name = "id_reservation")
    private Integer idReservation;

    @Column(name = "id_adherant")
    private Integer idAdherant;

    @Column(name = "nom_adherant")
    private String nomAdherant;

    @Column(name = "prenom_adherant")
    private String prenomAdherant;

    @Column(name = "id_exemplaire")
    private Integer idExemplaire;

    @Column(name = "numero_exemplaire")
    private Integer numeroExemplaire;

    @Column(name = "date_reservation")
    private Date dateReservation;

    @Column(name = "statut_actuel")
    private String statutActuel;

    // Getters & Setters

    public VReservationsAvecStatusActuel() {
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

    public String getNomAdherant() {
        return nomAdherant;
    }

    public void setNomAdherant(String nomAdherant) {
        this.nomAdherant = nomAdherant;
    }

    public String getPrenomAdherant() {
        return prenomAdherant;
    }

    public void setPrenomAdherant(String prenomAdherant) {
        this.prenomAdherant = prenomAdherant;
    }

    public Integer getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Integer getNumeroExemplaire() {
        return numeroExemplaire;
    }

    public void setNumeroExemplaire(Integer numeroExemplaire) {
        this.numeroExemplaire = numeroExemplaire;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatutActuel() {
        return statutActuel;
    }

    public void setStatutActuel(String statutActuel) {
        this.statutActuel = statutActuel;
    }
}