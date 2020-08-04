package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.keras.optimizers.{Adam => PyAdam}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions

class Adam private[api] (override val underlying: PyAdam) extends Optimizer(underlying)
