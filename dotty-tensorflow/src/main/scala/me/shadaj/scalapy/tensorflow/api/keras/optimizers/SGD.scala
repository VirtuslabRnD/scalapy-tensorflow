package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.keras.optimizers.{ SGD => PySGD}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions

class SGD private[api] (override val underlying: PySGD) extends Optimizer(underlying)
