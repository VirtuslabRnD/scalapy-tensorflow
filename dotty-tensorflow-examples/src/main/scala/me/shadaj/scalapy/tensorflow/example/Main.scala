package me.shadaj.scalapy.tensorflow.example

object Main {
  def main(args: Array[String]): Unit = {
    (args match {
      case Array("BidirectionalLSTMExample")        => BidirectionalLSTMExample
      case Array("GradientDescentOptimizerExample") => GradientDescentOptimizerExample
      case Array("MnistExample")                    => MnistExample
      case _                                        => throw new IllegalArgumentException("usage: sbt 'run <simple class name>'")
    }).run()
  }
}
