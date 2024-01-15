// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def getReceta: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getReceta",
      """
        function(recetaId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "receta/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("recetaId", recetaId0))})
        }
      """
    )
  
    // @LINE:6
    def listRecetas: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.listRecetas",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recetas"})
        }
      """
    )
  
    // @LINE:10
    def updateReceta: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.updateReceta",
      """
        function(recetaId0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "receta/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("recetaId", recetaId0))})
        }
      """
    )
  
    // @LINE:11
    def deleteReceta: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.deleteReceta",
      """
        function(recetaId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "receta/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("recetaId", recetaId0))})
        }
      """
    )
  
    // @LINE:7
    def createReceta: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.createReceta",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "receta"})
        }
      """
    )
  
    // @LINE:9
    def getRecetaByName: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getRecetaByName",
      """
        function(name0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "receta/nombre/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("name", name0))})
        }
      """
    )
  
  }

  // @LINE:15
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
