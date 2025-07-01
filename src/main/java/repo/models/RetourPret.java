package repo.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "retour_pret")
public class RetourPret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret")
    private Pret pret;

    @Temporal(TemporalType.DATE)
    private Date dateRetour;

    public RetourPret() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public Date getDateRetour() { return dateRetour; }
    public void setDateRetour(Date dateRetour) { this.dateRetour = dateRetour; }
}