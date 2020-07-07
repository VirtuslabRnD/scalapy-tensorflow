package me.shadaj.scalapy.tensorflow.api.compat

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.compat.{ Compat => PyCompat}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import me.shadaj.scalapy.tensorflow.api.compat.v1._
import scala.language.implicitConversions


class Compat private[api] (val underlying: PyCompat) extends PythonType[PyCompat] with PythonModule {

  def v1: V1 = new V1(underlying.v1)

}
