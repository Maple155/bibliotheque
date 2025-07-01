package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "status_prolongement")
public class StatusProlongement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prolongement")
    private ProlongementPret prolongementPret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status")
    private TypeStatusPret typeStatusPret;

    public StatusProlongement() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public ProlongementPret getProlongementPret() { return prolongementPret; }
    public void setProlongementPret(ProlongementPret prolongementPret) { this.prolongementPret = prolongementPret; }

    public TypeStatusPret getTypeStatusPret() { return typeStatusPret; }
    public void setTypeStatusPret(TypeStatusPret typeStatusPret) { this.typeStatusPret = typeStatusPret; }
}