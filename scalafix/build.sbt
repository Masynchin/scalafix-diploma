lazy val V = _root_.scalafix.sbt.BuildInfo
inThisBuild(
  List(
    organization := "ch.epfl.scala",
    homepage := Some(url("https://github.com/scalacenter/named-literal-arguments")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    libraryDependencies ++= Seq("org.typelevel" %% "cats-core" % "2.10.0"),
    developers := List(
      Developer(
        "olafurpg",
        "Olafur Geirssson",
        "olafur@geirsson.com",
        url("https://geirsson.com")
      )
    ),
    scalaVersion := V.scala213,
    semanticdbEnabled := true,
    semanticdbIncludeInJar := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalacOptions ++= List("-Yrangepos")
  )
)


(publish / skip) := true

lazy val rules = project.settings(
  moduleName := "named-literal-arguments",
  libraryDependencies ++= Seq(
    "ch.epfl.scala" %% "scalafix-core" % V.scalafixVersion,
    "org.typelevel" %% "cats-core" % "2.10.0"
  )
)

lazy val input = project.settings(
  (publish / skip) := true,
    libraryDependencies ++= Seq("org.typelevel" %% "cats-core" % "2.10.0")
)

lazy val output = project.settings(
  (publish / skip) := true,
  libraryDependencies ++= Seq("org.typelevel" %% "cats-core" % "2.10.0")
)

lazy val tests = project
  .settings(
    (publish / skip) := true,
    libraryDependencies ++= Seq(
      "ch.epfl.scala" % "scalafix-testkit" % V.scalafixVersion % Test cross CrossVersion.full,
      "org.typelevel" %% "cats-core" % "2.10.0"
    ),
    scalafixTestkitOutputSourceDirectories :=
      (output / Compile / unmanagedSourceDirectories).value,
    scalafixTestkitInputSourceDirectories :=
      (input / Compile / unmanagedSourceDirectories).value,
    scalafixTestkitInputClasspath :=
      (input / Compile / fullClasspath).value
  )
  .dependsOn(rules)
  .enablePlugins(ScalafixTestkitPlugin)
