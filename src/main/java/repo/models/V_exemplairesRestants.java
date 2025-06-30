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
    @Column(name = "id")
    Integer id;

    @Column(name = "id_exemplaire")
    Integer id_exemplaire;

    @Column(name = "titre")
    String titre;

    @Column(name = "nbPage")
    Integer nbPage;

    @Column(name = "auteur")
    String auteur;

    @Column(name = "datePublication")
    Date datePublication;

    @Column(name = "nbChapitre")
    Integer nbChapitre;

    @Column(name = "langue")
    String langue;

    @Column(name = "editeur")
    String editeur;

    @Column(name = "genre")
    String genre;

    @Column(name = "nb_exemplaires_totaux")
    Integer nbExemplairesTotaux;

    @Column(name = "nb_exemplaires_pretes")
    Integer nbExemplairesPretes;

    @Column(name = "nb_exemplaires_restants")
    Integer nbExemplairesRestants;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer obj) { id = obj; }

    public String getTitre() { return titre; }
    public void setTitre(String obj) { titre = obj; }

    public Integer getNbPage() { return nbPage; }
    public void setNbPage(Integer obj) { nbPage = obj; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String obj) { auteur = obj; }

    public Date getDatePublication() { return datePublication; }
    public void setDatePublication(Date obj) { datePublication = obj; }

    public Integer getNbChapitre() { return nbChapitre; }
    public void setNbChapitre(Integer obj) { nbChapitre = obj; }

    public String getLangue() { return langue; }
    public void setLangue(String obj) { langue = obj; }

    public String getEditeur() { return editeur; }
    public void setEditeur(String obj) { editeur = obj; }

    public String getGenre() { return genre; }
    public void setGenre(String obj) { genre = obj; }

    public Integer getNbExemplairesTotaux() { return nbExemplairesTotaux; }
    public void setNbExemplairesTotaux(Integer obj) { nbExemplairesTotaux = obj; }

    public Integer getNbExemplairesPretes() { return nbExemplairesPretes; }
    public void setNbExemplairesPretes(Integer obj) { nbExemplairesPretes = obj; }

    public Integer getNbExemplairesRestants() { return nbExemplairesRestants; }
    public void setNbExemplairesRestants(Integer obj) { nbExemplairesRestants = obj; }

    public Integer getId_exemplaire() { return id_exemplaire; }
    public void setId_exemplaire(Integer id_exemplaire) { this.id_exemplaire = id_exemplaire; }
}