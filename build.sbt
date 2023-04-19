import sbt.Keys.scalacOptions
// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val commonSettings = Seq(
  organization := "com.github.ondrejspanel",
  version := "0.5.0",
  scalaVersion := "2.13.10",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
)

lazy val jvmLibs = Seq(
  "io.udash" %% "udash-css" % "0.9.0"
)

lazy val sharedJs = crossProject(JVMPlatform)
  .crossType(CrossType.Pure).in(file("shared-js"))
  .disablePlugins(sbtassembly.AssemblyPlugin)
  .enablePlugins(JSDependenciesPlugin)
  .settings(commonSettings)
  .jvmSettings(libraryDependencies ++= jvmLibs)

lazy val sharedJs_JVM = sharedJs.jvm

lazy val backend = (project in file("backend"))
  .dependsOn(sharedJs.jvm)
  .settings(
    commonSettings,
    libraryDependencies ++= jvmLibs,
  )

lazy val root = (project in file(".")).aggregate(backend).settings(
  name := "CssRepro"
)
