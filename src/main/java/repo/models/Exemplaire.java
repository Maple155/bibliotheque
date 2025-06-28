package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Exemplaire")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    private Integer nbExemplaire;

    public Exemplaire() {}

    public Exemplaire(Livre livre, Integer nbExemplaire) {
        this.livre = livre;
        this.nbExemplaire = nbExemplaire;
    }

    // Getters and setters...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }

    public Integer getNbExemplaire() { return nbExemplaire; }
    public void setNbExemplaire(Integer nbExemplaire) { this.nbExemplaire = nbExemplaire; }
}