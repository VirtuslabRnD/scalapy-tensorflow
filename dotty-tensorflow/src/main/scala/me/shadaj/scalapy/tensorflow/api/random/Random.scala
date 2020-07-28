package me.shadaj.scalapy.tensorflow.api.random

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.random.{Random => PyRandom}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonModule
import scala.language.implicitConversions

class Random private[api] (val underlying: PyRandom) extends PythonModule[PyRandom] {
  def uniform(shape: Seq[Int], minval: Double, maxval: Double): Tensor = underlying.uniform(shape, minval, maxval)
}
