package repo.models;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Inscription")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherant", nullable = false)
    @JsonIgnore
    private Adherant adherant;

    @Column(name = "date_debut")
    private Date datedebut;

    @Column(name = "date_fin")
    private Date datefin;

    public Inscription() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }

    public Date getDateDebut() { return datedebut; }
    public void setDateDebut(Date datedebut) { this.datedebut = datedebut; }

    public Date getDatefin() { return datefin; }
    public void setDatefin(Date datefin) { this.datefin = datefin; }
}