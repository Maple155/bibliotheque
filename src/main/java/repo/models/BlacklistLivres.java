package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "BlacklistLivres")
public class BlacklistLivres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_adherant", nullable = false)
    private TypeAdherant typeAdherant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    // Getters & Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer obj) {
        id = obj;
    }

    public TypeAdherant getTypeAdherant() {
        return typeAdherant;
    }
    public void setTypeAdherant(TypeAdherant obj) {
        typeAdherant = obj;
    }

    public Livre getLivre() {
        return livre;
    }
    public void setLivre(Livre obj) {
        livre = obj;
    }
}