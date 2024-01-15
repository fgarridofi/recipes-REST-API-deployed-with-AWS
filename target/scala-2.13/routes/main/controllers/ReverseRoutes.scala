// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def getReceta(recetaId:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "receta/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("recetaId", recetaId)))
    }
  
    // @LINE:6
    def listRecetas(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recetas")
    }
  
    // @LINE:10
    def updateReceta(recetaId:Long): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "receta/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("recetaId", recetaId)))
    }
  
    // @LINE:11
    def deleteReceta(recetaId:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "receta/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("recetaId", recetaId)))
    }
  
    // @LINE:7
    def createReceta(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "receta")
    }
  
    // @LINE:9
    def getRecetaByName(name:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "receta/nombre/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("name", name)))
    }
  
  }

  // @LINE:15
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
