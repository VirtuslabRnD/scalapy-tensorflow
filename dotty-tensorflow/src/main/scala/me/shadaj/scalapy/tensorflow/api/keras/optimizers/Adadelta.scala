package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.keras.optimizers.{Adadelta => PyAdadelta}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions

class Adadelta private[api] (override val underlying: PyAdadelta) extends Optimizer(underlying)
