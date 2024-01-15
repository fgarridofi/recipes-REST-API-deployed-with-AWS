package views;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import models.CategoriaRecetaModel;
import models.PasosRecetaModel;
import models.RecetaModel;
import models.VideoRecetaModel;
import org.hibernate.validator.constraints.URL;
import play.data.validation.Constraints;
import play.libs.Json;
import validators.DificultadValidator;

import java.util.ArrayList;
import java.util.List;

public class RecetaResource {
    @JsonIgnore
    private Long id;
    @JsonProperty
    @Constraints.Required(message = "name-required") //validadores para el nombre
    @Constraints.MaxLength(50)
    private String name;

    private Integer time;

    @Constraints.ValidateWith(DificultadValidator.class) //Valído que sea un numero correcto(entre el 1 y el 5)
    private Integer dificultad;

    @URL
    private String videoURL;

    private List<String>  pasosList = new ArrayList<>();;

    private List<String> categoriaNames = new ArrayList<>();

    public RecetaResource() {
    }

    public RecetaResource(RecetaModel model){
        super();
        this.id = model.getId();
        this.time = model.getTime();
        this.dificultad = model.getDificultad();
        this.name = model.getName();
        this.videoURL = model.getVideo().getVideoURL();

        for (PasosRecetaModel pasos : model.getPasos()){
            pasosList.add(pasos.getPasosASeguir());
        }

        for (CategoriaRecetaModel categoria : model.getCategorias()){
            categoriaNames.add(categoria.getName());
        }
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    public Integer getDificultad() {
        return dificultad;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public List<String> getPasosList() {
        return pasosList;
    }

    public void setPasosList(List<String> pasosList) {
        this.pasosList = pasosList;
    }

    public List<String> getCategoriaNames() {
        return categoriaNames;
    }

    public void setCategoriaNames(List<String> categoriaNames) {
        this.categoriaNames = categoriaNames;
    }

    public JsonNode toJson(){
        return Json.toJson(this);
    }

    public RecetaModel toModel(){
        RecetaModel receta = new RecetaModel();

        receta.setId(this.id);
        receta.setName(this.name);
        receta.setTime(this.time);
        receta.setDificultad(this.dificultad);

        VideoRecetaModel video = new VideoRecetaModel();
        video.setVideoURL(this.videoURL);
        receta.setVideo(video); //enlazo modelo

        PasosRecetaModel pasos = new PasosRecetaModel();
        pasos.setPasosASeguir(this.pasosList.toString());
        receta.addPaso(pasos); //enlazo modelo


        receta.addCategoria("desayuno");//categoria por defecto pero se debe introducir la categoria en el body

        return receta;
    }


    public void updateModel(RecetaModel recetaModel) {
        recetaModel.setName(this.name);
        recetaModel.setTime(this.time);
        recetaModel.setDificultad(this.dificultad);

        // Actualizo la relación 1-1 (VideoRecetaModel)
        VideoRecetaModel videoModel = recetaModel.getVideo();
        if (videoModel == null) {
            videoModel = new VideoRecetaModel();
        }
        videoModel.setVideoURL(this.videoURL);
        recetaModel.setVideo(videoModel);

        // Actualizo la relación 1-N (PasosRecetaModel)
        List<PasosRecetaModel> pasosModels = new ArrayList<>();
        for (String paso : this.pasosList) {
            PasosRecetaModel pasoModel = new PasosRecetaModel();
            pasoModel.setPasosASeguir(paso);
            pasoModel.setParentReceta(recetaModel);
            pasosModels.add(pasoModel);
        }
        recetaModel.setPasos(pasosModels);

        // Actualizo la relación N-M (CategoriaRecetaModel)
        List<CategoriaRecetaModel> categoriasModels = new ArrayList<>();
        for (String categoria : this.categoriaNames) {
            CategoriaRecetaModel categoriaModel = CategoriaRecetaModel.findCategoriaByName(categoria);
            if (categoriaModel == null) {
                categoriaModel = new CategoriaRecetaModel();
                categoriaModel.setName(categoria);
            }
            categoriasModels.add(categoriaModel);
        }
        recetaModel.setCategorias(categoriasModels);
    }

}
