package me.shadaj.scalapy.tensorflow.api.compat.v1

import me.shadaj.scalapy.tensorflow.compat.v1.{Operation => PyOperation}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonType

trait Operation private[api] (val underlying: PyOperation) extends PythonType[PyOperation]
