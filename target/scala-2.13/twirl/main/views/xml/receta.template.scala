
package views.xml

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.xml._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object receta extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[views.RecetaResource,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(res: views.RecetaResource):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<receta>
    <id>"""),_display_(/*5.10*/res/*5.13*/.getId),format.raw/*5.19*/("""</id>
    <name>"""),_display_(/*6.12*/res/*6.15*/.getName),format.raw/*6.23*/("""</name>
    <time>"""),_display_(/*7.12*/res/*7.15*/.getTime),format.raw/*7.23*/("""</time>
    <dificultad>"""),_display_(/*8.18*/res/*8.21*/.getDificultad),format.raw/*8.35*/("""</dificultad>
    <videoURL>"""),_display_(/*9.16*/res/*9.19*/.getVideoURL),format.raw/*9.31*/("""</videoURL>
    <pasosList>
        """),_display_(/*11.10*/res/*11.13*/.getPasosList().mkString(", ")),format.raw/*11.43*/("""
    """),format.raw/*12.5*/("""</pasosList>
    <categoriaNames>
        """),_display_(/*14.10*/res/*14.13*/.getCategoriaNames().mkString(", ")),format.raw/*14.48*/("""
    """),format.raw/*15.5*/("""</categoriaNames>

</receta>"""))
      }
    }
  }

  def render(res:views.RecetaResource): play.twirl.api.XmlFormat.Appendable = apply(res)

  def f:((views.RecetaResource) => play.twirl.api.XmlFormat.Appendable) = (res) => apply(res)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/receta.scala.xml
                  HASH: c6492873119bf9bb05cc1336b9fd0fe778970735
                  MATRIX: 916->1|1036->29|1063->30|1163->104|1174->107|1200->113|1243->130|1254->133|1282->141|1327->160|1338->163|1366->171|1417->196|1428->199|1462->213|1517->242|1528->245|1560->257|1624->294|1636->297|1687->327|1719->332|1789->375|1801->378|1857->413|1889->418
                  LINES: 27->1|32->2|33->3|35->5|35->5|35->5|36->6|36->6|36->6|37->7|37->7|37->7|38->8|38->8|38->8|39->9|39->9|39->9|41->11|41->11|41->11|42->12|44->14|44->14|44->14|45->15
                  -- GENERATED --
              */
          