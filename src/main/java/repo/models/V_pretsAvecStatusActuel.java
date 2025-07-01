package repo.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "v_prets_avec_status_actuel")
public class V_pretsAvecStatusActuel {
    @Id
    @Column(name = "id_pret")
    private Integer idPret;

    @Column(name = "id_adherant")
    private Integer idAdherant;

    @Column(name = "id_exemplaire")
    private Integer idExemplaire;

    @Column(name = "type_pret")
    private Integer typePret;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "statut_actuel")
    private String statutActuel;

    // Getters & Setters
    public Integer getIdPret() { return idPret; }
    public void setIdPret(Integer idPret) { this.idPret = idPret; }

    public Integer getIdAdherant() { return idAdherant; }
    public void setIdAdherant(Integer idAdherant) { this.idAdherant = idAdherant; }

    public Integer getIdExemplaire() { return idExemplaire; }
    public void setIdExemplaire(Integer idExemplaire) { this.idExemplaire = idExemplaire; }

    public Integer getTypePret() { return typePret; }
    public void setTypePret(Integer typePret) { this.typePret = typePret; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public String getStatutActuel() { return statutActuel; }
    public void setStatutActuel(String statutActuel) { this.statutActuel = statutActuel; }
}