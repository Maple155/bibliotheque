package repo.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Prolongement_pret")
public class ProlongementPret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    @Column(name = "date_prolongement")
    private Date dateProlongement;

    public ProlongementPret() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public Date getDateProlongement() { return dateProlongement; }
    public void setDateProlongement(Date dateProlongement) { this.dateProlongement = dateProlongement; }
}