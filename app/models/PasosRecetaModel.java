package models;

import io.ebean.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PasosRecetaModel extends Model {
    @Id
    private Long id;

    private String pasosASeguir;


    @ManyToOne
    private RecetaModel parentReceta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasosASeguir() {
        return pasosASeguir;
    }

    public void setPasosASeguir(String pasosASeguir) {
        this.pasosASeguir = pasosASeguir;
    }

    public RecetaModel getParentReceta() {
        return parentReceta;
    }

    public void setParentReceta(RecetaModel parentReceta) {
        this.parentReceta = parentReceta;
    }
}
