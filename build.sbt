name := """forward-me-this"""
organization := "com.coderfromscratch"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.2"

libraryDependencies += guice

// Validate Emails
libraryDependencies += "commons-validator" % "commons-validator" % "1.6"

// Database Support
libraryDependencies ++= Seq(
  javaJdbc,
  evolutions,
  "mysql" % "mysql-connector-java" % "8.0.20",
  "org.glassfish.jaxb" % "jaxb-core" % "2.3.0.1",
  "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2"
)