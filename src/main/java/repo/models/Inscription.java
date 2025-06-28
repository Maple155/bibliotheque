package repo.models;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Inscription")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;

    private Date dateInscription;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.actif;

    public enum Status { actif, inactif }

    public Inscription() {}

    public Inscription(Adherant adherant, Date dateInscription, Status status) {
        this.adherant = adherant;
        this.dateInscription = dateInscription;
        this.status = status;
    }

    // Getters and setters...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }

    public Date getDateInscription() { return dateInscription; }
    public void setDateInscription(Date dateInscription) { this.dateInscription = dateInscription; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}