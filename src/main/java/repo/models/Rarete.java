package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Rarete")
public class Rarete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( name = "type")
    private String type;

    public Rarete() {}

    public Rarete(String type) { this.type = type; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}