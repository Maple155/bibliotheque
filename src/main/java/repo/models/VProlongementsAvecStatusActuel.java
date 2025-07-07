package repo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "v_prolongements_avec_status_actuel")
public class VProlongementsAvecStatusActuel {

    @Id
    @Column(name = "id_prolongement")
    private Integer idProlongement;

    @Column(name = "id_pret")
    private Integer idPret;

    @Column(name = "date_prolongement")
    private Date dateProlongement;

    @Column(name = "statut_actuel")
    private String statutActuel;

    @Column(name = "id_Adherant")
    private int idAdherant;

    @Column(name = "nom_adherant")
    private String nomAdherant;

    @Column(name = "prenom_adherant")
    private String prenomAdherant;

    @Column(name = "titre_livre")
    private String titreLivre;

    @Column(name = "numero_exemplaire")
    private Integer numeroExemplaire;

    // Getters & Setters

    public VProlongementsAvecStatusActuel() {
    }

    public Integer getIdProlongement() {
        return idProlongement;
    }

    public void setIdProlongement(Integer idProlongement) {
        this.idProlongement = idProlongement;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public Date getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(Date dateProlongement) {
        this.dateProlongement = dateProlongement;
    }

    public String getStatutActuel() {
        return statutActuel;
    }

    public void setStatutActuel(String statutActuel) {
        this.statutActuel = statutActuel;
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

    public String getTitreLivre() {
        return titreLivre;
    }

    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    public Integer getNumeroExemplaire() {
        return numeroExemplaire;
    }

    public void setNumeroExemplaire(Integer numeroExemplaire) {
        this.numeroExemplaire = numeroExemplaire;
    }

    public int getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(int idAdherant) {
        this.idAdherant = idAdherant;
    }
}