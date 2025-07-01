package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "status_inscription")
public class StatusInscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status")
    private TypeStatusInscription typeStatusInscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inscription")
    private Inscription inscription;

    public StatusInscription() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TypeStatusInscription getTypeStatusInscription() { return typeStatusInscription; }
    public void setTypeStatusInscription(TypeStatusInscription typeStatusInscription) { this.typeStatusInscription = typeStatusInscription; }

    public Inscription getInscription() { return inscription; }
    public void setInscription(Inscription inscription) { this.inscription = inscription; }
}