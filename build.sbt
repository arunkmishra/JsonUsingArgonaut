import LibraryVersion._

name := "error_log"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  jodaTime,
  argonaut,
  scalaz
)