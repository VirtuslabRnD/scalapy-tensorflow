import sbt.Keys.libraryDependencies
import scala.sys.process._
import sbtcrossproject.CrossPlugin.autoImport.{ CrossType, crossProject }

lazy val scala211Version = "2.11.12"
lazy val scala212Version = "2.12.11"
lazy val scala213Version = "2.13.2"

lazy val dottyVersion = "0.26.0-RC1"

organization in ThisBuild := "me.shadaj"
scalaVersion in ThisBuild := scala212Version

resolvers in ThisBuild += "jitpack" at "https://jitpack.io"

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
    scalaPyTensorFlowExamplesJVM,
    //scalaPyTensorFlowNative,
    //scalaPyTensorFlowExamplesNative,
    dottyTensorFlow,
    dottyTensorFlowExamples
  ).settings(
    name := "scalapy-tensorflow",
    publish / skip := true,
    scalacOptions ++= Seq(
      "-language:implicitConversions"
    )
  )

lazy val dottyTensorFlow = project
  .in(file("dotty-tensorflow"))
  .settings(
    name := "dotty-tensorflow",
    scalaVersion := dottyVersion,
    libraryDependencies += "com.github.shadaj.scalapy" % "scalapy-core_2.13" % "90a9ea7b731d7f457d11659c4b945b2816dbd5c4",
    fork := true,
    javaOptions += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib",
    projectDependencies ~=(_.map(_.withDottyCompat(dottyVersion))),
  )
  .dependsOn(scalaPyTensorFlowJVM)

lazy val dottyTensorFlowExamples = project
  .in(file("dotty-tensorflow-examples"))
  .settings(
    name := "dotty-tensorflow-examples",
    scalaVersion := dottyVersion,  
    javaOptions += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib",
    fork := true,
    publish / skip := true
  )
  .dependsOn(dottyTensorFlow)


lazy val scalaPyTensorFlowCross = crossProject(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("tensorflow"))
  .settings(
    name := "scalapy-tensorflow-cross",
    // scalapy-core version will replace the one in scalapy-numpy (maintaining binary compatibility)
    libraryDependencies += "com.github.shadaj.scalapy" %% "scalapy-core" % "90a9ea7b731d7f457d11659c4b945b2816dbd5c4",
    libraryDependencies += "com.github.piotrmwojcik" % "scalapy-numpy" % "bc2bce338c12bff8831cff8a5213e1a0af829de6" exclude("me.shadaj", "scalapy-core_2.13"),
    projectDependencies ~=(_.map(_.withDottyCompat(dottyVersion))),
  ).jvmSettings(
    scalaVersion := scala213Version,
    fork in Test := true,
    projectDependencies ~=(_.map(_.withDottyCompat(dottyVersion))),
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.3" % Test,
    fork := true,
    javaOptions += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib",
    javaOptions in Test += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib"
  )/*.nativeSettings(
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
  )*/

lazy val scalaPyTensorFlowJVM = scalaPyTensorFlowCross.jvm.settings(name := "scalapy-tensorflow-jvm")
//lazy val scalaPyTensorFlowNative = scalaPyTensorFlowCross.native.settings(name := "scalapy-tensorflow-native")


lazy val scalaPyTensorFlowExamplesCross = crossProject(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("tensorflow-examples"))
  .settings(
    name := "tensorflow-examples-cross",
    publish / skip := true
  ).jvmSettings(
    scalaVersion := scala213Version,
    javaOptions += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib",
    javaOptions in Test += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib",
    fork := true
  )/*.nativeSettings(
    scalaVersion := scala211Version,
    nativeLinkStubs := true,
    nativeLinkingOptions ++= {
      import scala.sys.process._
      Seq(
        "-rpath", s"${"python3-config --prefix".!!.trim}/lib",
        s"-L${"python3-config --prefix".!!.trim}/lib",
      ) ++ "python3-config --ldflags".!!.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
    }
  )*/
  .dependsOn(scalaPyTensorFlowCross)

lazy val scalaPyTensorFlowExamplesJVM = scalaPyTensorFlowExamplesCross.jvm.settings(name := "tensorflow-example-jvm")
//lazy val scalaPyTensorFlowExamplesNative = scalaPyTensorFlowExamplesCross.native.settings(name := "tensorflow-example-native")


// To make sure that changes to project structure are picked up by sbt without an explicit `reload`
Global / onChangedBuildSource := ReloadOnSourceChanges
