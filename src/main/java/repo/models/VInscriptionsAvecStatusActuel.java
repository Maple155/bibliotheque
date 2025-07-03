package repo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "v_inscriptions_avec_status_actuel")
public class VInscriptionsAvecStatusActuel {

    @Id
    @Column(name = "id_inscription")
    private Integer idInscription;

    @Column(name = "id_adherant")
    private Integer idAdherant;

    @Column(name = "date_inscription")
    private Date dateInscription;

    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "statut_actuel")
    private String statutActuel;

    // Getters & Setters

    public Integer getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Integer idInscription) {
        this.idInscription = idInscription;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatutActuel() {
        return statutActuel;
    }

    public void setStatutActuel(String statutActuel) {
        this.statutActuel = statutActuel;
    }
}