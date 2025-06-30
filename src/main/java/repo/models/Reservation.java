package repo.models;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    private Date dateReservation;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.en_attente;

    public enum Statut { en_attente, annulee, effectuee }

    public Reservation() {}

    public Reservation(Adherant adherant, Exemplaire exemplaire, Date dateReservation, Statut statut) {
        this.adherant = adherant;
        this.exemplaire = exemplaire;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    // Getters and setters...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }

    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }

    public Date getDateReservation() { return dateReservation; }
    public void setDateReservation(Date dateReservation) { this.dateReservation = dateReservation; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }
}