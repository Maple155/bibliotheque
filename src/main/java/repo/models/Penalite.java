package repo.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Penalite")
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Penalite() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}