import sbt.Keys.scalacOptions
// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val commonSettings = Seq(
  organization := "com.github.ondrejspanel",
  version := "0.5.0",
  scalaVersion := "2.12.17",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
)

lazy val jvmLibs = Seq(
  "io.udash" %% "udash-css" % "0.8.6"
)

lazy val sharedJs = project.in(file("shared-js"))
  .disablePlugins(sbtassembly.AssemblyPlugin)
  .settings(commonSettings)
  .settings(libraryDependencies ++= jvmLibs)

lazy val backend = (project in file("backend"))
  .dependsOn(sharedJs)
  .settings(
    commonSettings,
    libraryDependencies ++= jvmLibs,
  )

lazy val root = (project in file(".")).aggregate(backend).settings(
  name := "CssRepro"
)
