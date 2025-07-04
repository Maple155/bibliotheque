package repo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "penalite_adherant")
public class PenaliteAdherant {

    @Id
    @Column(name = "id_pret")
    private Integer idPret;

    @Column(name = "id_adherant")
    private Integer idAdherant;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "date_penalite")
    private Date datePenalite;

    @Column(name = "nbJour")
    private Integer nbJour;

    // Getters & Setters

    public PenaliteAdherant() {
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDatePenalite() {
        return datePenalite;
    }

    public void setDatePenalite(Date datePenalite) {
        this.datePenalite = datePenalite;
    }

    public Integer getNbJour() {
        return nbJour;
    }

    public void setNbJour(Integer nbJour) {
        this.nbJour = nbJour;
    }
}