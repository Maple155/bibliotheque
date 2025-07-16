package repo.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "ferie")
public class Ferie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date_ferie;

    public Ferie () {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDate_ferie() { return date_ferie; }
    public void setDate_ferie(Date date_ferie) { this.date_ferie = date_ferie; }
}
