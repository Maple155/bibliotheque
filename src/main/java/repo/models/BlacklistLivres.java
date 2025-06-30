package repo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BlacklistLivres")
public class BlacklistLivres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "id_adherant", nullable = false)
    Integer idAdherant;

    @Column(name = "id_livre", nullable = false)
    Integer idLivre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer obj) {
        id = obj;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer obj) {
        idAdherant = obj;
    }

    public Integer getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Integer obj) {
        idLivre = obj;
    }
}