package me.shadaj.scalapy.tensorflow.api.keras.layers

import me.shadaj.scalapy.tensorflow.keras.layers.{Dropout => PyDropout}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonUnion._
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonOption._
import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.api.Tensor._
import scala.language.implicitConversions

class Dropout private[api] (override val underlying: PyDropout) extends Layer(underlying) {
  def rate: Double = underlying.rate
  def noiseShape: Option[Tensor] = pyOption2Option(underlying.noise_shape).map(_.underlying)
  def seed: Option[Int] = underlying.seed
}
