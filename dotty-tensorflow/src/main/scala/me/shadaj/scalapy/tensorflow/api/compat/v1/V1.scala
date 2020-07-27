package me.shadaj.scalapy.tensorflow.api.compat.v1

import me.shadaj.scalapy.tensorflow.api.Tensor
import me.shadaj.scalapy.tensorflow.compat.v1.{V1 => PyV1}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions

class V1 private[api] (val underlying: PyV1) extends PythonType[PyV1] with PythonModule
