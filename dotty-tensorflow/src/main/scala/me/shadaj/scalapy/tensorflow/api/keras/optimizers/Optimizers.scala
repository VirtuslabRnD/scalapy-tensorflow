package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.keras.optimizers.{Optimizers => PyOptimizers}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption
import scala.language.implicitConversions

class Optimizers private[api] (val underlying: PyOptimizers) extends PythonType[PyOptimizers] with PythonModule {
  // def Adadelta(): Adadelta = py.native

  // def Adam(learning_rate: Double): Adam = py.native

  def SGD(learningRate: Double, momentum: Option[Double]): SGD = new SGD(underlying.SGD(learningRate, new PythonOption[Double].option2PyOption(momentum)))
}
