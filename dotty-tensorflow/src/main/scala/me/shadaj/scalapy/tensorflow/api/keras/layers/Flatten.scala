package me.shadaj.scalapy.tensorflow.api.keras.layers

import me.shadaj.scalapy.tensorflow.keras.layers.{Flatten => PyFlatten}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions

class Flatten private[api] (override val underlying: PyFlatten) extends Layer(underlying) {
  def dataFormat: Option[DataFormat] = underlying.data_format.toScalaOption.map(DataFormat.valueOf)
}
