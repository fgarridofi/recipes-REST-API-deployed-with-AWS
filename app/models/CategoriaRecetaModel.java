package models;

import io.ebean.ExpressionList;
import io.ebean.Finder;
import io.ebean.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class CategoriaRecetaModel extends Model {

    public static final Finder<Long,CategoriaRecetaModel> finder = new Finder<>(CategoriaRecetaModel.class);

    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categorias")
    private List<RecetaModel> recetas;

    public static CategoriaRecetaModel findCategoriaByName(String name){
        return finder.query().where().ieq("name", name).findOne();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecetaModel> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<RecetaModel> recetas) {
        this.recetas = recetas;
    }
}
