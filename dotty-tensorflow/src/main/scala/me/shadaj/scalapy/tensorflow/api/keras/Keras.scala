package me.shadaj.scalapy.tensorflow.api.keras

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.keras.{Keras => PyKeras}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions
import optimizers.Optimizers
class Keras private[api] (val underlying: PyKeras) extends PythonType[PyKeras] with PythonModule {
  def optimizers: Optimizers = new Optimizers(underlying.optimizers)
}
