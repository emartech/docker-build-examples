ThisBuild / scalaVersion := "2.13.5"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.emarsys"
ThisBuild / organizationName := "Emarsys"

val Http4sVersion         = "0.21.24"
val CirceVersion          = "0.14.1"
val LoggerVersion         = "0.8.2"
val LogbackEncoderVersion = "5.2"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .settings(
    name := "scala-native-packager",
    libraryDependencies ++= Seq(
      "org.http4s"          %% "http4s-ember-server"      % Http4sVersion,
      "org.http4s"          %% "http4s-circe"             % Http4sVersion,
      "org.http4s"          %% "http4s-dsl"               % Http4sVersion,
      "io.circe"            %% "circe-generic"            % CirceVersion,
      "com.emarsys"         %% "scala-logger"             % LoggerVersion,
      "com.emarsys"         %% "scala-logger-ce2"         % LoggerVersion,
      "net.logstash.logback" % "logstash-logback-encoder" % LogbackEncoderVersion
    ),
    Docker / packageName := "scala-native-packager",
    Docker / version := "latest",
    dockerRepository := Some("eu.gcr.io"),
    dockerUsername := Some("ems-gap-images"),
    dockerBaseImage := "openjdk:16-slim",
    dockerExposedPorts := Seq(8080)
  )
