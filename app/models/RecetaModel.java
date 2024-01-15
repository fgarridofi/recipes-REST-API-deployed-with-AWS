package models;

import io.ebean.ExpressionList;
import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecetaModel extends Model {

    public static final Finder<Long,RecetaModel> finder = new Finder<>(RecetaModel.class);
    @Id
    private Long id;

    private String name;

    private Integer time;

    private Integer dificultad;



    //Una receta tiene un único video asociado. Relación 1-1
    @OneToOne(cascade = CascadeType.ALL)
    private VideoRecetaModel video;

    //Una receta podría estar compuesta por una secuencia de pasos, y cada paso está asociado a una única receta.Relación 1-N
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentReceta")
    private List<PasosRecetaModel> pasos = new ArrayList<>();

    //Una receta podría pertenecer a múltiples categorías(Entrante,principal,desayuno...), y una categoría podría tener varias recetas.Relación N-M
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CategoriaRecetaModel> categorias = new ArrayList<>();
    @Version
    private Long version;

    @WhenCreated
    private Timestamp whenCreated;

    @WhenModified
    private Timestamp whenModified;

    //metodos find
    public static RecetaModel findById(Long id){
        return finder.byId(id);
    }

    public static List<RecetaModel> findAll(){
        return finder.all();
    }

    public static List<RecetaModel> findByName(String name,int page){
        ExpressionList<RecetaModel> res = finder.query().where()
                .ieq("name", name)
                .orderBy("name")
                .setFirstRow(10*page).setMaxRows((10*page)+10);

        return res.findList();
    }

    // getter y setter

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Timestamp whenCreated) {
        this.whenCreated = whenCreated;
    }

    public Timestamp getWhenModified() {
        return whenModified;
    }

    public void setWhenModified(Timestamp whenModified) {
        this.whenModified = whenModified;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }

    public VideoRecetaModel getVideo() {
        return video;
    }

    public void setVideo(VideoRecetaModel video) {
        video.setParentReceta(this);
        this.video = video;
    }

    public List<PasosRecetaModel> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasosRecetaModel> pasos) {
        this.pasos = pasos;
    }

    public List<CategoriaRecetaModel> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaRecetaModel> categorias) {
        this.categorias = categorias;
    }

    public void addPaso(PasosRecetaModel pasos) {
        pasos.setParentReceta(this);
        this.pasos.add(pasos);
    }

    public void addCategoria(String categoriaName) {

        //si no existe esa categória la creo.
        CategoriaRecetaModel cat = CategoriaRecetaModel.findCategoriaByName(categoriaName);
        if(cat == null){
            cat = new CategoriaRecetaModel();
            cat.setName(categoriaName);
        }

        //si ya existe, la añado.
        cat.getRecetas().add(this);
        this.categorias.add(cat);
    }
}
