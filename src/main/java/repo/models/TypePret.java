package repo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Type_pret")
public class TypePret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    public TypePret() {}
    public TypePret(String type) { this.type = type; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}