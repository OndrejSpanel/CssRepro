lazy val commonSettings = Seq(
  organization := "com.github.ondrejspanel",
  version := "0.5.0",
  scalaVersion := "2.13.10",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
)

lazy val jvmLibs = Seq(
  "io.udash" %% "udash-css" % "0.9.0"
)

lazy val sharedJs = project.in(file("shared-js"))
  .enablePlugins(ScalaJSBundlerPlugin)
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
