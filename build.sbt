import sbt.Keys.libraryDependencies
import scala.sys.process._
import sbtcrossproject.CrossPlugin.autoImport.{ CrossType, crossProject }

lazy val scala211Version = "2.11.12"
lazy val scala212Version = "2.12.11"
lazy val scala213Version = "2.13.2"

lazy val dottyVersion = "0.24.0-RC1"

organization in ThisBuild := "me.shadaj"
scalaVersion in ThisBuild := scala212Version


addCommandAlias(
  "publishSignedAll",
  (scalaPyTensorFlow: ProjectDefinition[ProjectReference])
    .aggregate
    .map(p => s"+ ${p.asInstanceOf[LocalProject].project}/publishSigned")
    .mkString(";", ";", "")
)

lazy val scalaPyTensorFlow = project
  .in(file("."))
  .aggregate(
    scalaPyTensorFlowJVM,
    scalaPyTensorFlowNative,
    dottyTensorflow
  ).settings(
    name := "scalapy-tensorflow",
    publish := {},
    publishLocal := {},
    scalacOptions ++= Seq(
      "-language:implicitConversions"
    )
  )

lazy val dottyTensorflow = project
  .in(file("dotty-tensorflow"))
  .settings(
    name := "dotty-tensorflow",
    scalaVersion := dottyVersion,
    // ScalaPy:
    libraryDependencies += "me.shadaj" % "scalapy-core_2.13" % "0.3.0+38-2597f7b2",
    fork := true,
    javaOptions += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib",
    projectDependencies ~=(_.map(_.withDottyCompat(dottyVersion))),
  )
  .dependsOn(scalaPyTensorFlowJVM)

lazy val scalaPyTensorFlowCross = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("tensorflow"))
  .settings(
    name := "scalapy-tensorflow-cross",
    // scalapy-core version will replace the one in scalapy-numpy (maintaining binary compatibility)
    libraryDependencies += "me.shadaj" %%% "scalapy-core" % "0.3.0+38-2597f7b2",
    libraryDependencies += "me.shadaj" %%% "scalapy-numpy" % "0.1.0+6-14ca0424" exclude("me.shadaj", "scalapy-core"),
    // 3.2.0-SNAP10 is the latest version of scalatest for which a scala-native is available for Scala 2.11.
    // Unfortunately, no stable version of scalatest seems to be available on Maven Central for scala-native :(
    projectDependencies ~=(_.map(_.withDottyCompat(dottyVersion))),
  ).jvmSettings(
    scalaVersion := scala213Version,
    fork in Test := true,
    projectDependencies ~=(_.map(_.withDottyCompat(dottyVersion))),
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.3" % Test,
    fork := true,
    javaOptions += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib",
    javaOptions in Test += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib"
  ).nativeSettings(
    // We need to stick to Scala 2.11 here since scalapy-core and scalapy-numpy are only provided for scala-native (0.3) under Scala 2.11
    scalaVersion := scala211Version,
    libraryDependencies += "com.github.lolgab" %%% "scalacheck" % "1.14.1" % Test,
    nativeLinkStubs := true,
    nativeLinkingOptions ++= {
      import scala.sys.process._
      Seq(
        "-rpath", s"${"python3-config --prefix".!!.trim}/lib",
        s"-L${"python3-config --prefix".!!.trim}/lib",
      ) ++ "python3-config --ldflags".!!.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
    }
  )

lazy val scalaPyTensorFlowJVM = scalaPyTensorFlowCross.jvm.settings(name := "scalapy-tensorflow-jvm")
lazy val scalaPyTensorFlowNative = scalaPyTensorFlowCross.native.settings(name := "scalapy-tensorflow-native")

// To make sure that changes to project structure are picked up by sbt without an explicit `reload`
Global / onChangedBuildSource := ReloadOnSourceChanges
