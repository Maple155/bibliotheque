package repo.models;

import java.sql.Date;

public class PenaliteAdherant {
    private Integer idAdherant;
    private String nom;
    private String prenom;
    private Integer idPret;
    private Date dateDebut;
    private Date datePenalite;
    private Integer nbJour;

    public PenaliteAdherant() { }

    public Integer getIdAdherant() { return idAdherant; }
    public void setIdAdherant(Integer idAdherant) { this.idAdherant = idAdherant; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public Integer getIdPret() { return idPret; }
    public void setIdPret(Integer idPret) { this.idPret = idPret; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDatePenalite() { return datePenalite; }
    public void setDatePenalite(Date datePenalite) { this.datePenalite = datePenalite; }

    public Integer getNbJour() { return nbJour; }
    public void setNbJour(Integer nbJour) { this.nbJour = nbJour; }
}