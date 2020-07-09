package me.shadaj.scalapy.tensorflow.api.compat.v1

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.compat.v1.{V1 => PyV1}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions

class V1 private[api] (val underlying: PyV1) extends PythonType[PyV1] with PythonModule {
  // def placeholder(`type`: String): Tensor = py.native

  // def placeholder(`type`: String, shape: Seq[py.NoneOr[Int]]): Tensor = py.native

  // def global_variables_initializer(): Operation = py.native

  // def Session(): Session = py.native

  // def InteractiveSession(): Session = py.native

}
