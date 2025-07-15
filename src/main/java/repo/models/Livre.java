package repo.models;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rarete")
    @JsonIgnore
    private Rarete rarete;

    public Livre() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

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

    public Rarete getRarete() { return rarete; }
    public void setRarete(Rarete rarete) { this.rarete = rarete; }
}