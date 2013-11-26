name := "akka-console-request-tracking-test"

organization := "org.elmarweber"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.2"


resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)


val akkaVersion = "2.1.4"
val specsVersion = "2.2.3"

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.specs2" %% "specs2" % specsVersion % "test",
    "com.typesafe.atmos" % "trace-akka-2.1.4" % "1.3.1"
  )
}

atmosSettings

traceAkka(akkaVersion)
