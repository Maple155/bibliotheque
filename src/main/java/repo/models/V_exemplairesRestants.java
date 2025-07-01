package repo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "v_exemplaires_restants")
public class V_exemplairesRestants {

    @Id
    @Column(name = "id_exemplaire") // chaque exemplaire est unique
    private Integer idExemplaire;

    @Column(name = "id_livre")
    private Integer idLivre;

    @Column(name = "numero_exemplaire")
    private Integer numeroExemplaire;

    @Column(name = "titre")
    private String titre;

    @Column(name = "nbPage")
    private Integer nbPage;

    @Column(name = "auteur")
    private String auteur;

    @Column(name = "datePublication")
    private Date datePublication;

    @Column(name = "nbChapitre")
    private Integer nbChapitre;

    @Column(name = "langue")
    private String langue;

    @Column(name = "editeur")
    private String editeur;

    @Column(name = "genre")
    private String genre;

    // Getters & Setters

    public Integer getIdExemplaire() { return idExemplaire; }
    public void setIdExemplaire(Integer idExemplaire) { this.idExemplaire = idExemplaire; }

    public Integer getIdLivre() { return idLivre; }
    public void setIdLivre(Integer idLivre) { this.idLivre = idLivre; }

    public Integer getNumeroExemplaire() { return numeroExemplaire; }
    public void setNumeroExemplaire(Integer numeroExemplaire) { this.numeroExemplaire = numeroExemplaire; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public Integer getNbPage() { return nbPage; }
    public void setNbPage(Integer nbPage) { this.nbPage = nbPage; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public Date getDatePublication() { return datePublication; }
    public void setDatePublication(Date datePublication) { this.datePublication = datePublication; }

    public Integer getNbChapitre() { return nbChapitre; }
    public void setNbChapitre(Integer nbChapitre) { this.nbChapitre = nbChapitre; }

    public String getLangue() { return langue; }
    public void setLangue(String langue) { this.langue = langue; }

    public String getEditeur() { return editeur; }
    public void setEditeur(String editeur) { this.editeur = editeur; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
