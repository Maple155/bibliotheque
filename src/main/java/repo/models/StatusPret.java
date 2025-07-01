package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "status_pret")
public class StatusPret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret")
    private Pret pret;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status")
    private TypeStatusPret typeStatusPret;

    public StatusPret() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public TypeStatusPret getTypeStatusPret() { return typeStatusPret; }
    public void setTypeStatusPret(TypeStatusPret typeStatusPret) { this.typeStatusPret = typeStatusPret; }
}