package me.shadaj.scalapy.tensorflow.api

import me.shadaj.scalapy.tensorflow.{Variable => PyVariable}
import scalaUtils.PythonType
import scala.language.implicitConversions

class Variable private[api] (override val underlying: PyVariable) extends Tensor(underlying) {}
