package me.shadaj.scalapy.tensorflow.api.keras.layers

import me.shadaj.scalapy.tensorflow.keras.layers.{MaxPooling2D => PyMaxPooling2D}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import scala.language.implicitConversions

class MaxPooling2D private[api] (override val underlying: PyMaxPooling2D) extends Layer(underlying) {
  def poolSize: (Int, Int) = underlying.pool_size
  def strides: Option[Int | (Int, Int)] = pyOption2Option(underlying.strides).map(fromPythonUnion(_))
  def padding: String = underlying.padding
  def dataFormat: Option[String] = underlying.data_format
}
