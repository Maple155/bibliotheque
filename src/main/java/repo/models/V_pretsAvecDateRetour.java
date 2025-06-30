package repo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "v_prets_avec_date_retour")
public class V_pretsAvecDateRetour {

    @Id
    @Column(name = "id_pret")
    Integer idPret;

    @Column(name = "id_adherant")
    Integer idAdherant;

    @Column(name = "id_exemplaire")
    Integer idExemplaire;

    @Column(name = "nb_exemplaire")
    Integer nbExemplaire;

    @Column(name = "type_pret")
    Integer typePret;

    @Column(name = "date_debut")
    Date dateDebut;

    @Column(name = "duree_max")
    Integer dureeMax;

    @Column(name = "date_retour_prevue")
    Date dateRetourPrevue;

    // Getters and Setters

    public Integer getIdPret() { return idPret; }
    public void setIdPret(Integer obj) { idPret = obj; }

    public Integer getIdAdherant() { return idAdherant; }
    public void setIdAdherant(Integer obj) { idAdherant = obj; }

    public Integer getIdExemplaire() { return idExemplaire; }
    public void setIdExemplaire(Integer obj) { idExemplaire = obj; }

    public Integer getNbExemplaire() { return nbExemplaire; }
    public void setNbExemplaire(Integer obj) { nbExemplaire = obj; }

    public Integer getTypePret() { return typePret; }
    public void setTypePret(Integer obj) { typePret = obj; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date obj) { dateDebut = obj; }

    public Integer getDureeMax() { return dureeMax; }
    public void setDureeMax(Integer obj) { dureeMax = obj; }

    public Date getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(Date obj) { dateRetourPrevue = obj; }
}