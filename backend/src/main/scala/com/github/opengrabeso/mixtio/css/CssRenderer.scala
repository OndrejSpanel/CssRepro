package com.github.opengrabeso.mixtio.css

import com.github.opengrabeso.mixtio.common.css._
import io.udash.css._
import scalacss.internal.{Renderer, StringRenderer}

/** Renderer of styles based on UdashCSS. */
class CssRenderer(renderPretty: Boolean) {
  private val renderer: Renderer[String] =
    if (renderPretty) StringRenderer.defaultPretty
    else StringRenderer.formatTiny

  def render(): String = {
    new CssStringRenderer(
      Seq(
        // the list of styles to be rendered
        GlobalStyles
      )
    ).render()(renderer)
  }
}

object CssRenderer {
  def main(args: Array[String]): Unit = {
    val css = new CssRenderer(true).render()
    println(css)
  }
}
