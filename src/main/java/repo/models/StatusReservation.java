package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "status_reservation")
public class StatusReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status")
    private TypeStatusPret typeStatusPret;

    public StatusReservation() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }

    public TypeStatusPret getTypeStatusPret() { return typeStatusPret; }
    public void setTypeStatusPret(TypeStatusPret typeStatusPret) { this.typeStatusPret = typeStatusPret; }
}