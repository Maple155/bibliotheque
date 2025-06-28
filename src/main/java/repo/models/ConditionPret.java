package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Condition_pret")
public class ConditionPret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_adherant", nullable = false)
    private TypeAdherant typeAdherant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_pret", nullable = false)
    private TypePret typePret;

    private Integer exemplaireMax;
    private Integer dureeMax;

    public ConditionPret() {}

    public ConditionPret(TypeAdherant typeAdherant, TypePret typePret, Integer exemplaireMax, Integer dureeMax) {
        this.typeAdherant = typeAdherant;
        this.typePret = typePret;
        this.exemplaireMax = exemplaireMax;
        this.dureeMax = dureeMax;
    }

    // Getters and setters...

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
}