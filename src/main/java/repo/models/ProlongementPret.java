package repo.models;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Prolongement_pret")
public class ProlongementPret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    private Date dateProlongement;
    private Date nouvelleDateRetour;

    public ProlongementPret() {}

    public ProlongementPret(Pret pret, Date dateProlongement, Date nouvelleDateRetour) {
        this.pret = pret;
        this.dateProlongement = dateProlongement;
        this.nouvelleDateRetour = nouvelleDateRetour;
    }

    // Getters and setters...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public Date getDateProlongement() { return dateProlongement; }
    public void setDateProlongement(Date dateProlongement) { this.dateProlongement = dateProlongement; }

    public Date getNouvelleDateRetour() { return nouvelleDateRetour; }
    public void setNouvelleDateRetour(Date nouvelleDateRetour) { this.nouvelleDateRetour = nouvelleDateRetour; }
}