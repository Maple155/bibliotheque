package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "duree_inscription")
public class DureeInscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_adherant")
    private TypeAdherant typeAdherant;

    private Integer duree;

    public DureeInscription() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TypeAdherant getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(TypeAdherant typeAdherant) { this.typeAdherant = typeAdherant; }

    public Integer getDuree() { return duree; }
    public void setDuree(Integer duree) { this.duree = duree; }
}