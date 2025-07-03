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

    @Column(name = "date")
    private Date date;

    @Column(name = "nbJour")
    private int nbJour;
    
    public Penalite() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public int getNbJour() { return nbJour; }
    public void setNbJour(int nbJour) { this.nbJour = nbJour; }
}