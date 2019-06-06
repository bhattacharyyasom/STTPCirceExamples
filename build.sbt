import scalariform.formatter.preferences._

name := "SttpCirceWorkingSet"
version := "0.1"
scalaVersion := "2.12.8"


val circeVersion = "0.11.1"
val sttpVersion="1.5.12"

resolvers ++= Seq(
  "Typesafe Repository"               at "http://repo.typesafe.com/typesafe/releases/",
  "SpringSource Milestone Repository" at "http://repo.spring.io/libs-milestone-local",
  "scoverage-bintray"                 at "https://dl.bintray.com/sksamuel/sbt-plugins/",
  "Boundless Repository"              at "https://repo.boundlessgeo.com/main/"
)

libraryDependencies ++= Seq(
  "io.circe"  %% "circe-core"     % circeVersion,
  "io.circe"  %% "circe-generic"  % circeVersion,
  "io.circe"  %% "circe-parser"   % circeVersion,
  "com.softwaremill.sttp" %% "core" % sttpVersion,
  "com.softwaremill.sttp" %% "circe" % sttpVersion
)

scalaSource in Compile := baseDirectory.value / "src"/"main"

scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DanglingCloseParenthesis, Preserve)
