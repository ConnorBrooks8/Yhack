import sbt._
import Keys._

object JavaSampleBuild extends Build {
  def scalaSettings = Seq(
    scalaVersion := "2.12.0",
    scalacOptions ++= Seq(
      "-optimize",
      "-unchecked",
      "-deprecation"
    )
  )

  def buildSettings =
    Project.defaultSettings ++
    scalaSettings

  lazy val root = {
    val settings = buildSettings ++ Seq(name := "JavaSample")
    Project(id = "JavaSample", base = file("."), settings = settings)
  }
}
