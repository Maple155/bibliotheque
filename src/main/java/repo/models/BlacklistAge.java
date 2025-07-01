package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "BlacklistAge")
public class BlacklistAge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "age_min", nullable = false)
    private int ageMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    public BlacklistAge() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAgeMin() { return ageMin; }
    public void setAgeMin(int ageMin) { this.ageMin = ageMin; }

    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
}