package repo.models;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "v_prets_avec_date_retour")
public class V_pretsAvecDateRetour {
    @Id
    @Column(name = "id_pret")
    private Integer idPret;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "id_adherant")
    private Integer idAdherant;

    @Column(name = "adherant_nom")
    private String adherantNom;

    @Column(name = "adherant_prenom")
    private String adherantPrenom;

    @Column(name = "id_type_adherant")
    private Integer idTypeAdherant;

    @Column(name = "type_adherant")
    private String typeAdherant;

    @Column(name = "id_exemplaire")
    private Integer idExemplaire;

    @Column(name = "numero_exemplaire")
    private Integer numeroExemplaire;

    @Column(name = "id_livre")
    private Integer idLivre;

    @Column(name = "livre_titre")
    private String livreTitre;

    @Column(name = "id_type_pret")
    private Integer idTypePret;

    @Column(name = "type_pret")
    private String typePret;

    @Column(name = "exemplaire_max")
    private Integer exemplaireMax;

    @Column(name = "duree_max")
    private Integer dureeMax;

    @Column(name = "date_retour_prevue")
    private Date dateRetourPrevue;

    @Column(name = "id_status_courant")
    private Integer idStatusCourant;

    @Column(name = "status_courant")
    private String statusCourant;

    // Getters & Setters

    public Integer getIdPret() { return idPret; }
    public void setIdPret(Integer idPret) { this.idPret = idPret; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Integer getIdAdherant() { return idAdherant; }
    public void setIdAdherant(Integer idAdherant) { this.idAdherant = idAdherant; }

    public String getAdherantNom() { return adherantNom; }
    public void setAdherantNom(String adherantNom) { this.adherantNom = adherantNom; }

    public String getAdherantPrenom() { return adherantPrenom; }
    public void setAdherantPrenom(String adherantPrenom) { this.adherantPrenom = adherantPrenom; }

    public Integer getIdTypeAdherant() { return idTypeAdherant; }
    public void setIdTypeAdherant(Integer idTypeAdherant) { this.idTypeAdherant = idTypeAdherant; }

    public String getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(String typeAdherant) { this.typeAdherant = typeAdherant; }

    public Integer getIdExemplaire() { return idExemplaire; }
    public void setIdExemplaire(Integer idExemplaire) { this.idExemplaire = idExemplaire; }

    public Integer getNumeroExemplaire() { return numeroExemplaire; }
    public void setNumeroExemplaire(Integer numeroExemplaire) { this.numeroExemplaire = numeroExemplaire; }

    public Integer getIdLivre() { return idLivre; }
    public void setIdLivre(Integer idLivre) { this.idLivre = idLivre; }

    public String getLivreTitre() { return livreTitre; }
    public void setLivreTitre(String livreTitre) { this.livreTitre = livreTitre; }

    public Integer getIdTypePret() { return idTypePret; }
    public void setIdTypePret(Integer idTypePret) { this.idTypePret = idTypePret; }

    public String getTypePret() { return typePret; }
    public void setTypePret(String typePret) { this.typePret = typePret; }

    public Integer getExemplaireMax() { return exemplaireMax; }
    public void setExemplaireMax(Integer exemplaireMax) { this.exemplaireMax = exemplaireMax; }

    public Integer getDureeMax() { return dureeMax; }
    public void setDureeMax(Integer dureeMax) { this.dureeMax = dureeMax; }

    public Date getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(Date dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }

    public Integer getIdStatusCourant() { return idStatusCourant; }
    public void setIdStatusCourant(Integer idStatusCourant) { this.idStatusCourant = idStatusCourant; }

    public String getStatusCourant() { return statusCourant; }
    public void setStatusCourant(String statusCourant) { this.statusCourant = statusCourant; }
}