package repo.models;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Penalite")
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    private Date date;

    public Penalite() {}

    public Penalite(Pret pret, Date date) {
        this.pret = pret;
        this.date = date;
    }

    // Getters and setters...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}