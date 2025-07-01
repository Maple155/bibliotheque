package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "BlacklistLivres")
public class BlacklistLivres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_adherant", nullable = false)
    private TypeAdherant typeAdherant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    public BlacklistLivres() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TypeAdherant getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(TypeAdherant typeAdherant) { this.typeAdherant = typeAdherant; }

    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
}