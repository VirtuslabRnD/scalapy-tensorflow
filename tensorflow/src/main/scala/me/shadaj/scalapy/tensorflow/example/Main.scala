package me.shadaj.scalapy.tensorflow.example

object Main {
  def main(args: Array[String]): Unit = {
    (args match {
      case Array("MnistExample")                    => MnistExample
      case Array("GradientDescentOptimizerExample") => GradientDescentOptimizerExample
      case _                                        => throw new IllegalArgumentException("usage: sbt 'run <simple class name>'")
    }).run()
  }
}
