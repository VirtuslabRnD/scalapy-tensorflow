package me.shadaj.scalapy.tensorflow.api.keras

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.keras.{ Keras => PyKeras}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions
import optimizers.Optimizers
class Keras private[api] (val underlying: PyKeras) extends PythonType[PyKeras] with PythonModule {

  // def models: Models = py.native
  // def datasets: Datasets = py.native
  // def backend: Backend = py.native
  // def utils: Utils = py.native
   def optimizers: Optimizers = new Optimizers(underlying.optimizers)
  // def losses: Losses = py.native
  // def layers: Layers = py.native
  // def preprocessing: Preprocessing = py.native

  // def Input(
  //     shape: Seq[py.NoneOr[Int]] = Seq(),
  //     name: py.NoneOr[String] = py.None,
  //     dtype: py.NoneOr[String] = py.None,
  //     sparse: Boolean = false,
  //     tensor: py.NoneOr[Tensor] = py.None,
  //     kwargs: Map[String, py.Any] = Map()
  // ): Tensor = py.nativeNamed

}