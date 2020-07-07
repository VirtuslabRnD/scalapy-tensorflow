package me.shadaj.scalapy.tensorflow.api.random

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.random.{ Random => PyRandom}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions

class Random private[api] (val underlying: PyRandom) extends PythonType[PyRandom] with PythonModule {
  def uniform(shape: Seq[Int], minval: Double, maxval: Double): Tensor = underlying.uniform(shape, minval, maxval) 
}
