package repo.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Historique_Pret")
public class HistoriquePret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_pret", nullable = false)
    private TypePret typePret;

    @Column(name = "date_debut")
    Date dateDebut;

    @Column(name = "date_retour")
    Date dateRetour;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer obj) { id = obj; }

    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant typeAdherant) { this.adherant = typeAdherant; }

    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire livre) { this.exemplaire = livre; }

    public TypePret getTypePret() { return typePret; }
    public void setTypePret(TypePret typePret) { this.typePret = typePret; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date obj) { dateDebut = obj; }

    public Date getDateRetour() { return dateRetour; }
    public void setDateRetour(Date obj) { dateRetour = obj; }
}