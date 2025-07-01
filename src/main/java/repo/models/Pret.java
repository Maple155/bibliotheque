package repo.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pret")
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_pret", nullable = false)
    private TypePret typePret;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    public Pret() {}

    public Pret(Adherant adherant, Exemplaire exemplaire, TypePret typePret, Date dateDebut) {
        this.adherant = adherant;
        this.exemplaire = exemplaire;
        this.typePret = typePret;
        this.dateDebut = dateDebut;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }

    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }

    public TypePret getTypePret() { return typePret; }
    public void setTypePret(TypePret typePret) { this.typePret = typePret; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
}