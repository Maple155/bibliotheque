package repo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "Condition_pret")
public class ConditionPret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_adherant", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TypeAdherant typeAdherant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_pret", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TypePret typePret;

    @Column(name = "exemplaire_max")
    private Integer exemplaireMax;

    @Column(name = "duree_max")
    private Integer dureeMax;

    @Column(name = "prolongement_max")
    private Integer prolongementMax;
    
    @Column(name = "reservation_max")
    private Integer reservationMax;

    @Column(name = "penalite")
    private Integer penalite;

    public ConditionPret() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TypeAdherant getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(TypeAdherant typeAdherant) { this.typeAdherant = typeAdherant; }

    public TypePret getTypePret() { return typePret; }
    public void setTypePret(TypePret typePret) { this.typePret = typePret; }

    public Integer getExemplaireMax() { return exemplaireMax; }
    public void setExemplaireMax(Integer exemplaireMax) { this.exemplaireMax = exemplaireMax; }

    public Integer getDureeMax() { return dureeMax; }
    public void setDureeMax(Integer dureeMax) { this.dureeMax = dureeMax; }

    public Integer getProlongementMax() { return prolongementMax; }
    public void setProlongementMax(Integer prolongementMax) { this.prolongementMax = prolongementMax; }

    public Integer getReservationMax() { return reservationMax; }
    public void setReservationMax(Integer reservationMax) { this.reservationMax = reservationMax; }

    public Integer getPenalite() { return penalite; }
    public void setPenalite(Integer penalite) { this.penalite = penalite; }
}