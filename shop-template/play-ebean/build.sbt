name := """play-auth"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.3"

javacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-parameters",
  "-Xlint:unchecked",
  "-Xlint:deprecation",
  "-Werror"
)

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.11.1" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "3.1.3" % Test

libraryDependencies ++= Seq(
  javaJpa,
  javaWs,
  guice,
  jdbc,
  "mysql" % "mysql-connector-java" % "8.0.13",
  "com.h2database" % "h2" % "1.4.199",
  "org.projectlombok" % "lombok" % "1.18.10" % Provided,
  "io.dropwizard.metrics" % "metrics-core" % "3.2.6",
  "com.palominolabs.http" % "url-builder" % "1.1.0",
  "net.jodah" % "failsafe" % "1.0.5",
  "org.glassfish.jaxb" % "jaxb-core" % "2.3.0.1",
  "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2",
  "be.objectify" %% "deadbolt-java" % "2.7.1"
)

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "ebean.properties"

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))