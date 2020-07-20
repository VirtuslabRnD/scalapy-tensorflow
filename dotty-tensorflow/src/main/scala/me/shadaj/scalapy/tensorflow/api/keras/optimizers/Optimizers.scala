package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.keras.optimizers.{Optimizers => PyOptimizers}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions

class Optimizers private[api] (val underlying: PyOptimizers) extends PythonModule[PyOptimizers] {
  def Adadelta(): Adadelta = new Adadelta(underlying.Adadelta)

  def Adam(learningRate: Double): Adam = new Adam(underlying.Adam(learningRate))

  def SGD(learningRate: Double, momentum: Option[Double]): SGD = new SGD(underlying.SGD(learningRate, momentum))
}
