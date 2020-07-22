package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.keras.optimizers.{Optimizers => PyOptimizers}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum
import scala.language.implicitConversions

enum OptimizerNames (override private[api] val v: String) extends PythonEnum(v){
  case Adam extends OptimizerNames("adam")
  case Adagrad extends OptimizerNames("adagrad")
  case Adadelta extends OptimizerNames("adadelta")
  case Adamax extends OptimizerNames("adamax")
  case Ftrl extends OptimizerNames("ftrl")
  case Nadam extends OptimizerNames("nadam")
  case RMSprop extends OptimizerNames("rmsprop")
  case SGD extends OptimizerNames("sgd")
}

class Optimizers private[api] (val underlying: PyOptimizers) extends PythonModule[PyOptimizers] {
  def Adadelta(): Adadelta = new Adadelta(underlying.Adadelta)

  def Adam(learningRate: Double): Adam = new Adam(underlying.Adam(learningRate))

  def SGD(learningRate: Double, momentum: Option[Double]): SGD = new SGD(underlying.SGD(learningRate, momentum))
}
