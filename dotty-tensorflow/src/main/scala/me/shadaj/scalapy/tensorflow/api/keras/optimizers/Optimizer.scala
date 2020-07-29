package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.compat.v1.Operation
import me.shadaj.scalapy.tensorflow.api.{Tensor, Variable}

import me.shadaj.scalapy.tensorflow.keras.optimizers.{Optimizer => PyOptimizer}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.PythonType
import scala.language.implicitConversions
import me.shadaj.scalapy.py

trait Optimizer private[api] (val underlying: PyOptimizer) extends PythonType[PyOptimizer] {
  // TODO loss should be a function () => py.Any
  def minimize(loss: py.Any, varList: Seq[Variable]): Operation = underlying.minimize(loss, varList.map(_.underlying))

  def applyGradients(
    gradsAndVars: Seq[(Tensor, Variable)]
    ): Operation =
    underlying.apply_gradients(gradsAndVars.map {
      case (tensor, variable) => (tensor.underlying, variable.underlying)
    })
}
