package repo.models;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Adherant")
public class Adherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_adherant", nullable = false)
    private TypeAdherant typeAdherant;

    private String nom;
    private String prenom;
    private Date naissance;

    public Adherant() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TypeAdherant getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(TypeAdherant typeAdherant) { this.typeAdherant = typeAdherant; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public Date getNaissance() { return naissance; }
    public void setNaissance(Date naissance) { this.naissance = naissance; }
}