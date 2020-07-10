package me.shadaj.scalapy.tensorflow.api.keras.optimizers

import me.shadaj.scalapy.tensorflow.compat.v1.Operation
import me.shadaj.scalapy.tensorflow.api.{Tensor, Variable}

import me.shadaj.scalapy.tensorflow.keras.optimizers.{Optimizer => PyOptimizer}
import me.shadaj.scalapy.tensorflow.api.scalaUtils.{PythonModule, PythonType}
import scala.language.implicitConversions
import me.shadaj.scalapy.py

trait Optimizer private[api] (val underlying: PyOptimizer) extends PythonType[PyOptimizer] {
  // TODO loss should be a function () => py.Any
  def minimize(loss: py.Any, varList: Seq[Variable]): Operation = underlying.minimize(loss, varList.map(_.underlying))

  def applyGradients(gradsAndVars: Seq[(Tensor, Variable)]): (Seq[Tensor], Operation) = {
    var vars = gradsAndVars.map(_._2)
    val varsCopy = vars.map(_.readValue())
    val op = underlying.apply_gradients(gradsAndVars.map {
      case (tensor, variable) => (tensor.underlying, variable.underlying)
    })
    val varsCopy2 = vars.map(_.readValue())
    vars.zip(varsCopy).map {
      case (v, c) => v.assign(c)
    }
    (varsCopy2, op)
  }

}
