package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.RecetaModel;
import play.cache.SyncCacheApi;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.*;
import play.twirl.api.Xml;
import views.RecetaResource;
import views.xml.receta;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomeController extends Controller {

    @Inject
    FormFactory formFactory;

    @Inject
    MessagesApi messagesApi;

    @Inject
    private SyncCacheApi cache;

    //Controlador para crear una receta
    public Result createReceta(Http.Request request) {
        //input
        Form<RecetaResource> form = formFactory.form(RecetaResource.class).bindFromRequest(request);

        if(form.hasErrors()){
            return badRequest(form.errorsAsJson());
        }

        // logic
        RecetaResource recetaRes =form.get();
        RecetaModel recetaModel =recetaRes.toModel();
        recetaModel.save();

        //output
        return Results
                .created(messagesApi.preferred(request).at("recipe-generated-success") //internacionalización para es/en
                        + new RecetaResource(recetaModel).toJson())
                .as("application/json");
    }

    //Controlador para obtener una receta por id
    public Result getReceta(Http.Request req, Long recetaId) {
        Optional<Object> cachedReceta = cache.get("receta-" + recetaId);
        RecetaModel model;

        //si está en cache lo obtengo de la cache si no, leo la bd
        if(cachedReceta.isPresent()){
            System.out.println("leo de cache");
            model = (RecetaModel) cachedReceta.get();
        }
        else{
            // logic
            System.out.println("leo de bd");
            model = RecetaModel.findById(recetaId);
            if(model == null){
                return Results.notFound(messagesApi.preferred(req).at("recipe-not-found"));
            }

            cache.set("receta-"+ recetaId, model);
        }

        RecetaResource recetaRes = new RecetaResource(model);

        //output
        if(req.header("Accept").isEmpty()){
            return Results.notAcceptable();
        }else {
            System.out.println(req.header("Accept"));
        }
        //content negotation
        if (req.accepts("application/json")) {
            JsonNode json = recetaRes.toJson(); //serialización
            return ok(json);
        } else if (req.accepts("application/xml")) {
            Xml content = receta.render(recetaRes);
            return ok(content);
        } else {
            return Results.notAcceptable();
        }
    }

    //Controlador para obtener una receta por nombre
    public Result getRecetaByName(Http.Request req, String name) {
        Optional<Object> cachedReceta = cache.get("receta-" + name);
        List<RecetaModel> models;

        //si está en cache lo obtengo de la cache si no, leo la bd
        if(cachedReceta.isPresent()){
            System.out.println("leo de cache");
            models = (List<RecetaModel>) cachedReceta.get();
        }
        else{
            // logic
            System.out.println("leo de bd");
            models = RecetaModel.findByName(name,0);
            if(models == null){
                return Results.notFound(messagesApi.preferred(req).at("recipe-not-found"));
            }

            cache.set("receta-"+ name, models);
        }

        List<Xml> recetaXmlList = new ArrayList<>();

        for (RecetaModel model : models) {
            RecetaResource recetaResource = new RecetaResource(model);
            recetaXmlList.add(views.xml.receta.render(recetaResource));

        }

        //output
        if(req.header("Accept").isEmpty()){
            return Results.notAcceptable();
        }else {
            System.out.println(req.header("Accept"));
        }
        //content negotation
        if (req.accepts("application/json")) {
            ArrayNode recetaArray = JsonNodeFactory.instance.arrayNode();
            for (RecetaModel model : models) {
                RecetaResource recetaResource = new RecetaResource(model);
                JsonNode recetaJson = recetaResource.toJson();
                recetaArray.add(recetaJson);
            }
            return ok(recetaArray);
        } else if (req.accepts("application/xml")) {
            //combino todos los xml en uno
            StringBuilder combinedXml = new StringBuilder("<recetas>");
            for (Xml xml : recetaXmlList) {
                combinedXml.append(xml.body());
            }
            combinedXml.append("</recetas>");
            return ok(combinedXml.toString());
        } else {
            return Results.notAcceptable();
        }
    }

    //Controlador para listar todas las recetas
    public Result listRecetas(Http.Request req) {

        List<RecetaModel> models = RecetaModel.findAll();
        if(models == null|| models.isEmpty()){
            return Results.notFound(messagesApi.preferred(req).at("recipe-not-found"));
        }

        List<Xml> recetaXmlList = new ArrayList<>();

        for (RecetaModel model : models) {
            RecetaResource recetaResource = new RecetaResource(model);
            recetaXmlList.add(views.xml.receta.render(recetaResource));
        }

        //content negotation
        if (req.accepts("application/json")) {
            ArrayNode recetaArray = JsonNodeFactory.instance.arrayNode();
            for (RecetaModel model : models) {
                RecetaResource recetaResource = new RecetaResource(model);
                JsonNode recetaJson = recetaResource.toJson();
                recetaArray.add(recetaJson);
            }
            return ok(recetaArray);
        } else if (req.accepts("application/xml")) {
            //combino todos los xml en uno
            StringBuilder combinedXml = new StringBuilder("<recetas>");
            for (Xml xml : recetaXmlList) {
                combinedXml.append(xml.body());
            }
            combinedXml.append("</recetas>");
            return ok(combinedXml.toString());
        } else {
            return Results.notAcceptable();
        }
    }

    //Controlador para actualizar una receta
    public Result updateReceta(Http.Request request,Long recetaId) {
        // Input
        Form<RecetaResource> form = formFactory.form(RecetaResource.class).bindFromRequest(request);

        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        // Lógica
        RecetaModel recetaModel = RecetaModel.findById(recetaId);

        if (recetaModel == null) {
            return Results.notFound(messagesApi.preferred(request).at("recipe-not-found"));
        }

        RecetaResource updatedRecetaResource = form.get();
        updatedRecetaResource.updateModel(recetaModel); // Actualiza el modelo existente
        recetaModel.update();

        // Lógica de caché
        cache.set("receta-" + recetaId, recetaModel);

        // Output
        String successMsg = messagesApi.preferred(request).at("recipe-modified-success");
        return ok(successMsg+new RecetaResource(recetaModel).toJson()).as("application/json");
    }


    //Controlador para eliminar receta
    public Result deleteReceta(Http.Request req,Long recetaId) {

        RecetaModel model = RecetaModel.findById(recetaId);
        if(model == null){
            return Results.notFound(messagesApi.preferred(req).at("recipe-not-found"));
        }

        boolean ok = model.delete();
        if (ok) {
            String successMsg = messagesApi.preferred(req).at("recipe-deleted-success");
            return Results.ok(successMsg);
        } else {
            String notFoundMsg = messagesApi.preferred(req).at("recipe-not-deleted");
            return Results.notFound(notFoundMsg);
        }
    }
}
