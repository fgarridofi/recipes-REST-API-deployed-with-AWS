// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:15
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:15
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recetas""", """controllers.HomeController.listRecetas(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """receta""", """controllers.HomeController.createReceta(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """receta/""" + "$" + """recetaId<[^/]+>""", """controllers.HomeController.getReceta(req:Request, recetaId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """receta/nombre/""" + "$" + """name<[^/]+>""", """controllers.HomeController.getRecetaByName(req:Request, name:String)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """receta/""" + "$" + """recetaId<[^/]+>""", """controllers.HomeController.updateReceta(req:Request, recetaId:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """receta/""" + "$" + """recetaId<[^/]+>""", """controllers.HomeController.deleteReceta(req:Request, recetaId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_listRecetas0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recetas")))
  )
  private[this] lazy val controllers_HomeController_listRecetas0_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.listRecetas(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "listRecetas",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """recetas""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_HomeController_createReceta1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("receta")))
  )
  private[this] lazy val controllers_HomeController_createReceta1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.createReceta(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "createReceta",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """receta""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_getReceta2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("receta/"), DynamicPart("recetaId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getReceta2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.getReceta(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getReceta",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """receta/""" + "$" + """recetaId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_HomeController_getRecetaByName3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("receta/nombre/"), DynamicPart("name", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getRecetaByName3_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.getRecetaByName(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getRecetaByName",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """receta/nombre/""" + "$" + """name<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_HomeController_updateReceta4_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("receta/"), DynamicPart("recetaId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_updateReceta4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.updateReceta(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "updateReceta",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "PUT",
      this.prefix + """receta/""" + "$" + """recetaId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_deleteReceta5_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("receta/"), DynamicPart("recetaId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_deleteReceta5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.deleteReceta(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "deleteReceta",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "DELETE",
      this.prefix + """receta/""" + "$" + """recetaId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_Assets_versioned6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned6_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_listRecetas0_route(params@_) =>
      call { 
        controllers_HomeController_listRecetas0_invoker.call(
          req => HomeController_0.listRecetas(req))
      }
  
    // @LINE:7
    case controllers_HomeController_createReceta1_route(params@_) =>
      call { 
        controllers_HomeController_createReceta1_invoker.call(
          req => HomeController_0.createReceta(req))
      }
  
    // @LINE:8
    case controllers_HomeController_getReceta2_route(params@_) =>
      call(params.fromPath[Long]("recetaId", None)) { (recetaId) =>
        controllers_HomeController_getReceta2_invoker.call(
          req => HomeController_0.getReceta(req, recetaId))
      }
  
    // @LINE:9
    case controllers_HomeController_getRecetaByName3_route(params@_) =>
      call(params.fromPath[String]("name", None)) { (name) =>
        controllers_HomeController_getRecetaByName3_invoker.call(
          req => HomeController_0.getRecetaByName(req, name))
      }
  
    // @LINE:10
    case controllers_HomeController_updateReceta4_route(params@_) =>
      call(params.fromPath[Long]("recetaId", None)) { (recetaId) =>
        controllers_HomeController_updateReceta4_invoker.call(
          req => HomeController_0.updateReceta(req, recetaId))
      }
  
    // @LINE:11
    case controllers_HomeController_deleteReceta5_route(params@_) =>
      call(params.fromPath[Long]("recetaId", None)) { (recetaId) =>
        controllers_HomeController_deleteReceta5_invoker.call(
          req => HomeController_0.deleteReceta(req, recetaId))
      }
  
    // @LINE:15
    case controllers_Assets_versioned6_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned6_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
