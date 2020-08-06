package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.keras.optimizers.{Optimizers => PyOptimizers}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonEnum
import scala.language.implicitConversions

enum OptimizerEnum (override private[api] val v: String) extends PythonEnum(v){
  case Adam extends OptimizerEnum("adam")
  case Adagrad extends OptimizerEnum("adagrad")
  case Adadelta extends OptimizerEnum("adadelta")
  case Adamax extends OptimizerEnum("adamax")
  case FtRL extends OptimizerEnum("ftrl")
  case Nadam extends OptimizerEnum("nadam")
  case RMSprop extends OptimizerEnum("rmsprop")
  case SGD extends OptimizerEnum("sgd")
}

class Optimizers private[api] (val underlying: PyOptimizers) extends PythonModule[PyOptimizers] {
  def Adadelta(): Adadelta = new Adadelta(underlying.Adadelta)

  def Adam(learningRate: Double): Adam = new Adam(underlying.Adam(learningRate))

  def SGD(learningRate: Double, momentum: Option[Double]): SGD = new SGD(underlying.SGD(learningRate, momentum))
}
